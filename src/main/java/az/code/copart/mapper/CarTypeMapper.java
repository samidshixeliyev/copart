package az.code.copart.mapper;

import az.code.copart.dto.request.CarTypeCreateRequest;
import az.code.copart.dto.request.CarTypeUpdateRequest;
import az.code.copart.dto.response.CarTypeResponse;
import az.code.copart.entity.CarType;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface CarTypeMapper {
    CarType fromCreateToEntity(CarTypeCreateRequest request);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CarType fromUpdateToEntity(@MappingTarget CarType carType, CarTypeUpdateRequest request);
    CarTypeResponse fromEntityToResponse(CarType entity);
}
