package org.mucahit.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * !!!!!!!!!!! DİKKAT !!!!!!!!!!!!!
 * Bu model üzerinden RabbitMQ'ya mesaj ileteceğiz ve bu mesajı kuyruğa işlenmesini isteyeceğiz
 * Burada gönderilecek olan sınıf bilgisi rabbitMQ ya base64 olarak iletilecektir.
 * Bu nedenle, sınıfın serileştirilmesi gerekmektedir. Buna riayet etmek için
 * sınıfın Serializable interface'ini implemente etmesi gerekir.
 *
 * !!!!!!!!!! ÇOOOOKKK DİKKAT !!!!!!!!!!!!!!!
 * Yine burada oluşturulan sınıf için paket ismi ve sınıf içinde kullanılan
 * tüm parametrelerin aynı olması gerekir. Aksi takdirde nesneniz iletilen
 * tarafta okunamayacaktır.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserModel implements Serializable {
    Long authid;
    String username;
    String email;
}
