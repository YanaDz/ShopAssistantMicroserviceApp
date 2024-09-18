package pl.dziadkouskaya.search_server.service.impl;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.stereotype.Service;
import pl.dziadkouskaya.search_server.entity.Seller;
import pl.dziadkouskaya.search_server.entity.dto.ComplexTitlePart;
import pl.dziadkouskaya.search_server.entity.dto.SearchResult;
import pl.dziadkouskaya.search_server.entity.enums.SellerElementField;
import pl.dziadkouskaya.search_server.entity.enums.SellerElementPriority;
import pl.dziadkouskaya.search_server.entity.enums.SellerElementType;
import pl.dziadkouskaya.search_server.exception.ResourceNotFoundException;
import pl.dziadkouskaya.search_server.exception.SellerParsingException;
import pl.dziadkouskaya.search_server.exception.ShopNotAvailableException;
import pl.dziadkouskaya.search_server.repository.SellerRepository;
import pl.dziadkouskaya.search_server.service.SearchService;

import java.util.ArrayList;
import java.util.List;

import static pl.dziadkouskaya.search_server.entity.enums.SellerElementPriority.ADDITIONAL_INCLUDED_ELEMENT_FIRST;
import static pl.dziadkouskaya.search_server.entity.enums.SellerElementPriority.ADDITIONAL_INCLUDED_ELEMENT_SECOND;
import static pl.dziadkouskaya.search_server.entity.enums.SellerElementPriority.MAIN_ELEMENT;
import static pl.dziadkouskaya.search_server.entity.enums.SellerElementPriority.ONE_ELEMENT;
import static pl.dziadkouskaya.search_server.utils.Constants.DIV_CONTAINS;
import static pl.dziadkouskaya.search_server.utils.Constants.DIV_STARTS_WITH;
import static pl.dziadkouskaya.search_server.utils.Constants.ERROR_PARSING_NO_ONE_OR_MAIN_PRIORITY;
import static pl.dziadkouskaya.search_server.utils.Constants.ERROR_PARSING_PRICE;
import static pl.dziadkouskaya.search_server.utils.Constants.ERROR_PARSING_TITLE;
import static pl.dziadkouskaya.search_server.utils.Constants.HREF_ATTRIBUTE;
import static pl.dziadkouskaya.search_server.utils.Constants.LINK;
import static pl.dziadkouskaya.search_server.utils.Constants.SEMICOLON;
import static pl.dziadkouskaya.search_server.utils.Constants.SHOP_CONNECTION_IS_NOT_AVAILABLE;
import static pl.dziadkouskaya.search_server.utils.Constants.SPACE;
import static pl.dziadkouskaya.search_server.utils.Validation.checkEmptyString;

@Service
@Slf4j
public class SearchServiceImpl implements SearchService {
    private final SellerRepository sellerRepository;

    public SearchServiceImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public List<SearchResult> getSellerProducts(String request) {
        var allSellers = sellerRepository.findAll();
        log.info("Found {} sellers.", allSellers.size());
        var products = allSellers.parallelStream()
            .peek(seller -> log.info("Start getting results from seller {}.", seller.getName()))
            .map(seller -> {
                try {
                    return getSearchResults(request, seller);
                } catch (InterruptedException e) {
                    throw new ShopNotAvailableException(String.format(SHOP_CONNECTION_IS_NOT_AVAILABLE, seller.getName(),
                        e.getMessage()));
                }
            })
            .flatMap(List::stream)
            .toList();
        log.info("Received {} products for request {}.", products.size(), request);
        return products;
    }

    @Override
    public List<SearchResult> getSearchResults(String request, Seller seller) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new FirefoxDriver();

        List<SearchResult> results = new ArrayList<>();
        try {
            String url = seller.getSearchUrl() + request;
            webDriver.get(url);

            // TODO: check how avoid sleep
            Thread.sleep(1000);

            String pageSource = webDriver.getPageSource();

            Document doc = Jsoup.parse(pageSource);

            Elements productContainers = doc.select(String.format(DIV_STARTS_WITH, seller.getTitleClass()));

            for (org.jsoup.nodes.Element product : productContainers) {
                String price = createPrice(seller, product);

                if (checkEmptyString(price)) {
                    continue;
                }
                String name = createProductTitle(seller, product);

                String link = product.select(LINK).attr(HREF_ATTRIBUTE);

                results.add(buildSearchResult(seller.getName(), name, price, link));
            }

            log.info("Number of product containers of seller {} is {}.", seller.getName(), results.size());
        } finally {
            webDriver.quit();
        }

        return results;
    }

    private SearchResult buildSearchResult(String seller, String name, String price, String url) {
        return SearchResult.builder()
            .name(name)
            .seller(seller)
            .price(price)
            .url(url)
            .build();
    }

    private String createProductTitle(Seller seller, org.jsoup.nodes.Element product) {
        var parts = createTitleParts(seller, product);
        return createComplexTitle(parts, seller.getName());
    }

    private List<ComplexTitlePart> createTitleParts(Seller seller, org.jsoup.nodes.Element product) {
        var titles = seller.getTitleProductElements()
            .stream()
            .filter(title -> title.getSellerElementField() == SellerElementField.PRODUCT_TITLE)
            .toList();
        if (titles.isEmpty()) {
            throw new SellerParsingException(String.format(ERROR_PARSING_TITLE, seller.getName()));
        }
        return titles.stream()
            .map(title -> {
                var productTitle =
                    title.getElementType().any(SellerElementType.A, SellerElementType.SPAN)
                        ? product.select(title.getElementName()).text()
                        : product.select(String.format(DIV_CONTAINS, title.getElementName())).text();
                return ComplexTitlePart.builder()
                    .titlePart(productTitle)
                    .priority(title.getPriority())
                    .build();
            })
            .toList();
    }

    private String createComplexTitle(List<ComplexTitlePart> parts, String sellerName) {
        var onePartTitle = crateSeparateTitlePart(parts, ONE_ELEMENT);
        if (!checkEmptyString(onePartTitle)) {
            return onePartTitle;
        }
        var mainPart = crateSeparateTitlePart(parts, MAIN_ELEMENT);
        if (checkEmptyString(mainPart)) {
            throw new SellerParsingException(String.format(ERROR_PARSING_NO_ONE_OR_MAIN_PRIORITY, sellerName));
        }
        var middlePart = crateSeparateTitlePart(parts, ADDITIONAL_INCLUDED_ELEMENT_FIRST);
        var endPart = crateSeparateTitlePart(parts, ADDITIONAL_INCLUDED_ELEMENT_SECOND);
        return mainPart + SEMICOLON + SPACE + middlePart + SPACE + endPart;
    }

    private String crateSeparateTitlePart(List<ComplexTitlePart> parts, SellerElementPriority priority) {
        var titlePart = "";
        var additionalMiddle = parts.stream()
            .filter(part -> part.getPriority() == priority)
            .findFirst();
        if (additionalMiddle.isPresent()) {
            titlePart = additionalMiddle.get().getTitlePart();
        }
        return titlePart;

    }

    private String createPrice(Seller seller, org.jsoup.nodes.Element product) {
        var prices = seller.getPrices()
            .stream()
            .filter(price -> price.getSellerElementField() == SellerElementField.PRODUCT_PRICE)
            .toList();
        if (prices.isEmpty()) {
            throw new ResourceNotFoundException(String.format(ERROR_PARSING_PRICE, seller.getName()));
        }
        var specificPrice = prices.stream()
            .filter(item -> item.getPriority() == SellerElementPriority.ADDITIONAL_ELEMENT_EXCLUDING_MAIN)
            .findFirst();
        if (specificPrice.isPresent()) {
            return product.select(specificPrice.get().getElementName()).text();
        }
        var price = prices.stream()
            .filter(item -> item.getPriority().any(SellerElementPriority.MAIN_ELEMENT, ONE_ELEMENT))
            .findFirst();
        if (price.isEmpty()) {
            throw new ResourceNotFoundException(String.format(ERROR_PARSING_PRICE, seller.getName()));
        }
        return product.select(price.get().getElementName()).text();
    }
}

