package pl.edu.wat.bookthevisit.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.wat.bookthevisit.entities.RoleEntity;
import java.util.List;

public interface RolesRepository extends CrudRepository<RoleEntity, Integer>
{
    List<RoleEntity> findAll();
    RoleEntity findAllByIdRole(Integer id);
    RoleEntity findAllByRole(String role);
}
