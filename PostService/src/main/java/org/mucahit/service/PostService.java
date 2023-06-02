package org.mucahit.service;

import org.mucahit.dto.request.GetMyProfileRequestDto;
import org.mucahit.dto.request.GetPostRequestDto;
import org.mucahit.dto.response.GetPostResponseDto;
import org.mucahit.manager.IUserProfileManager;
import org.mucahit.repository.IPostRepository;
import org.mucahit.repository.entity.Post;
import org.mucahit.repository.entity.UserProfile;
import org.mucahit.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostService extends ServiceManager<Post, String> {

    private final IPostRepository postRepository;
    private final PostResimService postResimService;
    private final IUserProfileManager userProfileManager;


    public PostService(IPostRepository postRepository, PostResimService postResimService, IUserProfileManager userProfileManager){
        super(postRepository);
        this.postRepository = postRepository;
        this.postResimService = postResimService;
        this.userProfileManager = userProfileManager;
    }

    public List<GetPostResponseDto> getPosts(GetPostRequestDto dto) {
        /**
         * öncelikle kullanıcıya dönülecek olan listenin boş halini oluşturuyoruz.
         */
        List<GetPostResponseDto> result = new ArrayList<>();
        /**
         * Tüm Postların listesini çekiyoruz.
         */
        List<Post> postList = postRepository.findAll();
        postList.forEach(post->{
            List<String> posturls = postResimService.getUrlsByPostId(post.getId());
            UserProfile userProfile = userProfileManager
                    .getOtherProfile(GetMyProfileRequestDto.builder()
                            .token(dto.getToken())
                            .userId(post.getUserId())
                            .build()).getBody();
            /**
             * Kullanıcıya dönülecek response dto oluşturuluyor.
             */
            GetPostResponseDto getPDto = GetPostResponseDto.builder()
                    .likecount(post.getBegeniSayisi())
                    .username(userProfile.getUsername())
                    .useravatar(userProfile.getAvatar())
                    .sharedtime(new Date(post.getPaylasimZamani())+"")
                    .posttext(post.getAciklama())
                    .posturls(posturls)
                    .build();
            result.add(getPDto);
        });
        return result;
    }
}
