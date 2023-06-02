package org.mucahit.service;

import org.mucahit.repository.IYorumRepository;
import org.mucahit.repository.entity.Yorum;
import org.mucahit.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class YorumService extends ServiceManager<Yorum, String> {

    private final IYorumRepository yorumRepository;

    public YorumService(IYorumRepository yorumRepository){
        super(yorumRepository);
        this.yorumRepository = yorumRepository;
    }
}
