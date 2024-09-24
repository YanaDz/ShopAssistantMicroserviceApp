package pl.dziadkouskaya.search_server.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import pl.dziadkouskaya.search_server.entity.enums.Location;

import java.util.List;
import java.util.UUID;

@Data
@Jacksonized
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class SellerDto {
    private UUID id;
    private String name;
    private Location location;
    private String searchUrl;
    private String titleClass;
    private List<SellerElementDto> titleProductElements;
    private List<SellerElementDto> prices;
    private String productUrl;
}
