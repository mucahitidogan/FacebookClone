package org.mucahit.service;

import org.mucahit.repository.IKayitliPostlarRepository;
import org.mucahit.repository.entity.KayitliPostlar;
import org.mucahit.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class KayitliPostlarService extends ServiceManager<KayitliPostlar, String> {

    private final IKayitliPostlarRepository kayitliPostlarRepository;

    public KayitliPostlarService(IKayitliPostlarRepository kayitliPostlarRepository){
        super(kayitliPostlarRepository);
        this.kayitliPostlarRepository = kayitliPostlarRepository;
    }
}
