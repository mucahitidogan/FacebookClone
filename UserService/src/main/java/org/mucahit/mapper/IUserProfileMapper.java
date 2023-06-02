package org.mucahit.mapper;

import org.apache.catalina.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.mucahit.dto.request.UserProfileSaveRequestDto;
import org.mucahit.dto.response.UserProfileFindResponseDto;
import org.mucahit.rabbitmq.model.CreateUserModel;
import org.mucahit.repository.entity.UserProfile;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IUserProfileMapper {

    IUserProfileMapper INSTANCE = Mappers.getMapper(IUserProfileMapper.class);

    UserProfileFindResponseDto toUserResponseDto(final UserProfile userProfile);
    UserProfile toUser(final UserProfileSaveRequestDto dto);

    UserProfile toUserProfile(final CreateUserModel model);
}
