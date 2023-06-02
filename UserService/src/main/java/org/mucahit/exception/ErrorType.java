package org.mucahit.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {

    USER_NOT_FOUND(2001,"Böyle bir kullanıcı bulunamadı", HttpStatus.NOT_FOUND),
    BAD_REQUEST(2002,"Geçersiz istek ya da parametre", HttpStatus.BAD_REQUEST),
    ERROR_INVALID_TOKEN(2003,"Geçersiz token bilgisi", HttpStatus.UNAUTHORIZED),
    ERROR_NOT_FOUND_USERNAME(2004,"Böyle bir kullanıcı bulunmamaktadır.", HttpStatus.INTERNAL_SERVER_ERROR),
    ERROR_ACCESS_DENIED(2005,"Yetkisiz Erişim. Lütfen geçerli bir kullanıcı ile tekrar deneyim.", HttpStatus.UNAUTHORIZED),
    ERROR(9000, "Beklenmeyen bir hata oluştu. Lütfen tekrar deneyiniz.", HttpStatus.INTERNAL_SERVER_ERROR)  ;

    int code;
    String message;
    HttpStatus httpStatus;

}
