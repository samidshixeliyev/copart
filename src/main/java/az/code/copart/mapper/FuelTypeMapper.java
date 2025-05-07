package az.code.copart.mapper;

import az.code.copart.dto.request.FuelTypeCreateRequest;
import az.code.copart.dto.request.FuelTypeUpdateRequest;
import az.code.copart.dto.response.FuelTypeResponse;
import az.code.copart.entity.FuelType;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface FuelTypeMapper {
    FuelType fromCreateToEntity(FuelTypeCreateRequest request);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FuelType fromUpdateToEntity(@MappingTarget FuelType fuelType, FuelTypeUpdateRequest request);
    FuelTypeResponse fromEntityToResponse(FuelType entity);
}
