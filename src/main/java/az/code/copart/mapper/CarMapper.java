package az.code.copart.mapper;

import az.code.copart.client.response.auth.UserResponse;
import az.code.copart.dto.request.CarCreateRequest;
import az.code.copart.dto.request.CarTypeUpdateRequest;
import az.code.copart.dto.request.CarUpdateRequest;
import az.code.copart.dto.response.CarResponse;
import az.code.copart.entity.*;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
uses = {MakerMapper.class, ModelMapper.class, CarTypeMapper.class, FuelTypeMapper.class, CityMapper.class}
)
public interface CarMapper {
    //bu entitylerdeki fieldi diger fielde map edir
    @Mapping(target = "maker", source = "maker")
    @Mapping(target = "model", source = "model")
    @Mapping(target = "carType", source = "carType")
    @Mapping(target = "fuelType", source = "fuelType")
    @Mapping(target = "city", source = "city")
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Car fromCreateToEntity(CarCreateRequest request,
                           Maker maker,
                           Model model,
                           CarType carType,
                           FuelType fuelType,
                           City city,
                           Long userId);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "maker", source = "maker")
    @Mapping(target = "model", source = "model")
    @Mapping(target = "carType", source = "carType")
    @Mapping(target = "fuelType", source = "fuelType")
    @Mapping(target = "city", source = "city")
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Car fromUpdateToEntity(@MappingTarget Car car,
                           CarUpdateRequest request,
                           Maker maker,
                           Model model,
                           CarType carType,
                           FuelType fuelType,
                           City city,
                           Long userId);
    @Mapping(target ="user",source = "user")
    @Mapping(target = "id", source = "car.id")
    CarResponse fromEntityToResponse(Car car, UserResponse user);
}