package org.mucahit.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mucahit.repository.entity.EGender;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileUpdateRequestDto {
    @NotEmpty
    @Size(min = 5)
    String token;
    String name;
    String surname;
    String phone;
    String address;
    String avatar;
    EGender gender;
}
