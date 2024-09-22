package pl.dziadkouskaya.search_server.entity.params;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import pl.dziadkouskaya.search_server.entity.enums.Location;

import java.util.ArrayList;
import java.util.List;

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

    @NotNull
    @NotBlank
    private Location location;

    private List<SellerElementParam> titleProductElements = new ArrayList<>();

    private List<SellerElementParam> prices = new ArrayList<>();

    private String productUrl;
}
