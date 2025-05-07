package az.code.copart.mapper;

import az.code.copart.dto.request.ModelCreateRequest;
import az.code.copart.dto.request.ModelUpdateRequest;
import az.code.copart.dto.response.ModelResponse;
import az.code.copart.entity.Maker;
import az.code.copart.entity.Model;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {MakerMapper.class}
)
public interface ModelMapper {
    @Mapping(target = "maker", source = "maker")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Model fromCreateToEntity(ModelCreateRequest request, Maker maker);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "maker", source = "maker")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Model fromUpdateToEntity(@MappingTarget Model model, ModelUpdateRequest request, Maker maker);
    ModelResponse fromEntityToResponse(Model model);

}
