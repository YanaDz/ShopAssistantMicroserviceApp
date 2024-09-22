package pl.dziadkouskaya.graphql.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import pl.dziadkouskaya.graphql.entity.Product;

import java.util.List;
import java.util.UUID;

@Data
@Jacksonized
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class FirmDto {
    private UUID id;

    private String name;

}
