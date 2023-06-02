package org.mucahit.controller;

import brave.Response;
import jdk.dynalink.linker.LinkerServices;
import lombok.RequiredArgsConstructor;
import org.mucahit.dto.request.UserProfileDto;
import org.mucahit.repository.entity.UserProfile;
import org.mucahit.service.UserProfileService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.mucahit.constants.EndPointList.*;

@RestController
@RequestMapping("elastic/userprofile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping(SAVE)
    public ResponseEntity<Void> save(@RequestBody UserProfileDto dto){
        userProfileService.save(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(FIND_ALL)
    public ResponseEntity<Iterable<UserProfile>> findAll(){
        return ResponseEntity.ok(userProfileService.findAll());
    }

    @GetMapping("findallpage")
    public ResponseEntity<Page<UserProfile>> findAll(int currentPage, int size, String sortParameter, String sortDirection){
        return ResponseEntity.ok(userProfileService.findAll(currentPage, size, sortParameter, sortDirection));
    }
}
