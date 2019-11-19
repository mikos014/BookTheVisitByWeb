package pl.edu.wat.bookthevisit.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pl.edu.wat.bookthevisit.entities.DoctorEntity;
import pl.edu.wat.bookthevisit.entities.VisitEntity;

import java.util.Date;

@Component
public interface VisitsRepository extends CrudRepository<VisitEntity, Integer>
{
    boolean existsVisitEntityByDateAndDoctor(Date date, DoctorEntity doctorEntity);
}
