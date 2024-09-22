package pl.dziadkouskaya.search_server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.dziadkouskaya.search_server.entity.enums.SellerElementField;
import pl.dziadkouskaya.search_server.entity.enums.SellerElementPriority;
import pl.dziadkouskaya.search_server.entity.enums.SellerElementType;

import java.util.UUID;

@Entity
@Table(name = "seller_element")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SellerElement extends AuditableEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", updatable = false, nullable = false)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "element_type")
    private SellerElementType elementType;

    @Column(name = "element_name")
    private String elementName;

    @Column(name = "priority")
    private SellerElementPriority priority;

    @Column(name = "seller_element_field")
    private SellerElementField sellerElementField;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "seller_id")
    private Seller seller;
}
