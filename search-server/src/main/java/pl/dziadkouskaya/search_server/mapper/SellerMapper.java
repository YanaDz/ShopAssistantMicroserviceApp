package pl.dziadkouskaya.search_server.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.dziadkouskaya.search_server.entity.Seller;
import pl.dziadkouskaya.search_server.entity.SellerElement;
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

    default List<SellerElement> mapSellerElements(List<SellerElementParam> sellers) {
        return sellers.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
    }
}
