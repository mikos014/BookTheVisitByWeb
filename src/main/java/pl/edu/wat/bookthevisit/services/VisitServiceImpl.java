package pl.edu.wat.bookthevisit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.edu.wat.bookthevisit.dtos.VisitDto;
import pl.edu.wat.bookthevisit.entities.DoctorEntity;
import pl.edu.wat.bookthevisit.entities.UserEntity;
import pl.edu.wat.bookthevisit.exceptions.VisitOccupiedException;
import pl.edu.wat.bookthevisit.repositories.UsersRepository;
import pl.edu.wat.bookthevisit.repositories.VisitsRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VisitServiceImpl implements VisitService
{
    private final VisitsRepository visitsRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public VisitServiceImpl(VisitsRepository visitsRepository, UsersRepository usersRepository)
    {
        this.visitsRepository = visitsRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public void addVisitById(Integer idVisit) throws VisitOccupiedException
    {
        boolean isOccupied = visitsRepository.existsByIdVisitAndOccupiedIsTrue(idVisit);

        if(isOccupied)
        {
            throw new VisitOccupiedException("Visit is occupied ! Cannot book this visit");
        }

        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity userEntity = usersRepository.findByEmail(userEmail);

        visitsRepository.addVisit(idVisit, userEntity);
    }

    @Override
    public List<VisitDto> showUnoccupiedVisits()
    {
        List<VisitDto> visitDtoList = new ArrayList<>();

        visitsRepository.findByOccupiedIsFalse()
                        .forEach(v -> visitDtoList.add(new VisitDto(v.getIdVisit(), v.getDate(), v.getTime(), v.getDoctor())));
        return visitDtoList;
    }

    @Override
    public List<VisitDto> showUnoccupiedVisitsLimitByDate(String spec, Date dateFrom, Date dateTo)
    {
        List<VisitDto> visitDtoList = new ArrayList<>();

        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setSpec(spec);

        visitsRepository.findByDateBetweenAndAndDoctor(dateFrom, dateTo, doctorEntity)
                .forEach(v -> visitDtoList.add(new VisitDto(v.getIdVisit(), v.getDate(), v.getTime(), v.getDoctor())));
        return visitDtoList;
    }

    @Override
    public List<VisitDto> showMyVisits()
    {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity userEntity = usersRepository.findByEmail(userEmail);

        List<VisitDto> visitDtoList = new ArrayList<>();

        visitsRepository.findByPacient(userEntity)
                .forEach(v -> visitDtoList.add(new VisitDto(v.getIdVisit(), v.getDate(), v.getTime(), v.getDoctor())));
        return visitDtoList;
    }

//    @Override
//    public void deleteVisitById(Integer id)
//    {
//        visitsRepository.deleteById(id);
//    }
}
