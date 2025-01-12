package example.daohibernate.repo;

import example.daohibernate.entity.Contact;
import example.daohibernate.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Contact> {
    List<UserEntity> findByCityOfLiving(String cityOfLiving);

    List<UserEntity> findByContactAgeLessThanOrderByContactAge(int age);

    Optional<UserEntity> findByContactNameAndContactSurname(String name, String surname);
}
