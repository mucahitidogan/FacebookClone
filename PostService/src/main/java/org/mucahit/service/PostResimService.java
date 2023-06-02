package org.mucahit.service;

import org.mucahit.repository.IPostResimRepository;
import org.mucahit.repository.entity.PostResim;
import org.mucahit.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostResimService extends ServiceManager<PostResim, String> {

    private final IPostResimRepository postResimRepository;

    public PostResimService(IPostResimRepository postResimRepository){
        super(postResimRepository);
        this.postResimRepository = postResimRepository;
    }

    public List<String> getUrlsByPostId(String postId){
        /**
         * Burada resimlerin listesi string olarak dönmüyor. Entity olarak datalar dönüyor.
         * Bu nedenle buradaki bilgileri bir string listesi haline çevirmemiz gerekli
         */
        List<PostResim> postResimList = postResimRepository.findAllByPostId(postId);
        List<String> urlList = new ArrayList<>();
        postResimList.forEach(x -> {
            urlList.add(x.getUrl());
        });
        return urlList;
    }
}
