package pl.dziadkouskaya.search_server.entity.params;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import pl.dziadkouskaya.search_server.entity.enums.Location;

@Data
@Jacksonized
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class SellerParam {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String searchUrl;

    @NotNull
    @NotBlank
    private String titleClass;

    private String titleProductElement;

    @NotNull
    @NotBlank
    private Location location;

    @NotNull
    @NotBlank
    private  String priceClass;

    @NotNull
    @NotBlank
    private String priceElement;

    private String productUrl;
}
