package pl.dziadkouskaya.graphql.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import pl.dziadkouskaya.graphql.entity.enums.Location;
import pl.dziadkouskaya.graphql.entity.enums.TranslationType;

import java.util.List;
import java.util.UUID;

@Data
@Jacksonized
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductTranslationDto {
    private UUID id;
    private TranslationType translationType;
    private Location location;
    private String locationTranslation;
    private ProductTranslationDto basicTranslation;
    private List<ProductTranslationDto> additionalTranslations;
}