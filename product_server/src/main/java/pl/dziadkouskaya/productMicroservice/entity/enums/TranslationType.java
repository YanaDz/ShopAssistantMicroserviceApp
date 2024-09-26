package pl.dziadkouskaya.productMicroservice.entity.enums;

import pl.dziadkouskaya.productMicroservice.entity.enums.converter.BaseConverter;

import java.util.HashMap;
import java.util.Map;

public enum TranslationType implements BaseEnum<TranslationType> {
    MAIN(0),
    ADDITIONAL(1),
    NOT_DETERMINED(2);

    private int code;

    TranslationType(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return 0;
    }

    public static class Converter extends BaseConverter<TranslationType> {
        public Converter() {
            super(TranslationType.class);
        }
    }
}
