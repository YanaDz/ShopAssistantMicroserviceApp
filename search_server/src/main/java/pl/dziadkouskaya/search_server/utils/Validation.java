package pl.dziadkouskaya.search_server.utils;

import pl.dziadkouskaya.search_server.entity.dto.SearchResult;

import java.util.List;

import static java.util.Objects.isNull;
import static pl.dziadkouskaya.search_server.utils.Constants.MIN_LIST_SIZE;

public class Validation {
    public static boolean checkEmptyString(String string) {
        return isNull(string) || string.isEmpty() || string.isBlank();
    }

    public static boolean checkStringWithLetters(String string) {
        return string.matches(".*[a-zA-Z]+.*");
    }

    public static <T>  boolean isContainsOneElement(List<T> list) {
        return list.size() == MIN_LIST_SIZE;
    }

    public static <T> boolean isAllListsAreEmpty(List<List<T>> initialResults) {
        return initialResults.stream()
            .allMatch(List::isEmpty);
    }
}
