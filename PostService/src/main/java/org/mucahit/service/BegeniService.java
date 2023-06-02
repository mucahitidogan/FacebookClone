package org.mucahit.service;

import org.mucahit.repository.IBegeniRepository;
import org.mucahit.repository.entity.Begeni;
import org.mucahit.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class BegeniService extends ServiceManager<Begeni, String> {

    private final IBegeniRepository begeniRepository;

    public BegeniService(IBegeniRepository begeniRepository){
        super(begeniRepository);
        this.begeniRepository = begeniRepository;
    }
}
