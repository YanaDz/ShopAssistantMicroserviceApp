package pl.dziadkouskaya.search_server.service;

import pl.dziadkouskaya.search_server.entity.dto.EqualProductNames;
import pl.dziadkouskaya.search_server.entity.dto.SearchResult;

import java.util.List;

public interface ProductComparisonService {
    List<EqualProductNames> findEqualProducts(List<List<SearchResult>> initialResults);

    EqualProductNames compareProductsOnIteration(EqualProductNames initialEqualProduct, List<SearchResult> list);

    boolean areProductsSimilarLevenstein(String product1, String product2);
    boolean areProductsSimilarSoundex(String product1, String product2);

    boolean areProductsSimilarJaroWinkler(String product1, String product2);
}
