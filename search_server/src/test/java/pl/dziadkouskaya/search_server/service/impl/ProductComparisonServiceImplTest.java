package pl.dziadkouskaya.search_server.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import pl.dziadkouskaya.search_server.entity.dto.SearchResult;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static pl.dziadkouskaya.search_server.utils_classes.TestUtils.buildDefaultProduct;
import static pl.dziadkouskaya.search_server.utils_classes.TestUtils.buildDefaultSeller;

class ProductComparisonServiceImplTest {

    @InjectMocks
    private ProductComparisonServiceImpl productComparisonService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @MethodSource("paramsForFindEqualProducts")
    void testFindEqualProducts(List<List<SearchResult>> initialList, int allResultsSize, int dublicatedProducts) {
        var results = productComparisonService.findEqualProducts(initialList);
        assertEquals(allResultsSize, results.size());
        var resultInDiffrerentShops = results.stream()
            .filter(product -> product.getDescriptions().size() > 1)
            .count();
        assertEquals(dublicatedProducts, resultInDiffrerentShops);
    }

    static Stream<Arguments> paramsForFindEqualProducts() {
        var seller1 = buildDefaultSeller("Los Pollos Hermanos ");
        var seller2 = buildDefaultSeller("The Leaky Cauldron");
        var seller3 = buildDefaultSeller("Central Perk");
        var seller1UniqueProduct = buildDefaultProduct("Chicken Cake", seller1, 100);
        var seller1SameProduct = buildDefaultProduct("Burger with salat and  avocado", seller1, 200);
        var seller2UniqueProduct = buildDefaultProduct("Chocolate Frog", seller2, 1000);
        var seller3UniqueProduct = buildDefaultProduct("Cruasan", seller3, 99);
        var seller3SameProduct = buildDefaultProduct("Salat burger with avocado", seller3, 200);
        var seller3SameProduct2 = buildDefaultProduct("Chocolate Frog from Harry Potter", seller3, 1100);

        var initialList = List.of(List.of(seller1SameProduct, seller1UniqueProduct), List.of(seller2UniqueProduct),
            List.of(seller3SameProduct, seller3UniqueProduct, seller3SameProduct2));

        return Stream.of(
            arguments(initialList, 4, 2)
        );
    }

    @ParameterizedTest
    @MethodSource("paramsForAreProductsSimilar")
    void testAreProductsSimilarLevenshtein(String string1, String string2, boolean expectedResult) {
        var result = productComparisonService.areProductsSimilarLevenstein(string1, string2);
        assertEquals(expectedResult, result);
    }


    @ParameterizedTest
    @MethodSource("paramsForAreProductsSimilar")
    void testAreProductsSimilarSoundex(String string1, String string2, boolean expectedResult) {
        var result = productComparisonService.areProductsSimilarSoundex(string1, string2);
        assertEquals(expectedResult, result);
    }

    @ParameterizedTest
    @MethodSource("paramsForAreProductsSimilar")
    void testAreProductsSimilarJaroWinkler(String string1, String string2, boolean expectedResult) {
        var result = productComparisonService.areProductsSimilarJaroWinkler(string1, string2);
        assertEquals(expectedResult, result);
    }

    static Stream<Arguments> paramsForAreProductsSimilar() {
        return Stream.of(
            arguments("iphone 16", "Apple iPhone 16", true),
            arguments("Maybelline New York Lash Sensational Sky High Tusz do rzęs", "MAYBELLINE Lash Sensational Sky High tusz do rzęs",
                true),
            arguments("Burberry Goddess Woda perfumowana", "Burberry Goddess woda perfumowana flakon napełnialny dla kobiet", true),
            arguments("Chanel Chance Eau Tendre woda toaletowa dla kobiet", "Chanel Chance Eau Vive", false),
            arguments("Chanel Chance Eau Tendre", "Chanel Chance Eau Vive", false),
            arguments("Piekarnik elektryczny Electrolux EOF5C50BV Biały", "Piekarnik ELECTROLUX EOF5C50BV", true),
            arguments("Piekarnik ELECTROLUX EOF5C50BV", "Piekarnik Samsung", false)
        );
    }
}