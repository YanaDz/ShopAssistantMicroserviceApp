package pl.dziadkouskaya.graphql.repository.sql;

import pl.dziadkouskaya.graphql.entity.ProductTranslation;
import pl.dziadkouskaya.graphql.entity.enums.Location;

import java.util.List;
import java.util.UUID;

public interface CustomProductTranslationRepository {
    List<ProductTranslation> getTranslationByStrictName(String name, Location location);
    List<ProductTranslation> getTranslationByName(String name);
    List<ProductTranslation> getAdditionalByMain(UUID id);
}
