package org.mucahit.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "postresimpozisyon")
public class PostResimPozisyon {

    @Id
    String id;
    String postResimId;
    String userId;
    int x;
    int y;
}
