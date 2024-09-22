package pl.dziadkouskaya.search_server.entity.params;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@Jacksonized
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class SearchParam {
    private String searchRequest;
    private UUID sellerId;
    private int timeWait;

}
