package pl.dziadkouskaya.search_server.service;

import pl.dziadkouskaya.search_server.entity.Seller;
import pl.dziadkouskaya.search_server.entity.dto.EqualProductNames;
import pl.dziadkouskaya.search_server.entity.dto.SearchResult;
import pl.dziadkouskaya.search_server.entity.params.SearchParam;


import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public interface SearchService {
    List<List<SearchResult>> getSellerProducts(String request) throws ExecutionException, InterruptedException;
    List<SearchResult> getSearchResultsFromSellers(String request, Seller sellerId, int waitTime) throws IOException, InterruptedException;
    List<SearchResult> getSearchResultsFromSellers(String request, Seller seller) throws IOException, InterruptedException;
    List<SearchResult> getSellerProducts(String request, UUID sellerId) throws IOException, InterruptedException;
    List<List<SearchResult>> getSearchResultsFromSellers(SearchParam param) throws IOException, InterruptedException, ExecutionException;
    List<EqualProductNames> mapSearchResultsToEqualProducts(List<SearchResult> initialData);
}
