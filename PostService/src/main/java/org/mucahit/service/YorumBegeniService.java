package org.mucahit.service;

import org.mucahit.repository.IYorumBegeniRepository;
import org.mucahit.repository.entity.YorumBegeni;
import org.mucahit.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class YorumBegeniService extends ServiceManager<YorumBegeni, String> {

    private final IYorumBegeniRepository yorumBegeniRepository;

    public YorumBegeniService(IYorumBegeniRepository yorumBegeniRepository){
        super(yorumBegeniRepository);
        this.yorumBegeniRepository = yorumBegeniRepository;
    }
}
