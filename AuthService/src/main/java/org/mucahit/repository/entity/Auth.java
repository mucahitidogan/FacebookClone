package org.mucahit.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tblauth")
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotEmpty
    @Size(min = 3)
    String username;
    @NotBlank
    @Size(min = 8)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$",
            message = "Şifre en az bir büyük, bir küçük, harf, rakam, ve özel karakterden oluşmalıdır.")
    String password;
    @Email
    String email;
    /**
     * Kullanıcının kayıt edilme tarihini tutan değer. Long olarak tutulur.
     */
    Long createat;

    /**
     *  0- Kullanıcı kayıt edilmiş ama onaylanmamış
     *  1- Kullanıcı kayıt edilmiş ve onaylanmış
     *  2- Kullanıcı kayıt edilmiş ve onaylanmış fakat hesabı kilitlenmiş
     *  3- Kullanıcı kayıt edilmiş ve onaylanmış fakat hesabı silinmiş
     */
    int status;

//    /**
//     * Kullanıcının durumu yukarıdaki değişken ile tutabileceği gibi
//     * bir enum ile de tutulabilir. Aşaağıda tanımlanan Enum içinde,
//     * Aktif, Pasif ve Silinmiş durumları tutabiliriz.
//     */
//    @Enumerated
//    State state;
}
