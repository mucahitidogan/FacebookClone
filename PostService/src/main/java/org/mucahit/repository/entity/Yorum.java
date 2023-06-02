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
@Document(collection = "yorum")
public class Yorum {
    @Id
    String id;
    String postId;
    String userId;
    String parentId;
    String icerik;
    Long paylasmaZamani;
    int begeniSayisi;
}
