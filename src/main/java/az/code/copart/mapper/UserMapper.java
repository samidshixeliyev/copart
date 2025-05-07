package az.code.copart.mapper;

import az.code.copart.dto.request.UserCreateRequest;
import az.code.copart.dto.request.UserUpdateRequest;
import az.code.copart.dto.response.UserResponse;
import az.code.copart.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User fromCreateToEntity(UserCreateRequest request);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User fromUpdateToEntity(@MappingTarget User user, UserUpdateRequest request);
    UserResponse fromEntityToResponse(User user);

}