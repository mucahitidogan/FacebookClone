package org.mucahit.utility;

import lombok.RequiredArgsConstructor;
import org.mucahit.manager.IElasticServiceManager;
import org.mucahit.service.UserProfileService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Component olarak işaretlenen bu sınıf, Spring Contexti başlatıldığında çalışır.
 */
@Component
@RequiredArgsConstructor
public class Runner {

    private final UserProfileService userProfileService;

    private final IElasticServiceManager elasticServiceManager;

    /**
     * @PostContruct anotasyonu ile işaretlenen bu method bu sınıftan bir nesne oluşturulurken çalışır.
     * Böylece Spring uygulaması ayağa kalkarken bu method çalışır.
     * Program çalıştığında veri tabanındaki tüm veriyi çekip elasticsearch veritabanına kaydedecek.
     */

    //@PostConstruct
    public void init(){
        userProfileService.findAll().forEach(x ->{
            elasticServiceManager.save(x);
        });
    }
}
