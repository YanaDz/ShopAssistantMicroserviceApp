package pl.dziadkouskaya.search_server.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.dziadkouskaya.search_server.entity.Seller;
import pl.dziadkouskaya.search_server.entity.params.SellerParam;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SellerMapper {


    @Mapping(target = "name", source = "name")
    @Mapping(target = "searchUrl", source = "searchUrl")
    @Mapping(target = "titleClass", source = "titleClass")
    @Mapping(target = "titleProductElement", source = "titleProductElement")
    @Mapping(target = "location", source = "location")
    Seller toEntity(SellerParam sellerParam);
}
