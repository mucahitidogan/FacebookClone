package org.mucahit.manager;

import org.mucahit.repository.entity.UserProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import static org.mucahit.constants.EndPointList.*;

@FeignClient(
        name = "elastic-service",
        url = "http://localhost:9099/elastic"
)
public interface IElasticServiceManager {

    @PostMapping("/userprofile"+SAVE)
    public ResponseEntity<Void> save(@RequestBody UserProfile dto);
}
