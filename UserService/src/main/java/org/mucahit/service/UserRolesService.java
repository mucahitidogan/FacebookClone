package org.mucahit.service;

import org.mucahit.repository.IUserRolesRepository;
import org.mucahit.repository.entity.UserRoles;
import org.mucahit.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class UserRolesService extends ServiceManager<UserRoles, String> {

    private final IUserRolesRepository userRolesRepository;

    public UserRolesService(IUserRolesRepository userRolesRepository) {
        super(userRolesRepository);
        this.userRolesRepository = userRolesRepository;
    }


}
