package pl.edu.wat.bookthevisit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.bookthevisit.dtos.VisitDto;
import pl.edu.wat.bookthevisit.entities.VisitEntity;
import pl.edu.wat.bookthevisit.repositories.VisitsRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class VisitServiceImpl implements VisitService
{
    private final VisitsRepository visitsRepository;

    @Autowired
    public VisitServiceImpl(VisitsRepository visitsRepository)
    {
        this.visitsRepository = visitsRepository;
    }

    @Override
    public boolean addVisit(VisitDto visitDto)
    {
        if ()
        {
            VisitEntity visitEntity = new VisitEntity();
            visitEntity.setDate((Date) visitDto.getDate());
            visitEntity.setDoctor();
            visitEntity.setPacient();
            return true;
        }

        return false;
    }

    @Override
    public List<VisitDto> showAllVisits()
    {
        List<VisitDto> visitDtoList = new ArrayList<>();
        visitsRepository.findAll()
                        .forEach(v -> visitDtoList.add(new VisitDto(v.getDate(), v.getDoctorDto(), v.getUserDto())));
        return null;
    }

    @Override
    public void deleteVisitById()
    {

    }
}
