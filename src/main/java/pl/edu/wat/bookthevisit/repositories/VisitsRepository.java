package pl.edu.wat.bookthevisit.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.wat.bookthevisit.entities.DoctorEntity;
import pl.edu.wat.bookthevisit.entities.UserEntity;
import pl.edu.wat.bookthevisit.entities.VisitEntity;

import java.util.Date;
import java.util.List;

@Component
public interface VisitsRepository extends CrudRepository<VisitEntity, Integer>
{
//    boolean existsByDateAndDoctor(Date date, DoctorEntity doctorEntity);
    boolean existsByIdVisitAndOccupiedIsTrue(Integer id);
    VisitEntity findAllByIdVisit(Integer id);
    List<VisitEntity> findByOccupiedIsFalseOrderByDateAscTimeAsc();
    List<VisitEntity> findByPacientOrderByDateAscTimeAsc(UserEntity userEntity);
    List<VisitEntity> findByDateBetweenAndOccupiedIsFalseOrderByDateAscTimeAsc(Date dateFrom, Date dateTo);
    List<VisitEntity> findAll();
    boolean existsByDateAndTimeAndDoctor(Date date, String time, DoctorEntity doctorEntity);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Visit v SET v.occupied = true, v.pacient = :user WHERE v.idVisit = :id")
    void addVisit(@Param("id") Integer idV, @Param("user") UserEntity userEntity);
}