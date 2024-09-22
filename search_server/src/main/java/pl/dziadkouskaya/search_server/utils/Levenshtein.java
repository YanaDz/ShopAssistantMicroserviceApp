package pl.dziadkouskaya.search_server.utils;

import org.apache.commons.text.similarity.LevenshteinDistance;

import static java.util.Objects.isNull;

public class Levenshtein {
    private static LevenshteinDistance levenshtein;

    private Levenshtein() {

    }

    public static LevenshteinDistance getInstance() {
        if (isNull(levenshtein)) {
            levenshtein = new LevenshteinDistance();
        }
        return levenshtein;
    }

}
