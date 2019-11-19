package pl.edu.wat.bookthevisit.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pl.edu.wat.bookthevisit.entities.UserEntity;

@Component
public interface UsersRepository extends CrudRepository<UserEntity, Integer>
{
    UserEntity findByEmail(String email);
    boolean existsByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
}
