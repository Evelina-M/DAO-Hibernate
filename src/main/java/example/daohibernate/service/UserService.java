package example.daohibernate.service;

import example.daohibernate.entity.Contact;
import example.daohibernate.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserEntity createUser(UserEntity user);

    Optional<UserEntity> getUser(Contact contact);

    UserEntity updateUser(UserEntity user);

    void deleteUser(Contact contact);

    List<UserEntity> getUsersByCity(String cityOfLiving);

    List<UserEntity> getLessThanOrderByAge(int age);

    Optional<UserEntity> getNameAndSurname(String name, String surname);
}
