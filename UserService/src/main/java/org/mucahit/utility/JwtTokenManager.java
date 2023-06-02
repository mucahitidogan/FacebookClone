package org.mucahit.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenManager {
    /**
     * JWT token üretiminde kullanılacak olan kriptolama algoritmalarının
     * kullanacağın anahtar bilgisini burada veriyoruz.
     * !!!!!!!!! DİKKAT !!!!!!!!
     * Bu anahtar bilgisinin açık bir şekilde kod içinde var olması bir güvenlik açığıdır.
     * Anahtarın kod içinden alınarak ENV şeklinde başka bir repo üzerinden erişebilir
     * olması daha güvenli olacaktır.
     */
    @Value("${my-application.jwt.secret-key}")
    private String secretKey;
    private final String sifreAnahtari = "nI5afruwE##E3-#&-&If65s!6WrlB#&PhLtI4ecrA?iBr+4Ra+1pa?e3LYAK688$";

    private Long exDate = 1000L * 60 * 2; //2dk
    /**
     * JWT üretimi yapılırken belli bilgilerin payload içine eklenmesi gereklidir.
     * Yani token verdiğimiz kullanıcıya ait kimlik bilgilerini doğrulayabilecek
     * bilgiler olabilir. Ama dikkat edmeliyiz ki burada bulunan bilgiler açık
     * bir şekilde iletilmektedir. Bu nedenle Claim nesnelerinin içine eklenecek
     * bilgilerin özel gizli bilgiler olmamasına dikkat etmeliyiz.
     *
     * Erişim kısıtları ve sayfa geçişleri için belli parametrelerin token
     * içerisinde olması ve eşleştirilmesi gereklidir.
     *
     * @param id
     * @return
     */
    public Optional<String> createToken(Long id) {
        String token;
        try {
            token = JWT.create().withAudience()
                    .withClaim("id", id) // payload dataları
                    .withClaim("howtopage","AuthMicroservice") // payload dataları
                    .withClaim("isOne",true) // payload dataları
                    .withIssuer("Java7") // token üreten uygulama
                    .withIssuedAt(new Date()) // token oluşturma tarihi
                    .withExpiresAt(new Date(System.currentTimeMillis() + exDate)) // tokenın sona erme zamanı
                    .sign(Algorithm.HMAC512(sifreAnahtari));
            return Optional.of(token);
        }catch (Exception e){
            return Optional.empty();
        }
    }

    /**
     * İlk olarak bir JWT token bilgisinin bize ait olup olmadığının, kullanım süresinin
     * dolup dolmadığının kontrolünün yapılması gereklidir.
     * Token içinde önceden bizim tarafımızdan eklenen bilgilerin token claim nesneleri
     * içinden alınması gereklidir.
     * @param token
     * @return
     */
    public Optional<Long> getIdFromToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC512(sifreAnahtari);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("Java7")
                    .build();
           DecodedJWT decodedJWT = verifier.verify(token);
           if (decodedJWT == null) return Optional.empty();
           return Optional.of(decodedJWT.getClaim("id").asLong());
        }catch (Exception e){
            return Optional.empty();
        }
    }
}
