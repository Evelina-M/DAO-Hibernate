package example.daohibernate.service;

import example.daohibernate.entity.UserEntity;
import example.daohibernate.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> getPersonsByCity(String city) {
        return userRepository.getPersonsByCity(city);
    }
}
