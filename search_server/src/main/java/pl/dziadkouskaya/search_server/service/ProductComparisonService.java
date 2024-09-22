package pl.dziadkouskaya.search_server.service;

import pl.dziadkouskaya.search_server.entity.dto.EqualProductNames;
import pl.dziadkouskaya.search_server.entity.dto.SearchResult;

import java.util.List;

public interface ProductComparisonService {
    List<EqualProductNames> findEqualProducts(List<List<SearchResult>>initialResults);
}
