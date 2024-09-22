package pl.dziadkouskaya.graphql.entity.filters;

import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import pl.dziadkouskaya.graphql.entity.enums.ProductType;

@Data
@Jacksonized
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class ProductFilter {
    private String name;

    private ProductType productType;

    private String firm;

    private String productVersion;
}
