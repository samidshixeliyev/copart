package az.code.copart.mapper;

import az.code.copart.dto.request.MakerCreateRequest;
import az.code.copart.dto.request.MakerUpdateRequest;
import az.code.copart.dto.response.MakerResponse;
import az.code.copart.entity.Maker;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface MakerMapper {
    Maker fromCreateToEntity(MakerCreateRequest request);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Maker fromUpdateToEntity(@MappingTarget Maker maker, MakerUpdateRequest request);
    MakerResponse fromEntityToResponse(Maker maker);
}
