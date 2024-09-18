package pl.dziadkouskaya.search_server.entity.enums;

public enum SellerElementField implements BaseEnum<SellerElementField> {
    PRODUCT_TITLE(0),
    PRODUCT_PRICE(1);

    private int code;

    SellerElementField(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }
}
