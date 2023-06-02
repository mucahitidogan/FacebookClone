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
@Document(collection = "post")
public class Post {

    @Id
    String id;
    String userId;
    Long paylasimZamani;
    /**
     * Integer - int arasındaki fark nedir?
     * int -> bu değer DBde olmasa bile default olarak 0 gelecektir.
     */
    int begeniSayisi;
    String aciklama;
    int yorumSayisi;
}
