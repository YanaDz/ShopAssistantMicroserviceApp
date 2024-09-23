package pl.dziadkouskaya.search_server.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.dziadkouskaya.search_server.entity.dto.EqualProductNames;
import pl.dziadkouskaya.search_server.entity.dto.SearchResult;
import pl.dziadkouskaya.search_server.service.ProductComparisonService;
import pl.dziadkouskaya.search_server.utils.JaroWinklerStrigAnalizator;
import pl.dziadkouskaya.search_server.utils.LevenshteinStringAnalizator;
import pl.dziadkouskaya.search_server.utils.SoundexStringAnalizator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static pl.dziadkouskaya.search_server.utils.Constants.JARO_WINKLER_SCORE_LIMIT;
import static pl.dziadkouskaya.search_server.utils.Constants.LEVENSHTEIN_DISTANCE_THRESHOLD;


@Service
@Slf4j
public class ProductComparisonServiceImpl implements ProductComparisonService {
    @Override
    public List<EqualProductNames> findEqualProducts(List<List<SearchResult>> initialResults) {
        List<EqualProductNames> results = new ArrayList<>();

        var index = findFirstNonEmptyListIndex(initialResults);
        var lastIndex = initialResults.size() - 1;

        while (index <= lastIndex && hasUncomparedProducts(initialResults.get(index))) {
            var currentList = initialResults.get(index);

            int finalIndex = index;
            currentList.stream()
                .filter(product -> !product.isCompared())
                .forEach(product -> {
                    var equalProduct = buildEqualProductNames(product);
                    product.setCompared(true);
                    compareWithRemainingLists(equalProduct, initialResults, finalIndex + 1);
                    results.add(equalProduct);
                });

            index++;
        }

        handleLastListUncomparedProducts(initialResults, lastIndex, results);

        return results;
    }

    private int findFirstNonEmptyListIndex(List<List<SearchResult>> lists) {
        return IntStream.range(0, lists.size())
            .filter(i -> !lists.get(i).isEmpty())
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("No non-empty list found."));
    }

    private boolean hasUncomparedProducts(List<SearchResult> products) {
        return products.stream().anyMatch(product -> !product.isCompared());
    }

    private void compareWithRemainingLists(EqualProductNames initialProduct, List<List<SearchResult>> allResults, int startIndex) {
        IntStream.range(startIndex, allResults.size())
            .forEach(i -> compareProductsOnIteration(initialProduct, allResults.get(i)));
    }

    private void handleLastListUncomparedProducts(List<List<SearchResult>> initialResults, int lastIndex, List<EqualProductNames> results) {
        List<SearchResult> lastList = initialResults.get(lastIndex);
        if (hasUncomparedProducts(lastList)) {
            List<EqualProductNames> uniqueProducts = lastList.stream()
                .filter(product -> !product.isCompared())
                .map(this::buildEqualProductNames)
                .toList();

            log.info("The number of unique products is {}.", uniqueProducts.size());
            results.addAll(uniqueProducts);
        }
    }

    @Override
    public boolean areProductsSimilarLevenstein(String product1, String product2) {
        int distance = LevenshteinStringAnalizator.getInstance().apply(product1.toLowerCase(), product2.toLowerCase());
        return distance <= LEVENSHTEIN_DISTANCE_THRESHOLD;
    }

    @Override
    public boolean areProductsSimilarSoundex(String product1, String product2) {
        var soundex = SoundexStringAnalizator.getInstance();
        return soundex.encode(product1).equals(soundex.encode(product2));
    }

    @Override
    public boolean areProductsSimilarJaroWinkler(String product1, String product2) {
        double score = JaroWinklerStrigAnalizator.getInstance().apply(product1, product2);
        return score >= JARO_WINKLER_SCORE_LIMIT;
    }

    @Override
    public EqualProductNames compareProductsOnIteration(EqualProductNames initialProduct, List<SearchResult> productList) {
        productList.stream()
            .filter(product -> !product.isCompared())
            .filter(product -> areProductsSimilarJaroWinkler(initialProduct.getProductName(), product.getName())
                && areProductsSimilarLevenstein(initialProduct.getProductName(), product.getName()))
            .forEach(product -> {
                initialProduct.addSameProduct(product);
                product.setCompared(true);
            });
        return initialProduct;
    }

    private EqualProductNames buildEqualProductNames(SearchResult product) {
        EqualProductNames equalProduct = EqualProductNames.builder()
            .productName(product.getName())
            .descriptions(new ArrayList<>())
            .build();
        equalProduct.addSameProduct(product);
        product.setCompared(true);
        return equalProduct;
    }
}
