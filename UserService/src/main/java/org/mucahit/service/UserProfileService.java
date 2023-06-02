package org.mucahit.service;

import org.mucahit.dto.request.GetMyProfileRequestDto;
import org.mucahit.dto.request.UserProfileSaveRequestDto;
import org.mucahit.dto.request.UserProfileUpdateRequestDto;
import org.mucahit.dto.response.GetMyProfileResponseDto;
import org.mucahit.exception.ErrorType;
import org.mucahit.exception.UserException;
import org.mucahit.mapper.IUserProfileMapper;
import org.mucahit.rabbitmq.model.CreateUserModel;
import org.mucahit.repository.IUserProfileRepository;
import org.mucahit.repository.entity.UserProfile;
import org.mucahit.utility.JwtTokenManager;
import org.mucahit.utility.ServiceManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile, String> {
    private final IUserProfileRepository userRepository;
    private final JwtTokenManager jwtTokenManager;

    public UserProfileService(IUserProfileRepository userRepository, JwtTokenManager jwtTokenManager){
        super(userRepository);
        this.userRepository = userRepository;
        this.jwtTokenManager = jwtTokenManager;
    }

    public void save(UserProfileSaveRequestDto dto){
        save(IUserProfileMapper.INSTANCE.toUser(dto));
    }

    public void save(CreateUserModel model){
        save(IUserProfileMapper.INSTANCE.toUserProfile(model));
    }
    public List<UserProfile> findAll(){
       return userRepository.findAll();
    }

    public void update(UserProfileUpdateRequestDto dto){
        Optional<Long> authid = jwtTokenManager.getIdFromToken(dto.getToken());
        if(authid.isEmpty())
            throw new UserException(ErrorType.ERROR_INVALID_TOKEN);
        Optional<UserProfile> optionalUser = userRepository.findOptionalByAuthid(authid.get());
        if(optionalUser.isPresent()){
           UserProfile profile = optionalUser.get();
           profile.setAvatar(dto.getAvatar());
           profile.setGender(dto.getGender());
           profile.setName(dto.getName());
           profile.setSurname(dto.getSurname());
           profile.setPhone(dto.getPhone());
           profile.setAddress(dto.getAddress());
           update(profile);
        }
    }

    @Cacheable(value = "getnametoupper")
    public String getNameToUpper(String name){
        try{
            Thread.sleep(3000);
        }catch (Exception e){

        }
        return name.toUpperCase();
    }

    @CacheEvict(value = "getnametoupper", allEntries = true)
    public void clearCacheToUpper(){
        System.out.println("Tüm cache'i temizledim...");
    }

    public GetMyProfileResponseDto getMyProfile(GetMyProfileRequestDto dto) {
        Optional<Long> authid = jwtTokenManager.getIdFromToken(dto.getToken());
        if(authid.isEmpty())
            throw new UserException(ErrorType.ERROR_INVALID_TOKEN);
        Optional<UserProfile> optionalUser = userRepository.findOptionalByAuthid(authid.get());
        if(optionalUser.isEmpty())
            throw new UserException(ErrorType.USER_NOT_FOUND);
        return GetMyProfileResponseDto.builder()
                .about(optionalUser.get().getPhone())
                .avatar(optionalUser.get().getAvatar())
                .name(optionalUser.get().getName()+ " " + optionalUser.get().getSurname())
                .username(optionalUser.get().getUsername())
                .build();
    }

    public UserProfile getOtherProfile(GetMyProfileRequestDto dto) {
       Optional<UserProfile> optionalUserProfile = findById(dto.getUserId());
       if(optionalUserProfile.isEmpty())
           throw new UserException(ErrorType.USER_NOT_FOUND);
       return optionalUserProfile.get();
    }

    public Optional<UserProfile> findByAuthid(Long authid){
        return userRepository.findOptionalByAuthid(authid);
    }

//    public List<UserProfileFindResponseDto>  findBySearchBarValue(UserProfileFindRequestDto dto){
//        List<UserProfile> userProfileList = userRepository.findByNameContainsIgnoreCase(dto.getSearchBarValue());
//        userRepository.findBySurnameContainsIgnoreCase(dto.getSearchBarValue()).forEach(userProfile ->{
//            userProfileList.add(userProfile);
//        });
//        if(userProfileList.isEmpty())
//            throw new UserException(ErrorType.USER_NOT_FOUND);
//        List<UserProfileFindResponseDto> users = new ArrayList<>();
//        userProfileList.forEach(userProfile ->{
//            users.add(IUserProfileMapper.INSTANCE.toUserResponseDto(userProfile));
//        });
//         return users;
//    }


}
