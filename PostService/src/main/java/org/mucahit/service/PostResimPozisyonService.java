package org.mucahit.service;

import org.mucahit.repository.IPostResimPozisyonRepository;
import org.mucahit.repository.entity.PostResimPozisyon;
import org.mucahit.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class PostResimPozisyonService extends ServiceManager<PostResimPozisyon, String> {

    private final IPostResimPozisyonRepository postResimPozisyonRepository;

    public PostResimPozisyonService(IPostResimPozisyonRepository postResimPozisyonRepository){
        super(postResimPozisyonRepository);
        this.postResimPozisyonRepository = postResimPozisyonRepository;
    }
}
