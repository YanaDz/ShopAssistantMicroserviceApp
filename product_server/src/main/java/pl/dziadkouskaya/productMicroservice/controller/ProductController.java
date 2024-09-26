package pl.dziadkouskaya.productMicroservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dziadkouskaya.productMicroservice.entity.dto.ProductDto;
import pl.dziadkouskaya.productMicroservice.entity.filters.ProductFilter;
import pl.dziadkouskaya.productMicroservice.entity.param.ProductParams;
import pl.dziadkouskaya.productMicroservice.service.ProductService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = ProductController.PATH)
public class ProductController {
    public static final String PATH = "/product";

    private final ProductService productService;

    @GetMapping(consumes = "application/json")
    public List<ProductDto> getProducts() {
        return productService.getAll();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ProductDto createProduct(@RequestBody ProductParams params) {
        return productService.createProduct(params);
    }

    @GetMapping(value = "/search", consumes = "application/json")
    public List<ProductDto> getProductsByFilter(@RequestBody ProductFilter filter) {
        return productService.getProductsByFields(filter);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ProductDto updateProduct(@PathVariable UUID id, @RequestBody ProductParams input) {
        return productService.updateProduct(id, input);
    }

    @DeleteMapping(value = "/{id}", consumes = "application/json")
    public Boolean deleteProduct(@PathVariable UUID id) {
        return productService.deleteProduct(id);
    }


}
