package pl.dziadkouskaya.graphql.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

//    @Mapping(target = "name", source = "name")
//    @Mapping(target = "productType", source = "productType")
//    @Mapping(target = "firm", source = "firm")
//    @Mapping(target = "productVersion", source = "productVersion")
//    Product toEntity(CreateProductInput input);

}
