package pl.dziadkouskaya.search_server.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
import pl.dziadkouskaya.search_server.entity.enums.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "seller")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
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

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "seller", orphanRemoval = true)
    @ToString.Exclude
    private List<SellerElement> titleProductElements = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "seller", orphanRemoval = true)
    @ToString.Exclude
    private List<SellerElement> prices = new ArrayList<>();

    @Column(name = "product_url")
    private String productUrl;

}
