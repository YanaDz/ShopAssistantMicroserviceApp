package pl.dziadkouskaya.graphql.entity.enums;

import lombok.Getter;

public enum Location implements BaseEnum<Location> {
    EN(0, "English"),
    PL(1, "Polish"),
    RU(2, "Russian");


    private final int code;

    @Getter
    private final String language;

    Location(int code, String language) {
        this.code = code;
        this.language = language;
    }

    @Override
    public int getCode() {
        return 0;
    }
}
