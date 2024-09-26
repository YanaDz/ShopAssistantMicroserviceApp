package pl.dziadkouskaya.productMicroservice.utils;

import static pl.dziadkouskaya.productMicroservice.utils.Constants.PERCENT;

public class StringOperations {
    public static String createSearchingRequest(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(PERCENT);
        stringBuilder.append(string.toLowerCase());
        stringBuilder.append(PERCENT);
        return stringBuilder.toString();
    };
}
