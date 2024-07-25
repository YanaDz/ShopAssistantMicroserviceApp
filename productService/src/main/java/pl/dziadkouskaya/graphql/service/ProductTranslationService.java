package pl.dziadkouskaya.graphql.service;

import pl.dziadkouskaya.graphql.entity.dto.ProductTranslationDto;
import pl.dziadkouskaya.graphql.entity.param.ProductTranslationParam;

import java.util.List;
import java.util.UUID;

public interface ProductTranslationService {
    ProductTranslationDto createProductTranslation(ProductTranslationParam param);
    List<ProductTranslationDto> getTranslations();
    ProductTranslationDto getTranslation(UUID id);
    List<ProductTranslationDto> getTranslationsByName(String name);
}
