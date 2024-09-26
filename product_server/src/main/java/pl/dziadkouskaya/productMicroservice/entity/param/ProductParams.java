package pl.dziadkouskaya.productMicroservice.entity.param;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import pl.dziadkouskaya.productMicroservice.entity.Firm;
import pl.dziadkouskaya.productMicroservice.entity.ProductTranslation;
import pl.dziadkouskaya.productMicroservice.entity.enums.ProductType;

import java.util.List;
import java.util.UUID;

@Data
@Jacksonized
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class ProductParams {
    private String name;
    private UUID firmId;
    private String productVersion;
    private ProductType productType;
}
