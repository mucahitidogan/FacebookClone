package org.mucahit.controller;

import lombok.RequiredArgsConstructor;
import org.mucahit.dto.request.GetPostRequestDto;
import org.mucahit.dto.response.GetPostResponseDto;
import org.mucahit.repository.entity.Post;
import org.mucahit.repository.entity.PostResim;
import org.mucahit.service.PostResimService;
import org.mucahit.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.mucahit.constants.EndPointList.*;

@RestController
@RequestMapping(POST)
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostResimService postResimService;
    @PostMapping("/get-posts")
    @CrossOrigin("*")
    public ResponseEntity<List<GetPostResponseDto>> getPosts(@RequestBody @Valid GetPostRequestDto dto){
        return ResponseEntity.ok(postService.getPosts(dto));
    }

    @GetMapping("/new-post")
    @CrossOrigin("*")
    public ResponseEntity<Void> newPost(String aciklama, String  userid, String url, String url2){
        Post post = new Post();
        post.setAciklama(aciklama);
        post.setUserId(userid);
        post.setPaylasimZamani(System.currentTimeMillis());
        postService.save(post);
        /**
         * Post kayıt işleminden sonra postun id bilgisi atanmış olur.
         */
        PostResim postResim = new PostResim();
        postResim.setPostId(post.getId());
        postResim.setUrl(url);
        postResimService.save(postResim);
        postResim = new PostResim();
        postResim.setPostId(post.getId());
        postResim.setUrl(url2);
        postResimService.save(postResim);
        return ResponseEntity.ok().build();

    }
}
