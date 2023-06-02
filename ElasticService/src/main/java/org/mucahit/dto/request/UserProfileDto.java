package org.mucahit.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mucahit.repository.entity.EGender;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileDto {

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
