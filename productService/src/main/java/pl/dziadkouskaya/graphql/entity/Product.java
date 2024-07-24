package pl.dziadkouskaya.graphql.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.dziadkouskaya.graphql.entity.enums.ProductType;

import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "product")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Product extends AuditableEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", updatable = false, nullable = false)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "product", orphanRemoval = true)
    private List<ProductTranslation> productTranslations;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "firm_id")
    private Firm firm;

    @Column(name = "product_version")
    private String productVersion;

    @Column(name = "product_type")
    @Convert(converter = ProductType.Converter.class)
    private ProductType productType;



}
