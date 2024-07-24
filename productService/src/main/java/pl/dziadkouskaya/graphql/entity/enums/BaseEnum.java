package pl.dziadkouskaya.graphql.entity.enums;

public interface BaseEnum<E extends BaseEnum<E>> {
    int getCode();

    default boolean any(E first, E... values) {
        if (first == this) {
            return true;
        }
        for (E val : values) {
            if (val == this) {
                return true;
            }
        }
        return false;
    }

    default boolean none(E first, E... values) {
        if (first == this) {
            return false;
        }
        for (E val : values) {
            if (val == this) {
                return false;
            }
        }
        return true;
    }
}
