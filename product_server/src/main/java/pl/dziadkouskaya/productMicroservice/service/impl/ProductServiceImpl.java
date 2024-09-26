package pl.dziadkouskaya.productMicroservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.dziadkouskaya.productMicroservice.entity.Product;
import pl.dziadkouskaya.productMicroservice.entity.ProductTranslation;
import pl.dziadkouskaya.productMicroservice.entity.dto.ProductDto;
import pl.dziadkouskaya.productMicroservice.entity.filters.ProductFilter;
import pl.dziadkouskaya.productMicroservice.entity.param.ProductParams;
import pl.dziadkouskaya.productMicroservice.exception.ResourceNotFoundException;
import pl.dziadkouskaya.productMicroservice.mapper.ProductMapper;
import pl.dziadkouskaya.productMicroservice.repository.sql.ProductRepository;
import pl.dziadkouskaya.productMicroservice.service.FirmService;
import pl.dziadkouskaya.productMicroservice.service.ProductService;
import pl.dziadkouskaya.productMicroservice.service.ProductTranslationService;

import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final FirmService firmService;
    private final ProductTranslationService translationService;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, FirmService firmService,
                              ProductTranslationService translationService) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.firmService = firmService;
        this.translationService = translationService;
    }

    @Override
    public List<ProductDto> getAll() {
        log.info("Get all products.");
        var products = productRepository.findAll();
        log.info("Returned {} products. ", products.size());
        return products.stream()
            .map(product -> productMapper.toDto(product, firmService.getFirmDtoById(product.getFirm().getId()),
                translationService.getTranslationDto(product.getProductTranslations())))
            .toList();
    }


    @Override
    public List<ProductDto> getProductsByFields(ProductFilter filter) {
        log.info("Get products by params: {}.", filter);
        var products = productRepository.findByFilters(filter.getName(), filter.getFirm(),
            filter.getProductType().getCode(), filter.getProductVersion());
        log.info("Returned {} products. ", products.size());
        return products.stream()
            .map(product -> productMapper.toDto(product, firmService.getFirmDtoById(product.getFirm().getId()),
                translationService.getTranslationDto(product.getProductTranslations())))
            .toList();
    }

    @Override
    public ProductDto getProductById(UUID id) {
        log.info("Get product by ID: {}.", id);
        var product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ResourceNotFoundException(String.format("There is no product with id %s", id));
        }
        return productMapper.toDto(product.get(), firmService.getFirmDtoById(product.get().getFirm().getId()),
            translationService.getTranslationDto(product.get().getProductTranslations()));
    }

    @Override
    public ProductDto createProduct(ProductParams input) {
        log.info("Get new product with input: {}.", input);

        var firm = firmService.getFirmDtoById(input.getFirmId());
        var translations = translationService.getMainTranslationByStrictName(input.getName());
        ProductTranslation basicTranslation;
        if (isNull(translations) || translations.isEmpty()) {
            basicTranslation = translationService.createNotTranslatedEntity(input.getName());
            translations.add(basicTranslation);
        }

        var additionalTranslations = translationService.getAdditionalTranslations(translations.getFirst().getId());
        if (!additionalTranslations.isEmpty()) {
            translations.addAll(additionalTranslations);
        }

        var initialProduct = productMapper.toEntity(input, firmService.getFirmById(input.getFirmId()), translations);
        var product = productRepository.save(initialProduct);
        log.info("Create entity with id {}.", product.getId());
        translations.stream()
            .forEach(translation -> {
                translation.getProductTranslations().add(product);
                translationService.updateTranslation(translation);
            });
        return productMapper.toDto(product, firm, translationService.getTranslationDto(translations));
    }

    @Override
    public ProductDto updateProduct(UUID id, ProductParams input) {
        log.info("Update product with id {}.", id);
        var initialProduct = productRepository.findById(id);
        if (initialProduct.isEmpty()) {
            throw new ResourceNotFoundException(String.format("There is no product with id %s", id));
        }
        var updatedProduct = updateProduct(input, initialProduct.get());
        var product = productRepository.save(updatedProduct);
        log.info("Update entity with id {}.", product.getId());
        return productMapper.toDto(product, firmService.getFirmDtoById(product.getFirm().getId()),
            translationService.getTranslationDto(product.getProductTranslations()));

    }

    @Override
    public Boolean deleteProduct(UUID id) {
        if (productRepository.existsById(id)) {
            log.info("Product with id {} exists.", id);
            productRepository.deleteById(id);
            log.info("Product with id {} has been deleted.", id);
            return true;
        } else {
            log.info("Product with id {} doesn't exist.", id);
            return false;
        }
    }

    private Product updateProduct(ProductParams input, Product initialProduct) {
        if (nonNull(input.getName())) {
            initialProduct.setName(input.getName());
        }
        if ((nonNull(input.getProductType()))) {
            initialProduct.setProductType(input.getProductType());
        }
        if (nonNull(input.getFirmId())) {
            initialProduct.setFirm(firmService.getFirmById(input.getFirmId()));
        }
        if (nonNull(input.getProductVersion())) {
            initialProduct.setProductVersion(input.getProductVersion());
        }
        return initialProduct;

    }

}
