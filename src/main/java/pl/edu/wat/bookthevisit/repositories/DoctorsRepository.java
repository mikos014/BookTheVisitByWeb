package pl.edu.wat.bookthevisit.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pl.edu.wat.bookthevisit.entities.DoctorEntity;

import java.util.List;

@Component
public interface DoctorsRepository extends CrudRepository<DoctorEntity, Integer>
{
    List<DoctorEntity> findAll();
    DoctorEntity findAllByIdDoctor(Integer id);
    List<DoctorEntity> findBySpec(String spec);
    boolean existsAllByNameAndSpecAndSurname(String name, String spec, String surname);
}
