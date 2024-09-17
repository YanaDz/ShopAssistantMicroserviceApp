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
import pl.dziadkouskaya.search_server.entity.dto.SearchResult;
import pl.dziadkouskaya.search_server.exception.ShopNotAvailableException;
import pl.dziadkouskaya.search_server.repository.SellerRepository;
import pl.dziadkouskaya.search_server.service.SearchService;

import java.util.ArrayList;
import java.util.List;

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
                    throw new ShopNotAvailableException(String.format("Shop %s is not available now", seller.getName()));
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

            // Wait for the page to fully load
            Thread.sleep(1000);

            // Get the page source after it has been fully rendered
            String pageSource = webDriver.getPageSource();

            // Use Jsoup to parse the page source
            Document doc = Jsoup.parse(pageSource);

            // Select all product containers
            Elements productContainers = doc.select(String.format("div[class^='%s']", seller.getTitleClass()));

            // Process the elements
            for (org.jsoup.nodes.Element product : productContainers) {
                // Extract product name
                String name = product.select(String.format("div[class*='%s']", seller.getTitleProductElement())).text();

                // Extract product URL
                String link = product.select("a").attr("href");

                // Extract product price
                String price = product.select(seller.getPriceClass()).text();

                // Add result to list
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
}
