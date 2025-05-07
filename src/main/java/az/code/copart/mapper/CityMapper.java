package az.code.copart.mapper;

import az.code.copart.dto.request.CityCreateRequest;
import az.code.copart.dto.request.CityUpdateRequest;
import az.code.copart.dto.response.CityResponse;
import az.code.copart.entity.City;
import az.code.copart.entity.State;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,uses = {StateMapper.class})
public interface CityMapper {
    @Mapping(target = "state", source = "state")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    City fromCreateToEntity(CityCreateRequest request, State state);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "state", source = "state")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    City fromUpdateToEntity(@MappingTarget City city, CityUpdateRequest request, State state);
    CityResponse fromEntityToResponse(City city);
}
