package pl.dziadkouskaya.search_server.utils_classes;

import pl.dziadkouskaya.search_server.entity.Seller;
import pl.dziadkouskaya.search_server.entity.dto.SearchResult;
import pl.dziadkouskaya.search_server.entity.enums.Location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class TestUtils {
    public static Seller buildDefaultSeller(String sellerName) {
        return Seller.builder()
            .id(UUID.randomUUID())
            .name(sellerName)
            .prices(new ArrayList<>())
            .titleProductElements(new ArrayList<>())
            .productUrl("product_url")
            .searchUrl("search_url")
            .location(Location.PL)
            .build();
    }

    public static SearchResult buildDefaultProduct(String productName, Seller seller, Integer price) {
        return SearchResult.builder()
            .name(productName)
            .seller(seller.getName())
            .price(price.toString())
            .url("product_url")
            .isCompared(false)
            .build();
    }

    public static void switchOffIsCompared(SearchResult ... results) {
        Arrays.stream(results)
            .forEach(result -> result.setCompared(false));
    }
}
