package pl.dziadkouskaya.search_server.utils;

import static java.util.Objects.isNull;

public class Validation {
    public static boolean checkEmptyString(String string) {
        return isNull(string) || string.isEmpty();
    }
}
