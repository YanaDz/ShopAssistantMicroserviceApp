package pl.dziadkouskaya.graphql.entity.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import pl.dziadkouskaya.graphql.entity.Product;

import java.util.List;
import java.util.UUID;

public class FirmParams {
    private String name;

}
