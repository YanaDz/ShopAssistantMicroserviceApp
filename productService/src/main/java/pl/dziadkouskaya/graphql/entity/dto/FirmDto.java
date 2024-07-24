package pl.dziadkouskaya.graphql.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import pl.dziadkouskaya.graphql.entity.Product;

import java.util.List;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FirmDto {
    private UUID id;

    private String name;

    List<Product> products;

}
