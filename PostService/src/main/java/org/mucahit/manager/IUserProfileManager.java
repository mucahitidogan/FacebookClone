package org.mucahit.manager;

import org.mucahit.dto.request.GetMyProfileRequestDto;
import org.mucahit.repository.entity.UserProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(
        name = "user-service",
        url = "http://localhost:9093/api/v1/userprofile"
)
public interface IUserProfileManager {

    @PostMapping("/get-other-profile")
    ResponseEntity<UserProfile> getOtherProfile(@RequestBody @Valid GetMyProfileRequestDto dto);
}
