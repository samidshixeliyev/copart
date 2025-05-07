package az.code.copart.mapper;

import az.code.copart.dto.request.FavouriteCarCreateRequest;
import az.code.copart.dto.request.FavouriteCarUpdateRequest;
import az.code.copart.dto.response.FavouriteCarResponse;
import az.code.copart.entity.Car;
import az.code.copart.entity.FavouriteCar;
import az.code.copart.entity.User;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
uses = {CarMapper.class, UserMapper.class})
public interface FavouriteCarMapper {
    @Mapping(target = "user", source = "user")
    @Mapping(target = "car", source = "car")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    FavouriteCar fromCreateToEntity(FavouriteCarCreateRequest request, User user, Car car);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "car", source = "car")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    FavouriteCar fromUpdateToEntity(@MappingTarget FavouriteCar favouriteCar, FavouriteCarUpdateRequest request,User user, Car car);
    FavouriteCarResponse fromEntityToResponse(FavouriteCar favouriteCar);
}
