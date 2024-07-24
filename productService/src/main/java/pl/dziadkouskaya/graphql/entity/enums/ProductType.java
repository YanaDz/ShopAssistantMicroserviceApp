package pl.dziadkouskaya.graphql.entity.enums;

import lombok.Getter;
import pl.dziadkouskaya.graphql.entity.enums.converter.BaseConverter;

import java.util.HashMap;
import java.util.Map;

public enum ProductType implements BaseEnum<ProductType>{
    HOME_APPLIANCE(0, "Home Appliance"),
    KITCHEN_APPLIANCE(1, "Kitchen Appliance"),
    SMARTPHONES(2, "Smartphones"),
    COMPUTERS(3, "Computers");

    private final int code;

    @Getter
    private final String productTypeName;

    ProductType(int code, String productTypeName) {
        this.code = code;
        this.productTypeName = productTypeName;
    }

    @Override
    public int getCode() {
        return code;
    }

    private static final Map<String, Integer> BY_NAME_CODE = new HashMap<>();

    static {
        for (ProductType productType : values()) {
            BY_NAME_CODE.put(productType.productTypeName, productType.code);
        }
    }

    public static Integer codeByName(String name) {
        return BY_NAME_CODE.get(name);
    }


    public static class Converter extends BaseConverter<ProductType> {
        public Converter() {
            super(ProductType.class);
        }
    }
}
