package org.mucahit.repository.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Document
public class UserProfile {
    @Id
    String id;
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
