package pl.edu.wat.bookthevisit.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pl.edu.wat.bookthevisit.entities.UserEntity;

@Component
public interface UsersRepository extends CrudRepository<UserEntity, Integer>
{
    UserEntity findByEmail(String email);
    boolean existsByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);

    @Modifying
    @Query("update pacient p set p.email = ?2, p.password = ?3 where p.email = ?1")
    String saveUpdate(String currentEmail, String newEmail, String newPassword);

}
