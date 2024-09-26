package pl.dziadkouskaya.productMicroservice.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.dziadkouskaya.productMicroservice.entity.Firm;
import pl.dziadkouskaya.productMicroservice.entity.dto.FirmDto;
import pl.dziadkouskaya.productMicroservice.entity.dto.FirmParams;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FirmMapper {
    @Mapping(target = "name", source = "name")
    Firm toEntity(FirmParams firmParams);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    FirmDto toDto(Firm firm);
}
