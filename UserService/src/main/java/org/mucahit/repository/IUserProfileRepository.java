package org.mucahit.repository;

import org.mucahit.repository.entity.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface IUserProfileRepository extends MongoRepository<UserProfile, String> {

    List<UserProfile> findByNameContainsIgnoreCase(String searchBarValue);
    List<UserProfile> findBySurnameContainsIgnoreCase(String searchBarValue);

    Optional<UserProfile> findOptionalByAuthid(Long authid);
}
