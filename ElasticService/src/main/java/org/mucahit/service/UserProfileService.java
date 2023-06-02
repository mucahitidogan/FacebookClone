package org.mucahit.service;

import org.mucahit.dto.request.UserProfileDto;
import org.mucahit.mapper.IUserProfileMapper;
import org.mucahit.repository.IUserProfileRepository;
import org.mucahit.repository.entity.UserProfile;
import org.mucahit.utility.ServiceManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService extends ServiceManager<UserProfile, String> {

    private final IUserProfileRepository userProfileRepository;

    public UserProfileService(IUserProfileRepository userProfileRepository) {
        super(userProfileRepository);
        this.userProfileRepository = userProfileRepository;
    }

    public void save(UserProfileDto dto) {
        UserProfile user = IUserProfileMapper.INSTANCE.toUserProfile(dto);
        save(user);
    }

    /**
     * Sayfalama işlemleri;
     * Bir kayıt bilgisinde çok fazla datanın olması nedeniyle bilgilerin
     * belli bir sayfalama yöntemi ile çağrılması
     * Hangi sayfadasın? Page
     * Bir sayfada kaç kayıt var? Size
     * Gösterdiğin kayıtları sıralayacak mısın? Sort
     */

    public Page<UserProfile> findAll(int currentPage, int size, String sortParameter, String sortDirection) {
        /**
         * Sort      --> Sıralama işlemlerini belirtir.
         * Pageable  --> Sayfalama işlemlerini belirtir.
         */

        Sort sort = null;
        Pageable pageable = null;

        if (sortParameter != null)
            sort = Sort.by(Sort.Direction.fromString(sortDirection == null ? "ASC" : sortDirection), sortParameter);

        if(sort != null && currentPage != 0){
            pageable = PageRequest.of(currentPage, size == 0 ? 10 : size, sort);
        }else {
            pageable = PageRequest.of(currentPage, size == 0 ? 10 : size);
        }

        return userProfileRepository.findAll(pageable);
    }
}












