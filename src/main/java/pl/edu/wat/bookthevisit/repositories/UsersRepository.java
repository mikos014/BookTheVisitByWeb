package pl.edu.wat.bookthevisit.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.wat.bookthevisit.entities.UserEntity;

@Component
public interface UsersRepository extends CrudRepository<UserEntity, Integer>
{
    UserEntity findByEmail(String email);
    boolean existsByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Pacient p SET p.email = :nEmail, p.password = :nPassword WHERE p.email = :cEmail")
    void saveUpdate(@Param("cEmail") String currentEmail, @Param("nEmail") String newEmail, @Param("nPassword") String newPassword);

}
