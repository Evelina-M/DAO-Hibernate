package example.daohibernate.service;

import example.daohibernate.entity.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> getPersonsByCity(String city);
}
