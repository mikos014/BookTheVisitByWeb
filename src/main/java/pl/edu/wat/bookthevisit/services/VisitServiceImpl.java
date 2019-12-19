package pl.edu.wat.bookthevisit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.edu.wat.bookthevisit.dtos.DateFilterDto;
import pl.edu.wat.bookthevisit.dtos.VisitDto;
import pl.edu.wat.bookthevisit.entities.UserEntity;
import pl.edu.wat.bookthevisit.entities.VisitEntity;
import pl.edu.wat.bookthevisit.exceptions.VisitOccupiedException;
import pl.edu.wat.bookthevisit.repositories.DoctorsRepository;
import pl.edu.wat.bookthevisit.repositories.UsersRepository;
import pl.edu.wat.bookthevisit.repositories.VisitsRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class VisitServiceImpl implements VisitService
{
    private final VisitsRepository visitsRepository;
    private final UsersRepository usersRepository;
    private final DoctorsRepository doctorsRepository;

    @Autowired
    public VisitServiceImpl(VisitsRepository visitsRepository, UsersRepository usersRepository, DoctorsRepository doctorsRepository)
    {
        this.visitsRepository = visitsRepository;
        this.usersRepository = usersRepository;
        this.doctorsRepository = doctorsRepository;
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

        visitsRepository.findByOccupiedIsFalseOrderByDateAscTimeAsc()
                        .forEach(v -> visitDtoList.add(new VisitDto(v.getIdVisit(), v.getDate(), v.getTime(), v.getDoctor().getIdDoctor())));
        return visitDtoList;
    }

    @Override
    public VisitDto getVisitById(Integer id)
    {
        VisitEntity visitEntity = visitsRepository.findAllByIdVisit(id);

        return new VisitDto(visitEntity.getIdVisit(), visitEntity.getDate(), visitEntity.getTime(), visitEntity.getDoctor().getIdDoctor());
    }

    @Override
    public List<VisitDto> showUnoccupiedVisitsLimitByDate(DateFilterDto dateFilterDto) throws ParseException {
        List<VisitDto> visitDtoList = new LinkedList<>();
        List<Integer> list;

        Date dateFrom = dateFilterDto.getDateFrom() == null ? convert(new Date()) : convert(dateFilterDto.getDateFrom());
        Date dateTo = dateFilterDto.getDateTo() == null ? convert(new Date()) : convert(dateFilterDto.getDateTo());

        visitsRepository.findByDateBetweenAndOccupiedIsFalseOrderByDateAscTimeAsc(dateFrom, dateTo)
                .forEach(v -> visitDtoList.add(new VisitDto(v.getIdVisit(), v.getDate(), v.getTime(), v.getDoctor().getIdDoctor())));

        if (dateFilterDto.getSpec() != null && visitDtoList.size() != 0) {
            list = new ArrayList<>();
            doctorsRepository.findBySpec(dateFilterDto.getSpec())
                    .forEach(d -> list.add(d.getIdDoctor()));

            for (int i = 0; i < visitDtoList.size(); i++) {
                int abc = list.indexOf(visitDtoList.get(i).getDoctor());
                if (abc == -1) {
                    visitDtoList.remove(visitDtoList.get(i));
                }
            }
        }
        return visitDtoList;
    }

    @Override
    public List<VisitDto> showMyVisits()
    {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity userEntity = usersRepository.findByEmail(userEmail);

        List<VisitDto> visitDtoList = new ArrayList<>();

        visitsRepository.findByPacientOrderByDateAscTimeAsc(userEntity)
                .forEach(v -> visitDtoList.add(new VisitDto(v.getIdVisit(), v.getDate(), v.getTime(), v.getDoctor().getIdDoctor())));
        return visitDtoList;
    }

    private Date convert(Date date) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String d = sdf.format(date);
        return new SimpleDateFormat("yyyy-MM-dd").parse(d);
    }

//    @Override
//    public void deleteVisitById(Integer id)
//    {
//        visitsRepository.deleteById(id);
//    }
}
