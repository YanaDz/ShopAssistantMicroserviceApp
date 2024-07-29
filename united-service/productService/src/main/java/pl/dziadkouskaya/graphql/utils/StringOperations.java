package pl.dziadkouskaya.graphql.utils;

import static pl.dziadkouskaya.graphql.utils.Constants.PERCENT;

public class StringOperations {
    public static String createSearchingRequest(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(PERCENT);
        stringBuilder.append(string.toLowerCase());
        stringBuilder.append(PERCENT);
        return stringBuilder.toString();
    };
}
