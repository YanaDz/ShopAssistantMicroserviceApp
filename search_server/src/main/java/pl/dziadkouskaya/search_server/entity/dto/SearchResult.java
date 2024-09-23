package pl.dziadkouskaya.search_server.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class SearchResult {
    private String seller;
    private String name;
    private String url;
    private String price;
    private boolean isCompared = false;

}
