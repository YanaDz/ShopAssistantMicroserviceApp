package pl.dziadkouskaya.productMicroservice.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "firm")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Firm extends AuditableEntity{
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", updatable = false, nullable = false)
    @EqualsAndHashCode.Include
    private UUID id;

    @NonNull
    private String name;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "firm", orphanRemoval = true)
    List<Product> products;
}
