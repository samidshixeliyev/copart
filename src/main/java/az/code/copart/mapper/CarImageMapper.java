package az.code.copart.mapper;

import az.code.copart.dto.response.CarImageResponse;
import az.code.copart.entity.CarImage;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface CarImageMapper {

    CarImageResponse fromEntityToResponse(CarImage carImage);
}
