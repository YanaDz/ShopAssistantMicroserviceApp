package pl.dziadkouskaya.graphql.repository.sql;

import pl.dziadkouskaya.graphql.entity.Product;

import java.util.List;

public interface CustomProductRepository {
    List<Product> findByFilters(String name, String firm, Integer type, String productVersion);
}
