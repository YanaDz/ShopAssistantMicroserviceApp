package pl.dziadkouskaya.graphql.service;


import pl.dziadkouskaya.graphql.entity.dto.ProductDto;
import pl.dziadkouskaya.graphql.entity.filters.ProductFilter;
import pl.dziadkouskaya.graphql.entity.param.ProductParams;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<ProductDto> getAll();

    List<ProductDto> getProductsByFields(ProductFilter filter);

    ProductDto getProductById(UUID id);

    ProductDto createProduct(ProductParams input);

    ProductDto updateProduct(UUID id, ProductParams input);

    Boolean deleteProduct(UUID id);

}
