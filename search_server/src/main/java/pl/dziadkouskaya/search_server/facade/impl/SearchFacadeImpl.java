package pl.dziadkouskaya.search_server.facade.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.dziadkouskaya.search_server.entity.dto.EqualProductNames;
import pl.dziadkouskaya.search_server.entity.dto.SearchResult;
import pl.dziadkouskaya.search_server.entity.params.SearchParam;
import pl.dziadkouskaya.search_server.facade.SearchFacade;
import pl.dziadkouskaya.search_server.service.ProductComparisonService;
import pl.dziadkouskaya.search_server.service.SearchService;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class SearchFacadeImpl implements SearchFacade {
    private final SearchService searchService;
    private final ProductComparisonService comparisonService;

    public SearchFacadeImpl(SearchService searchService, ProductComparisonService comparisonService) {
        this.searchService = searchService;
        this.comparisonService = comparisonService;
    }

    @Override
    public List<EqualProductNames> findResults(String param) throws IOException, InterruptedException {
        var initialData = searchService.getSellerProducts(param);
        return getComparedData(initialData);
    }

    @Override
    public List<EqualProductNames> findProductsFromSellers(SearchParam param) throws IOException, InterruptedException {
        var initialData = searchService.getSearchResultsFromSellers(param);
        return getComparedData(initialData);
    }

    private List<EqualProductNames> getComparedData(List<List<SearchResult>> initialData) {
        var comparedData = comparisonService.findEqualProducts(initialData);
        log.info("ComparedData list is {}.", comparedData.size());
        var equalsProducts = comparedData.stream()
            .filter(product -> product.getDescriptions().size() > 1)
            .count();
        log.info("The number of entities have more than one description is {}.", equalsProducts);
        return comparedData;
    }
}
