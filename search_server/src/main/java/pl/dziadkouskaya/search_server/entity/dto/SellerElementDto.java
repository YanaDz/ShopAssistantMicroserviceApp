package pl.dziadkouskaya.search_server.entity.dto;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import pl.dziadkouskaya.search_server.entity.Seller;
import pl.dziadkouskaya.search_server.entity.enums.SellerElementField;
import pl.dziadkouskaya.search_server.entity.enums.SellerElementPriority;
import pl.dziadkouskaya.search_server.entity.enums.SellerElementType;

import java.util.UUID;

@Data
@Jacksonized
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class SellerElementDto {
    private UUID id;
    private SellerElementType elementType;
    private String elementName;
    private SellerElementPriority priority;
    private SellerElementField sellerElementField;
    private String sellerName;
    private String sellerId;
}
