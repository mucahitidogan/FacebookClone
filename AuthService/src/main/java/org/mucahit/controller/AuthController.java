package org.mucahit.controller;

import lombok.RequiredArgsConstructor;
import org.mucahit.dto.request.LoginRequestDto;
import org.mucahit.dto.request.RegisterRequestDto;
import org.mucahit.dto.response.LoginResponseDto;
import org.mucahit.dto.response.RegisterResponseDto;
import org.mucahit.exception.AuthException;
import org.mucahit.exception.ErrorType;
import org.mucahit.repository.entity.Auth;
import org.mucahit.service.AuthService;
import org.mucahit.utility.JwtTokenManager;
import org.mucahit.utility.TokenCreator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtTokenManager jwtTokenManager;

    @GetMapping("/getpage")
    public ResponseEntity<String> getPage(){
        return ResponseEntity.ok("Auth Service'e ulaştınız.");
    }
    @PostMapping("/login")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto dto){
        Optional<Auth> auth = authService.doLogin(dto);
        if(auth.isEmpty())
            return ResponseEntity.ok(LoginResponseDto.builder()
                            .statusCode(4000)
                            .message("Kullanıcı adı veya şifre hatalı")
                    .build());
        return ResponseEntity.ok(LoginResponseDto.builder()
                .statusCode(2001)
                .message(jwtTokenManager.createToken(auth.get().getId()).get())
                .build());
    }

    @PostMapping("/register")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto){
        if(!dto.getPassword().equals(dto.getRepassword()))
            throw new AuthException(ErrorType.ERROR_PASSWORD);
        authService.register(dto);
        return ResponseEntity.ok(RegisterResponseDto.builder()
                .statusCode(2000)
                .message("Kayıt işlemi başarılı şekilde gerçekleşti. " +
                        "Lütfen E-Posta adresinize gelin linkten aktivasyonunuzu tamamlayınız.")
                .build());

    }
}
