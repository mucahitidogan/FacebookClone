package org.mucahit.controller;

import lombok.RequiredArgsConstructor;
import org.mucahit.dto.request.GetMyProfileRequestDto;
import org.mucahit.dto.request.UserProfileFindRequestDto;
import org.mucahit.dto.request.UserProfileSaveRequestDto;
import org.mucahit.dto.request.UserProfileUpdateRequestDto;
import org.mucahit.dto.response.GetMyProfileResponseDto;
import org.mucahit.dto.response.UserProfileFindResponseDto;
import org.mucahit.repository.entity.UserProfile;
import org.mucahit.service.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/userprofile")
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService userProfileService;

    @GetMapping("/getpage")
    public ResponseEntity<String> getPage(){
        return ResponseEntity.ok("UserProfile Service'e ulaştınız.");
    }
    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody @Valid UserProfileSaveRequestDto dto){
        userProfileService.save(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find-all")
    @PreAuthorize("hasAuthority('NE_OLA_KI') or hasAuthority('NE_OLA_Bu')")
    public ResponseEntity<List<UserProfile>> findAll(){
        return ResponseEntity.ok(userProfileService.findAll());
    }
    @PostMapping("/update-user")
    public ResponseEntity<Void> updateUser(@RequestBody @Valid UserProfileUpdateRequestDto dto){
        userProfileService.update(dto);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/to-Upper")
    public ResponseEntity<String> getNameToUpper(String name){
        return ResponseEntity.ok(userProfileService.getNameToUpper(name));
    }

    @PostMapping("/get-my-profile")
    @CrossOrigin("*")
    public ResponseEntity<GetMyProfileResponseDto> getMyProfile(@RequestBody @Valid GetMyProfileRequestDto dto){
        return ResponseEntity.ok(userProfileService.getMyProfile(dto));
    }

    @PostMapping("/get-other-profile")
    @CrossOrigin("*")
    public ResponseEntity<UserProfile> getOtherProfile(@RequestBody @Valid GetMyProfileRequestDto dto){
        return ResponseEntity.ok(userProfileService.getOtherProfile(dto));
    }
    @PostMapping("/clearcache")
    public ResponseEntity<Void> clearCache(String name){
        userProfileService.clearCacheToUpper();
        return ResponseEntity.ok().build();
    }



//    @GetMapping("/find-by-search-bar-value")
//    public ResponseEntity<List<UserProfileFindResponseDto>> findBySearchBarValue(UserProfileFindRequestDto dto){
//        return ResponseEntity.ok(userProfileService.findBySearchBarValue(dto));
//    }
//



}
