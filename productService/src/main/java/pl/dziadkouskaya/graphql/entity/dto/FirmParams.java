package pl.dziadkouskaya.graphql.entity.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class FirmParams {
    @NonNull
    private String name;

}
