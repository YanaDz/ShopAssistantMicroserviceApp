package pl.dziadkouskaya.productMicroservice.entity.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import pl.dziadkouskaya.productMicroservice.entity.enums.ProductType;

import java.util.List;
import java.util.UUID;

@Data
@Jacksonized
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto {
    private UUID id;
    private String name;
    private FirmDto firm;
    private String productVersion;
    private ProductType productType;
    private List<ProductTranslationDto> productTranslations;
}
