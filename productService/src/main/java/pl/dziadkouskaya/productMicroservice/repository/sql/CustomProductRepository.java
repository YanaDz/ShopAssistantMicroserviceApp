package pl.dziadkouskaya.graphql.repository.sql;

import pl.dziadkouskaya.graphql.entity.Product;
import pl.dziadkouskaya.graphql.entity.enums.ProductType;

import java.util.List;

public interface CustomProductRepository {
    List<Product> findByFilters(String name, String firm, Integer type, String productVersion);
}
