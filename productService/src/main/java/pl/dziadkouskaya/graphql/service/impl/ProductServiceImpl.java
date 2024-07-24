package pl.dziadkouskaya.graphql.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.dziadkouskaya.graphql.mapper.ProductMapper;
import pl.dziadkouskaya.graphql.repository.sql.ProductRepository;
import pl.dziadkouskaya.graphql.service.ProductService;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }
//
//    @Override
//    public List<Product> getAll() {
//        log.info("Get all products.");
//        var products = productRepository.findAll();
//        log.info("Returned {} products. ", products.size());
//        return products;
//    }
//
//    @Override
//    public List<Product> getProductsByFields(ProductFilter filter) {
//        log.info("Get products by params: {}.", filter);
//        var products =  productRepository.findByFilters(filter.getName(), filter.getFirm(),
//                ProductType.codeByName(filter.getProductType()), filter.getProductVersion());
//        log.info("Returned {} products. ", products.size());
//        return products;
//    }
//
//    @Override
//    public Optional<Product> getProductById(UUID id) {
//        log.info("Get product by ID: {}.", id);
//        return productRepository.findById(id);
//    }
//
//    @Override
//    public Product createProduct(CreateProductInput input) {
//        log.info("Get new product with input: {}.", input);
//        var initialProduct = productMapper.toEntity(input);
//        var product = productRepository.save(initialProduct);
//        log.info("Create entity with id {}.", product.getId());
//        return product;
//    }
//
//    @Override
//    public Product updateProduct(UpdateProductInput input) {
//        log.info("Update product with id {}.", input.getId());
//        if (productRepository.existsById(UUID.fromString(input.getId()))) {
//            var initialProduct = productRepository.findById(UUID.fromString(input.getId())).get();
//            var updatedProduct = updateProduct(input, initialProduct);
//            var product = productRepository.save(updatedProduct);
//            log.info("Update entity with id {}.", product.getId());
//            return product;
//        }
//        throw new ResourceNotFoundException();
//    }
//
//    @Override
//    public Boolean deleteProduct(UUID id) {
//        if (productRepository.existsById(id)) {
//            log.info("Product with id {} exists.", id);
//            productRepository.deleteById(id);
//            log.info("Product with id {} has been deleted.", id);
//            return true;
//        } else {
//            log.info("Product with id {} doesn't exist.", id);
//            return false;
//        }
//    }
//
//    private Product updateProduct(UpdateProductInput input, Product initialProduct) {
//        if (nonNull(input.getName())) {
//            initialProduct.setName(input.getName());
//        }
//        if ((nonNull(input.getProductType()))) {
//            initialProduct.setProductType(input.getProductType());
//        }
//        if (nonNull(input.getFirm())) {
//            initialProduct.setFirm(input.getFirm());
//        }
//        if (nonNull(input.getProductVersion())) {
//            initialProduct.setProductVersion(input.getProductVersion());
//        }
//        initialProduct.setModified(getModified());
//        initialProduct.setVersion(updateVersion(initialProduct.getVersion()));
//        return initialProduct;
//
//    }
//
//    private LocalDateTime getModified() {
//        return LocalDateTime.now();
//    }
//
//    private long updateVersion(long version) {
//        return version + 1;
//    }
}
