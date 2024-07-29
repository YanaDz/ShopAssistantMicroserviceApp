package pl.dziadkouskaya.search_server.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.dziadkouskaya.search_server.entity.enums.Location;

import java.util.UUID;

@Entity
@Table(name = "seller")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Seller extends AuditableEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", updatable = false, nullable = false)
    @EqualsAndHashCode.Include
    private UUID id;

    @NonNull
    private String name;

    @NonNull
    private Location location;

    @NonNull
    private String searchUrl;

    private String titleClass;

    private String titleProductElement;

    private  String priceClass;

    private String priceElement;

    private String productUrl;


}
