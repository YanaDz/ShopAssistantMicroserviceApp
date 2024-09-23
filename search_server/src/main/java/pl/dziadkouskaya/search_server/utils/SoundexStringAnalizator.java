package pl.dziadkouskaya.search_server.utils;

import org.apache.commons.codec.language.Soundex;

import static java.util.Objects.isNull;

public class SoundexStringAnalizator {
    private static Soundex soundex;

    public SoundexStringAnalizator() {
    }

    public static Soundex getInstance() {
        if (isNull(soundex)) {
            soundex = new Soundex();
        }
        return soundex;
    }
}
