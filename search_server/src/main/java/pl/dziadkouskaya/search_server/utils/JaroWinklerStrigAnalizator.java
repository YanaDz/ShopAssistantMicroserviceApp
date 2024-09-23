package pl.dziadkouskaya.search_server.utils;

import org.apache.commons.text.similarity.JaroWinklerSimilarity;

import static java.util.Objects.isNull;

public class JaroWinklerStrigAnalizator {
    private static JaroWinklerSimilarity jaroWinklerSimilarity;

    public JaroWinklerStrigAnalizator() {
    }

    public static JaroWinklerSimilarity getInstance() {
        if (isNull(jaroWinklerSimilarity)) {
            jaroWinklerSimilarity = new JaroWinklerSimilarity();
        }
        return jaroWinklerSimilarity;
    }
}
