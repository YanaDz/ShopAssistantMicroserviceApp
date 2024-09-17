package pl.dziadkouskaya.graphql.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.dziadkouskaya.graphql.entity.Firm;
import pl.dziadkouskaya.graphql.entity.dto.FirmDto;
import pl.dziadkouskaya.graphql.entity.dto.FirmParams;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FirmMapper {
    @Mapping(target = "name", source = "name")
    Firm toEntity(FirmParams firmParams);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    FirmDto toDto(Firm firm);
}
