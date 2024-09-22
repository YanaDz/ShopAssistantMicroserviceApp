package pl.dziadkouskaya.search_server.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.util.ArrayList;
import java.util.List;

@Data
@Jacksonized
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class EqualProductNames {
    private String productName;
    private List<SearchResult> descriptions = new ArrayList<>();

    public void addSameProduct(SearchResult result) {
        descriptions.add(result);
    }

}
