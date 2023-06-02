package org.mucahit.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mucahit.dto.request.GetMyProfileRequestDto;
import org.mucahit.dto.response.GetMyProfileResponseDto;
import org.mucahit.repository.IUserProfileRepository;
import org.mucahit.repository.entity.UserProfile;
import org.mucahit.utility.JwtTokenManager;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserProfileUnitTest {

    @InjectMocks
    UserProfileService userProfileService;
    @Mock
    IUserProfileRepository userProfileRepository;
    @Mock
    JwtTokenManager jwtTokenManager;

    @Test
    void getMyProfileTest(){
        when(jwtTokenManager.getIdFromToken(any()))
                .thenReturn(Optional.of(19l));
        when(userProfileRepository.findOptionalByAuthid(any()))
                .thenReturn(Optional.of(
                        UserProfile.builder()
                                .phone("0 555 555 55 55")
                                .avatar("mu.png")
                                .name("Mucahit")
                                .surname("Doğan")
                                .username("mucahit")
                                .build()
                ));
        GetMyProfileResponseDto responseDto =
                userProfileService
                        .getMyProfile(GetMyProfileRequestDto.builder()
                                .token("5634gdf456456456")
                                .build());
        Assertions.assertTrue(responseDto != null);
    }

}
