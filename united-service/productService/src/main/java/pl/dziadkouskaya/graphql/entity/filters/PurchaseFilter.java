package pl.dziadkouskaya.graphql.entity.filters;

import lombok.Data;

@Data
public class PurchaseFilter {
    private String product;

    private String seller;

    private String firm;
}
