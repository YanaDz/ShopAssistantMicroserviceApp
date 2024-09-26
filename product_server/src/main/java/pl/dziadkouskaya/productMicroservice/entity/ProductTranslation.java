package pl.dziadkouskaya.productMicroservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.dziadkouskaya.productMicroservice.entity.enums.Location;
import pl.dziadkouskaya.productMicroservice.entity.enums.TranslationType;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "product_translation")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductTranslation extends AuditableEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", updatable = false, nullable = false)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "translation_type")
    private TranslationType translationType;

    @Column(name = "location")
    private Location location;

    @Column(name = "location_translation")
    private String locationTranslation;

    @Column(name = "basic_translation_id")
    private UUID basicTranslationId;

    @ManyToMany(mappedBy = "productTranslations")
    private List<Product> productTranslations;
}
