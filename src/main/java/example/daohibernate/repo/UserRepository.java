package example.daohibernate.repo;

import example.daohibernate.entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<UserEntity> getPersonsByCity(String city) {
        return entityManager.createQuery
                        ("SELECT p FROM UserEntity p WHERE p.cityOfLiving = :city", UserEntity.class)
                .setParameter("city", city)
                .getResultList();
    }
}
