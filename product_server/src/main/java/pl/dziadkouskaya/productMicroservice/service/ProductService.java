package pl.dziadkouskaya.productMicroservice.service;


import pl.dziadkouskaya.productMicroservice.entity.dto.ProductDto;
import pl.dziadkouskaya.productMicroservice.entity.filters.ProductFilter;
import pl.dziadkouskaya.productMicroservice.entity.param.ProductParams;

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
