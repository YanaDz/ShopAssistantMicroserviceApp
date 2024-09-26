package pl.dziadkouskaya.productMicroservice.repository.sql;

import pl.dziadkouskaya.productMicroservice.entity.ProductTranslation;
import pl.dziadkouskaya.productMicroservice.entity.enums.Location;

import java.util.List;
import java.util.UUID;

public interface CustomProductTranslationRepository {
    List<ProductTranslation> getMainTranslationByStrictName(String name, Location location);
    List<ProductTranslation> getTranslationByName(String name);
    List<ProductTranslation> getAdditionalByMain(UUID id);
    List<ProductTranslation> getMainTranslationByStrictName(String name);
}
