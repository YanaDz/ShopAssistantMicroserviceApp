package pl.dziadkouskaya.search_server.entity.enums;

public enum SellerElementPriority implements BaseEnum<SellerElementPriority> {
    ONE_ELEMENT(0),
    MAIN_ELEMENT(1),
    ADDITIONAL_INCLUDED_ELEMENT_FIRST(2),
    ADDITIONAL_INCLUDED_ELEMENT_SECOND(3),
    ADDITIONAL_ELEMENT_EXCLUDING_MAIN(4);
    private final int code;

    SellerElementPriority(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }
}
