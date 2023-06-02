package org.mucahit.service;

import org.mucahit.dto.request.LoginRequestDto;
import org.mucahit.dto.request.RegisterRequestDto;
import org.mucahit.dto.request.UserProfileSaveRequestDto;
import org.mucahit.exception.AuthException;
import org.mucahit.exception.ErrorType;
import org.mucahit.manager.IUserProfileManager;
import org.mucahit.mapper.IAuthMapper;
import org.mucahit.rabbitmq.model.CreateUserModel;
import org.mucahit.rabbitmq.producer.CreateUserProducer;
import org.mucahit.repository.IAuthRepository;
import org.mucahit.repository.entity.Auth;
import org.mucahit.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth, Long> {
    private final IAuthRepository authRepository;
    private final IUserProfileManager userProfileManager;
    private final CreateUserProducer createUserProducer;
    public AuthService(IAuthRepository authRepository, IUserProfileManager userProfileManager, CreateUserProducer createUserProducer){
        super(authRepository);
        this.authRepository = authRepository;
        this.userProfileManager = userProfileManager;
        this.createUserProducer = createUserProducer;
    }

    public void register(RegisterRequestDto dto){
        if(authRepository.existsByUsername(dto.getUsername()))//if şart
            throw new AuthException(ErrorType.ERROR_USERNAME); //if blok
        Auth auth = save(IAuthMapper.INSTANCE.toAuth(dto));//if'e girerse buraya gelmez hata fırlattığı için
        /**
         * Bir kullanıcı uygulamamızda üyelik açtıktan sonra bu kullanıcıya ait bilgiler ile
         * userprofil bilgisinin de oluşturulması gerekiyor. Bunu sağlamak için UserProfile
         * servisine istek atmak üzere FeignClient kullanıyoruz.
         *
         * Kaydetme işlemi için, manager bizden DTO istemektedir. Bu nedenle auth için
         * yapılan kayıt bilgilerini dtonun içine koyarak istek atıyoruz.
         */
        UserProfileSaveRequestDto requestDto = UserProfileSaveRequestDto.builder()
                /**
                 *  .username(auth.getUsername())
                 *  .email(auth.getEmail())
                 *  .authid(auth.getId())
                 */
                .username(auth.getUsername())
                .email(auth.getEmail())
                .authid(auth.getId())
                .build();
        /**
         * Bu kısımda, DTO içindeki alanlara gerekli plan datalar girilir.
         * FeignClient bizim için verdiğimiz parametreleri iletişime geçeceğimiz UserProfile servisinin save methoduna
         * jsonObject olarak gönderir ve böylece o save methodunun çalışmasını sağlar
         */
    //    userProfileManager.save(requestDto);
        createUserProducer.convertAndSendData(CreateUserModel.builder()
                        .authid(auth.getId())
                        .email(auth.getEmail())
                        .username(auth.getUsername())
                .build());
    }

    public Optional<Auth> doLogin(LoginRequestDto dto){
        return authRepository.findOptionalByUsernameAndPassword(
                dto.getUsername(), dto.getPassword());
    }
}
