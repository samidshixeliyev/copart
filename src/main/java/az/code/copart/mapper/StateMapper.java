package az.code.copart.mapper;

import az.code.copart.dto.request.StateCreateRequest;
import az.code.copart.dto.request.StateUpdateRequest;
import az.code.copart.dto.response.StateResponse;
import az.code.copart.entity.State;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface StateMapper {
    State fromCreateToEntity(StateCreateRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    State fromUpdateToEntity(@MappingTarget State state, StateUpdateRequest request);
    StateResponse fromEntityToResponse(State state);
}
