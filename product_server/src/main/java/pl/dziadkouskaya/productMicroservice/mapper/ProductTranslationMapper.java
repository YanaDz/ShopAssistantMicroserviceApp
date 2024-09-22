package pl.dziadkouskaya.graphql.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.dziadkouskaya.graphql.entity.ProductTranslation;
import pl.dziadkouskaya.graphql.entity.dto.ProductTranslationDto;
import pl.dziadkouskaya.graphql.entity.enums.Location;
import pl.dziadkouskaya.graphql.entity.enums.TranslationType;
import pl.dziadkouskaya.graphql.entity.param.ProductTranslationParam;

import java.util.UUID;

import static pl.dziadkouskaya.graphql.entity.enums.TranslationType.ADDITIONAL;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductTranslationMapper {

    @Mapping(target = "translationType", source = "translationType")
    @Mapping(target = "location", source = "location")
    @Mapping(target = "locationTranslation", source = "locationTranslation")
    @Mapping(target = "basicTranslationId", source = "basicLocationId")
    ProductTranslation toEntity(ProductTranslationParam param);

    @Mapping(target = "translationType", source = "translationType")
    @Mapping(target = "location", source = "location")
    @Mapping(target = "locationTranslation", source = "locationTranslation")
    ProductTranslationDto toDto(ProductTranslation translation);

    @Mapping(target = "translationType", expression = "java(getAdditionalType())")
    @Mapping(target = "location", source = "location")
    @Mapping(target = "locationTranslation", source = "translation")
    @Mapping(target = "basicTranslationId", source = "idMainTranslation")
    ProductTranslation toAdditionalTranslation(UUID idMainTranslation, Location location, String translation);

    @Mapping(target = "id", source = "translation.id")
    @Mapping(target = "translationType", source = "translation.translationType")
    @Mapping(target = "location", source = "translation.location")
    @Mapping(target = "locationTranslation", source = "translation.locationTranslation")
    @Mapping(target = "basicTranslation", source = "basicTranslation")
    ProductTranslationDto toDto(ProductTranslation translation, ProductTranslationDto basicTranslation);

    default TranslationType getAdditionalType() {
        return ADDITIONAL;
    }
}
