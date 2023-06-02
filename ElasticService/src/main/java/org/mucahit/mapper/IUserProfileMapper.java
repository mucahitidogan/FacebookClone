package org.mucahit.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.mucahit.dto.request.UserProfileDto;
import org.mucahit.repository.entity.UserProfile;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserProfileMapper {
    IUserProfileMapper INSTANCE = Mappers.getMapper(IUserProfileMapper.class);

    @Mapping(target = "userid", source = "id")
    UserProfile toUserProfile(final UserProfileDto dto);
}
