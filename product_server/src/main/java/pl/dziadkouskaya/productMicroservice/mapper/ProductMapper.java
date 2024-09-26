package pl.dziadkouskaya.productMicroservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.dziadkouskaya.productMicroservice.entity.Firm;
import pl.dziadkouskaya.productMicroservice.entity.Product;
import pl.dziadkouskaya.productMicroservice.entity.ProductTranslation;
import pl.dziadkouskaya.productMicroservice.entity.dto.FirmDto;
import pl.dziadkouskaya.productMicroservice.entity.dto.ProductDto;
import pl.dziadkouskaya.productMicroservice.entity.dto.ProductTranslationDto;
import pl.dziadkouskaya.productMicroservice.entity.param.ProductParams;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    @Mapping(target = "name", source = "input.name")
    @Mapping(target = "productType", source = "input.productType")
    @Mapping(target = "productVersion", source = "input.productVersion")
    @Mapping(target = "firm", source = "firm")
    @Mapping(target = "productTranslations", source = "translations")
    Product toEntity(ProductParams input, Firm firm, List<ProductTranslation> translations);

    @Mapping(target = "id", source = "product.id")
    @Mapping(target = "name", source = "product.name")
    @Mapping(target = "productType", source = "product.productType")
    @Mapping(target = "productVersion", source = "product.productVersion")
    @Mapping(target = "firm", source = "firmDto")
    @Mapping(target = "productTranslations", source = "translations")
    ProductDto toDto(Product product, FirmDto firmDto, List<ProductTranslationDto> translations);

}
