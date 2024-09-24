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
import pl.dziadkouskaya.search_server.entity.params.SearchParam;
import pl.dziadkouskaya.search_server.exception.ResourceNotFoundException;
import pl.dziadkouskaya.search_server.exception.SellerParsingException;
import pl.dziadkouskaya.search_server.exception.ShopNotAvailableException;
import pl.dziadkouskaya.search_server.service.SearchService;
import pl.dziadkouskaya.search_server.service.SellerService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static pl.dziadkouskaya.search_server.entity.enums.SellerElementField.PRODUCT_PRICE;
import static pl.dziadkouskaya.search_server.entity.enums.SellerElementPriority.ADDITIONAL_ELEMENT_EXCLUDING_MAIN;
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
import static pl.dziadkouskaya.search_server.utils.Constants.PRODUCT_SEARCH_DEFAULT_WAIT;
import static pl.dziadkouskaya.search_server.utils.Constants.SHOP_CONNECTION_IS_NOT_AVAILABLE;
import static pl.dziadkouskaya.search_server.utils.Constants.SPACE;
import static pl.dziadkouskaya.search_server.utils.Validation.checkEmptyString;
import static pl.dziadkouskaya.search_server.utils.Validation.checkStringWithLetters;

@Service
@Slf4j
public class SearchServiceImpl implements SearchService {
    private final SellerService sellerService;

    public SearchServiceImpl(SellerService sellerService) {
        this.sellerService = sellerService;
    }


    @Override
    public List<List<SearchResult>> getSellerProducts(String request) {
        var allSellers = sellerService.getAllSellers();
        log.info("Found {} sellers.", allSellers.size());
        var products = allSellers.parallelStream()
            .peek(seller -> log.info("Start getting results from seller {}.", seller.getName()))
            .map(seller -> {
                try {
                    return getSearchResults(request, seller);
                } catch (InterruptedException | IOException e) {
                    throw new ShopNotAvailableException(String.format(SHOP_CONNECTION_IS_NOT_AVAILABLE, seller.getName(),
                        e.getMessage()));
                }
            })
//            .flatMap(List::stream)
            .toList();
        log.info("Received {} products for request {}.", products.size(), request);
        return products;
    }


    @Override
    public List<SearchResult> getSearchResults(String request, Seller seller, int waitTime) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new FirefoxDriver();

        List<SearchResult> results = new ArrayList<>();
        try {
            String url = seller.getSearchUrl() + request;
            webDriver.get(url);

            // TODO: check how avoid sleep
            Thread.sleep(waitTime);

            String pageSource = webDriver.getPageSource();

            Document doc = Jsoup.parse(pageSource);

            Elements productContainers = doc.select(String.format(DIV_STARTS_WITH, seller.getTitleClass()));

            for (org.jsoup.nodes.Element product : productContainers) {
                String price = createPrice(seller, product);

                String name = createProductTitle(seller, product);

                if (checkEmptyString(price) || checkEmptyString(name)) {
                    continue;
                }

                String link = product.select(LINK).attr(HREF_ATTRIBUTE);

                results.add(buildSearchResult(seller.getName(), name, price, link));
            }

            log.info("Number of product containers of seller {} is {}.", seller.getName(), results.size());
        } finally {
            webDriver.quit();
        }

        return results;
    }

    @Override
    public List<SearchResult> getSearchResults(String request, Seller seller) throws IOException, InterruptedException {
        return getSearchResults(request, seller, PRODUCT_SEARCH_DEFAULT_WAIT);
    }

    @Override
    public List<SearchResult> getSellerProducts(String request, UUID sellerId) throws IOException, InterruptedException {
        log.info("Start getting results from seller {}.", sellerId);
        var seller = sellerService.getSellerById(sellerId);
        List<SearchResult> products = new ArrayList<>();
        try {
            products = getSearchResults(request, seller);
        } catch (InterruptedException e) {
            throw new ShopNotAvailableException(String.format(SHOP_CONNECTION_IS_NOT_AVAILABLE, seller.getName(),
                e.getMessage()));
        }
        log.info("Received {} products for request {} and seller {}.", products.size(), request, seller.getName());
        return products;
    }

    @Override
    public List<SearchResult> getSearchResults(SearchParam param) throws IOException, InterruptedException {
        var seller = sellerService.getSellerById(param.getSellerId());
        var results = getSearchResults(param.getSearchRequest(), seller, param.getTimeWait());
        log.info("{} products received fron seller {}.", results.size(), seller.getName());
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
                if (!checkStringWithLetters((productTitle))) {
                    return null;
                }
                return ComplexTitlePart.builder()
                    .titlePart(productTitle)
                    .priority(title.getPriority())
                    .build();
            })
            .filter(Objects::nonNull)
            .toList();
    }

    private String createComplexTitle(List<ComplexTitlePart> parts, String sellerName) {
        var onePartTitle = crateSeparateTitlePart(parts, ONE_ELEMENT);
        if (!checkEmptyString(onePartTitle)) {
            return onePartTitle;
        }
        var mainPart = crateSeparateTitlePart(parts, MAIN_ELEMENT);
        var middlePart = crateSeparateTitlePart(parts, ADDITIONAL_INCLUDED_ELEMENT_FIRST);
        var endPart = crateSeparateTitlePart(parts, ADDITIONAL_INCLUDED_ELEMENT_SECOND);
        return mainPart + SPACE + middlePart + SPACE + endPart;
    }

    private String crateSeparateTitlePart(List<ComplexTitlePart> parts, SellerElementPriority priority) {
        var titlePart = "";
        var additionalMiddle = parts.stream()
            .filter(part -> part.getPriority() == priority && !part.getTitlePart().isBlank())
            .findFirst();
        if (additionalMiddle.isPresent()) {
            titlePart = additionalMiddle.get().getTitlePart();
        }
        return titlePart;

    }

    private String createPrice(Seller seller, org.jsoup.nodes.Element product) {
        var prices = seller.getPrices()
            .stream()
            .filter(price -> price.getSellerElementField() == PRODUCT_PRICE)
            .toList();
        if (prices.isEmpty()) {
            throw new ResourceNotFoundException(String.format(ERROR_PARSING_PRICE, seller.getName()));
        }
        var specificPrice = prices.stream()
            .filter(item -> item.getPriority() == ADDITIONAL_ELEMENT_EXCLUDING_MAIN)
            .findFirst();
        if (specificPrice.isPresent()) {
            return product.select(specificPrice.get().getElementName()).text();
        }
        var price = prices.stream()
            .filter(item -> item.getPriority().any(MAIN_ELEMENT, ONE_ELEMENT))
            .findFirst();
        if (price.isEmpty()) {
            throw new ResourceNotFoundException(String.format(ERROR_PARSING_PRICE, seller.getName()));
        }
        return product.select(price.get().getElementName()).text();
    }
}
