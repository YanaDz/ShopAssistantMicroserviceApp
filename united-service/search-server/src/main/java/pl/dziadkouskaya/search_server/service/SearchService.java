package pl.dziadkouskaya.search_server.service;

import pl.dziadkouskaya.search_server.entity.Seller;
import pl.dziadkouskaya.search_server.entity.dto.SearchResult;


import java.io.IOException;
import java.util.List;

public interface SearchService {
    List<SearchResult> getSearchResults(Seller seller, String request);
    List<SearchResult> getSearchResults(String request) throws IOException;

}
