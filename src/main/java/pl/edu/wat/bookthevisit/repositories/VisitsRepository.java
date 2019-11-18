package pl.edu.wat.bookthevisit.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pl.edu.wat.bookthevisit.dtos.VisitDto;

@Component
public interface VisitsRepository extends CrudRepository<VisitDto, Integer>
{

}
