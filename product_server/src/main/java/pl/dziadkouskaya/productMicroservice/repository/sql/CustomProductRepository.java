package pl.dziadkouskaya.productMicroservice.repository.sql;

import pl.dziadkouskaya.productMicroservice.entity.Product;
import pl.dziadkouskaya.productMicroservice.entity.enums.ProductType;

import java.util.List;

public interface CustomProductRepository {
    List<Product> findByFilters(String name, String firm, Integer type, String productVersion);
}
