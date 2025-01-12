package example.daohibernate.service;

import example.daohibernate.entity.Contact;
import example.daohibernate.entity.UserEntity;
import example.daohibernate.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<UserEntity> getUser(Contact contact) {
        return userRepository.findById(contact);
    }

    @Override
    public UserEntity updateUser(UserEntity user) {
        if (userRepository.existsById(user.getContact())) {
            return userRepository.save(user);
        }
        throw new RuntimeException("User not found");
    }

    @Override
    public void deleteUser(Contact contact) {
        if (userRepository.existsById(contact)) {
            userRepository.deleteById(contact);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public List<UserEntity> getUsersByCity(String cityOfLiving) {
        return userRepository.findByCityOfLiving(cityOfLiving);
    }

    @Override
    public List<UserEntity> getLessThanOrderByAge(int age) {
        return userRepository.findByContactAgeLessThanOrderByContactAge(age);
    }

    @Override
    public Optional<UserEntity> getNameAndSurname(String name, String surname) {
        return userRepository.findByContactNameAndContactSurname(name, surname);
    }


}
