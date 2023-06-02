package org.mucahit.repository.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Document(indexName = "userprofile")
public class UserProfile {
    @Id
    String id;
    String userid;
    Long authid;
    String username;
    String email;
    String name;
    String surname;
    String phone;
    String address;
    String avatar;
    EGender gender;

}
