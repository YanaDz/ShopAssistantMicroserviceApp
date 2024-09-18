package pl.dziadkouskaya.search_server.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.dziadkouskaya.search_server.entity.Seller;
import pl.dziadkouskaya.search_server.entity.SellerElement;
import pl.dziadkouskaya.search_server.entity.dto.SellerDto;
import pl.dziadkouskaya.search_server.entity.dto.SellerElementDto;
import pl.dziadkouskaya.search_server.entity.params.SellerElementParam;
import pl.dziadkouskaya.search_server.entity.params.SellerParam;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SellerMapper {


    @Mapping(target = "name", source = "name")
    @Mapping(target = "location", source = "location")
    @Mapping(target = "searchUrl", source = "searchUrl")
    @Mapping(target = "titleClass", source = "titleClass")
    @Mapping(target = "productUrl", source = "titleClass")
    @Mapping(target = "titleProductElements", expression = "java(mapSellerElements(sellerParam.getTitleProductElements()) )")
    @Mapping(target = "prices", expression = "java(mapSellerElements(sellerParam.getPrices()) )")
    Seller toEntity(SellerParam sellerParam);

    @Mapping(target = "elementType", source = "elementType")
    @Mapping(target = "elementName", source = "elementName")
    @Mapping(target = "priority", source = "priority")
    @Mapping(target = "sellerElementField", source = "sellerElementField")
    SellerElement toEntity(SellerElementParam param);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "elementType", source = "elementType")
    @Mapping(target = "elementName", source = "elementName")
    @Mapping(target = "priority", source = "priority")
    @Mapping(target = "sellerElementField", source = "sellerElementField")
    @Mapping(target = "sellerName", source = "element.seller.name")
    @Mapping(target = "sellerId", source = "element.seller.id")
    SellerElementDto toDto(SellerElement element);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "location", source = "location")
    @Mapping(target = "searchUrl", source = "searchUrl")
    @Mapping(target = "titleClass", source = "titleClass")
    @Mapping(target = "productUrl", source = "titleClass")
    @Mapping(target = "titleProductElements", expression = "java(mapSellerElementsToDto(seller.getTitleProductElements()) )")
    @Mapping(target = "prices", expression = "java(mapSellerElementsToDto(seller.getPrices()))")
    SellerDto toDto(Seller seller);

    default List<SellerElement> mapSellerElements(List<SellerElementParam> sellers) {
        return sellers.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
    }

    default List<SellerElementDto> mapSellerElementsToDto(List<SellerElement> sellers) {
        return sellers.stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }
}
