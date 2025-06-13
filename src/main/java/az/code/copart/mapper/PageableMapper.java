package az.code.copart.mapper;


import az.code.copart.dto.response.*;
import az.code.copart.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;



@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
uses = {CarTypeMapper.class, CityMapper.class, ModelMapper.class,
        MakerMapper.class, FuelTypeMapper.class, StateMapper.class, CarMapper.class, FavouriteCarMapper.class}
)
public interface PageableMapper {
  PageableResponse <CarTypeResponse>  fromCarTypeEntityToPageableResponse(Page<CarType> entity);
  PageableResponse <CityResponse>     fromCityEntityToPageableResponse(Page<City> entity);
  PageableResponse <ModelResponse>    fromModelEntityToPageableResponse(Page<Model> entity);
  PageableResponse <MakerResponse>    fromMakerEntityToPageableResponse(Page<Maker> entity);
  PageableResponse <FuelTypeResponse> fromFuelTypeEntityToPageableResponse(Page<FuelType> entity);
  PageableResponse <StateResponse>    fromStateEntityToPageableResponse(Page<State> entity);
  PageableResponse <Car> fromCarEntityToPageableResponse(Page<Car> entity);
  FavouriteCarResponse fromFavouriteCarEntityToFavouriteCarResponse(Page<Car> entity);

}
