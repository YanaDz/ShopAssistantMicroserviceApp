package pl.dziadkouskaya.search_server.service;

import pl.dziadkouskaya.search_server.entity.Seller;
import pl.dziadkouskaya.search_server.entity.dto.SearchResult;


import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface SearchService {
    List<SearchResult> getSellerProducts(String request);
    List<SearchResult> getSearchResults(String request, Seller seller) throws IOException, InterruptedException;
    List<SearchResult> getSellerProducts(String request, UUID sellerId) throws IOException, InterruptedException;

}
