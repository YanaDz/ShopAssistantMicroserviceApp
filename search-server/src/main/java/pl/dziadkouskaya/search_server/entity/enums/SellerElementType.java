package pl.dziadkouskaya.search_server.entity.enums;

public enum SellerElementType implements BaseEnum<SellerElementType>{
    DIV(0),
    SPAN(1),
    A(2),
    CUSTOM_TAG(3);

    private int code;

    SellerElementType(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }
}
