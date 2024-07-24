package pl.dziadkouskaya.graphql.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dziadkouskaya.graphql.entity.Product;
import pl.dziadkouskaya.graphql.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = ProductController.PATH)
public class ProductController {
    public static final String PATH = "/product";

    private final ProductService productService;

    @GetMapping
    public List<Product> getProducts() {
        return null;
    }


}
