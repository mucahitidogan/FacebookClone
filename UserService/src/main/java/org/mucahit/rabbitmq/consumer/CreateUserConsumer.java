package org.mucahit.rabbitmq.consumer;

import lombok.RequiredArgsConstructor;
import org.mucahit.rabbitmq.model.CreateUserModel;
import org.mucahit.service.UserProfileService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserConsumer {
    private final UserProfileService userProfileService;
    @RabbitListener(queues = "auth-queue-create-user")
    public void createUserFromHandleQueue(CreateUserModel userModel){
        userProfileService.save(userModel);
        System.out.println("Kullanıcı oluşturuldu.");
    }
}
