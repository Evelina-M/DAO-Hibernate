package example.daohibernate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PERSONS")
@Getter
@Setter
@Builder
public class UserEntity {

    @EmbeddedId
    private Contact contact;

    @Column(length = 25, name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(length = 50, name = "city_of_living", nullable = false)
    private String cityOfLiving;
}
