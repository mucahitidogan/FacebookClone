package org.mucahit.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetMyProfileResponseDto {

    String avatar;
    String name;
    String username;
    String about;

}
