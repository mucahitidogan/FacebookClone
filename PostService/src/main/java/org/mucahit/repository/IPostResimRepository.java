package org.mucahit.repository;

import org.mucahit.repository.entity.PostResim;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostResimRepository extends MongoRepository<PostResim, String> {

    List<PostResim> findAllByPostId(String postId);
}
