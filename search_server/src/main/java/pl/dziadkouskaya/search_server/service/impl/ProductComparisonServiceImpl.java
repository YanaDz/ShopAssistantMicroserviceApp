package pl.dziadkouskaya.search_server.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.dziadkouskaya.search_server.entity.dto.EqualProductNames;
import pl.dziadkouskaya.search_server.entity.dto.SearchResult;
import pl.dziadkouskaya.search_server.service.ProductComparisonService;
import pl.dziadkouskaya.search_server.utils.Levenshtein;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static pl.dziadkouskaya.search_server.utils.Constants.DISTANCE_THRESHOLD;


@Service
@Slf4j
public class ProductComparisonServiceImpl implements ProductComparisonService {
    @Override
    public List<EqualProductNames> findEqualProducts(List<List<SearchResult>> initialResults) {
        var itemsSource = initialResults.get(0);
        initialResults = initialResults.subList(1, initialResults.size() - 1);
        var comparableResults = initialResults.stream()
            .flatMap(List::stream)
            .toList();
        return itemsSource.stream()
            .map(product -> compareProductsOnIteration(product, comparableResults))
            .collect(Collectors.toList());
    }

    private boolean areProductsSimilar(String product1, String product2) {
        int distance = Levenshtein.getInstance().apply(product1.toLowerCase(), product2.toLowerCase());
        return distance <= DISTANCE_THRESHOLD;
    }

    private EqualProductNames buildEqualProductNames(SearchResult result) {
        var equalProductName = EqualProductNames.builder()
            .productName(result.getName())
            .descriptions(new ArrayList<>())
            .build();
        equalProductName.addSameProduct(result);
        return equalProductName;
    }

    private EqualProductNames addEqualProductName(SearchResult searchResult, EqualProductNames equalProduct) {
        equalProduct.addSameProduct(searchResult);
        return equalProduct;
    }

    private EqualProductNames compareProductsOnIteration(SearchResult item, List<SearchResult> list) {
        var initialEqualProduct = buildEqualProductNames(item);
        item.setCompared(true);

        list.forEach(product -> {
            if (!product.isCompared() && areProductsSimilar(item.getName(), product.getName())) {
                initialEqualProduct.addSameProduct(product);
                product.setCompared(true);
            }
        });
        return initialEqualProduct;
    }
}
