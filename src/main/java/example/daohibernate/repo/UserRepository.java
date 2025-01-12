package example.daohibernate.repo;

import example.daohibernate.entity.Contact;
import example.daohibernate.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Contact> {
    @Query("SELECT u FROM UserEntity u WHERE u.cityOfLiving = :city")
    List<UserEntity> findByCity(@Param("city") String city);

    @Query("SELECT u FROM UserEntity u WHERE u.contact.age < :age ORDER BY u.contact.age ASC")
    List<UserEntity> findByAgeLessThanOrderByAge(@Param("age") int age);

    @Query("SELECT u FROM UserEntity u WHERE u.contact.name = :name AND u.contact.surname = :surname")
    Optional<UserEntity> findByNameAndSurname(@Param("name") String name, @Param("surname") String surname);
}
