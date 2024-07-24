package pl.dziadkouskaya.graphql.entity.filters;

import lombok.Data;

@Data
public class ProductFilter {
    private String name;

    private String productType;

    private String firm;

    private String productVersion;
}
