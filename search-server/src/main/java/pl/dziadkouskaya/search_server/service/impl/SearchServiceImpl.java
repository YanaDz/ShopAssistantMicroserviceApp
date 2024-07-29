package pl.dziadkouskaya.search_server.service.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import pl.dziadkouskaya.search_server.entity.Seller;
import pl.dziadkouskaya.search_server.entity.dto.SearchResult;
import pl.dziadkouskaya.search_server.repository.SellerRepository;
import pl.dziadkouskaya.search_server.service.SearchService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class SearchServiceImpl implements SearchService {
    private final SellerRepository sellerRepository;

    public SearchServiceImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public List<SearchResult> getSearchResults(Seller seller, String request) {
        return List.of();
    }

    @Override
    public List<SearchResult> getSearchResults(String request) throws IOException {
//
//        String searchUrl = "https://mediamarkt.pl/pl/search.html?query=" + request;
//        Document doc = Jsoup.connect(searchUrl).get();
//        Element productElement = doc.selectFirst(".sc-b0c0d999-0 hHtrNC"); // Update the CSS query based on the actual website structure
//
//        if (productElement != null) {
//            String fullProductName = productElement.selectFirst(".sc-3f2da4f5-0 fLePRG").text();
////            String productProducer = productElement.selectFirst(".product-brand").text();
////            String productFeatures = productElement.selectFirst(".product-features").text();
//            String price = productElement.selectFirst(".sc-3f2da4f5-0 dievjx sc-dd1a61d2-2 efAprc").text();
//
//            var searchResults = SearchResult.builder()
//                    .name(fullProductName)
//                    .price(price)
//                    .build();
//
//            return List.of(searchResults);
//        }
        String name = "Telewizor QLED Samsung QE75Q60C 75";
        var ceneo = sellerRepository.findById(UUID.fromString("ab4a818f-85b5-4204-aac0-565e63ceebab")).get();
        var rtv = sellerRepository.findById(UUID.fromString("87c19627-f579-4e07-a5bd-0e6f91468ed2")).get();
        var mediamarkt = sellerRepository.findById(UUID.fromString("f2db4525-b593-4965-bdf2-d2ef7ae67c97")).get();
        var productCeneo = buildSearchResult(ceneo, name, "3856");
        var productRtv = buildSearchResult(rtv, name, "4999");
        var productMedia = buildSearchResult(mediamarkt, name, "6799");

        return List.of(productCeneo, productRtv, productMedia);

    }

    private SearchResult buildSearchResult(Seller seller, String name, String price) {
        return SearchResult.builder()
                .name(name)
                .seller(seller)
                .price(price)
                .build();
    }
}
