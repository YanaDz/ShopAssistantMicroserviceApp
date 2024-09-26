package pl.dziadkouskaya.productMicroservice.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.UUID;

@Data
@Jacksonized
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class SellerParam {
    private UUID id;
    private String name;
    private pl.dziadkouskaya.productMicroservice.entity.enums.Location location;
    private String searchUrl;
    private String titleClass;
    private String productUrl;
}
