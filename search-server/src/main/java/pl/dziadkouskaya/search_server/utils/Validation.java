package pl.dziadkouskaya.search_server.utils;

import static java.util.Objects.isNull;

public class Validation {
    public static boolean checkEmptyString(String string) {
        return isNull(string) || string.isEmpty() || string.isBlank();
    }

    public static boolean checkStringWithLetters(String string) {
        return string.matches(".*[a-zA-Z]+.*");
    }
}
