package pl.dziadkouskaya.productMicroservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.dziadkouskaya.productMicroservice.entity.Seller;
import pl.dziadkouskaya.productMicroservice.entity.dto.SellerDto;
import pl.dziadkouskaya.productMicroservice.entity.dto.SellerParam;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SellerMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "searchUrl", source = "searchUrl")
    Seller toEntity(SellerDto sellerDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "searchUrl", source = "searchUrl")
    SellerDto toDto(Seller  seller);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "searchUrl", source = "searchUrl")
    Seller toEntity(SellerParam sellerParam);
}
