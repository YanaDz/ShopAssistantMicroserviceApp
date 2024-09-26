package pl.dziadkouskaya.productMicroservice.service;

import pl.dziadkouskaya.productMicroservice.entity.ProductTranslation;
import pl.dziadkouskaya.productMicroservice.entity.dto.ProductTranslationDto;
import pl.dziadkouskaya.productMicroservice.entity.param.ProductTranslationParam;

import java.util.List;
import java.util.UUID;

public interface ProductTranslationService {
    ProductTranslationDto createProductTranslation(ProductTranslationParam param);

    List<ProductTranslationDto> getTranslations();

    ProductTranslationDto getTranslation(UUID id);

    List<ProductTranslationDto> getTranslationsByName(String name);

    List<ProductTranslation> getMainTranslationByStrictName(String name);

    ProductTranslation createNotTranslatedEntity(String name);

    List<ProductTranslation> getAdditionalTranslations(UUID uuid);

    ProductTranslation updateTranslation(ProductTranslation productTranslation);

    List<ProductTranslationDto> getTranslationDto(List<ProductTranslation> translations);
}
