package example.daohibernate.entity;

import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataEntity implements CommandLineRunner {

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        UserEntity eva = UserEntity.builder().contact(Contact.builder()
                        .name("Eva")
                        .surname("Son")
                        .age(15)
                        .build())
                .phoneNumber("+7 (111) 111-11-11")
                .cityOfLiving("Moscow")
                .build();

        UserEntity andrey = UserEntity.builder().contact(Contact.builder()
                        .name("Andrey")
                        .surname("Filin")
                        .age(25)
                        .build())
                .phoneNumber("+7 (999) 999-99-99")
                .cityOfLiving("Moscow")
                .build();

        UserEntity vlad = UserEntity.builder().contact(Contact.builder()
                        .name("Vladislav")
                        .surname("Ashotov")
                        .age(53)
                        .build())
                .phoneNumber("+7 (666) 666-66-66")
                .cityOfLiving("Ryazan")
                .build();
    }
}
