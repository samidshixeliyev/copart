package az.code.copart.mapper;


import az.code.copart.dto.response.CarTypeResponse;
import az.code.copart.dto.response.PageableResponse;
import az.code.copart.entity.CarType;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;



@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
uses = {CarTypeMapper.class})
public interface PageableMapper {
  PageableResponse <CarTypeResponse> fromCarTypeEntityToPageableResponse(Page<CarType> entity);

}
