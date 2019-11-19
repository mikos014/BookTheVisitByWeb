package pl.edu.wat.bookthevisit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.bookthevisit.dtos.DoctorDto;
import pl.edu.wat.bookthevisit.dtos.UserDto;
import pl.edu.wat.bookthevisit.dtos.VisitDto;
import pl.edu.wat.bookthevisit.entities.DoctorEntity;
import pl.edu.wat.bookthevisit.entities.UserEntity;
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
    public boolean addVisit(VisitDto visitDto, DoctorDto doctorDto, UserDto userDto)
    {
        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setIdDoctor(doctorDto.getId());
        doctorEntity.setName(doctorDto.getName());
        doctorEntity.setSpec(doctorDto.getSpec());
        doctorEntity.setSurname(doctorDto.getSurname());

        UserEntity userEntity = new UserEntity();
        userEntity.setIdPacient(userDto.getId());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setName(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());

        if (!visitsRepository.existsVisitEntityByDateAndDoctor(visitDto.getDate(), doctorEntity))
        {
            VisitEntity visitEntity = new VisitEntity();
            visitEntity.setDate((Date) visitDto.getDate());
            visitEntity.setDoctor(doctorEntity);
            visitEntity.setPacient(userEntity);

            visitsRepository.save(visitEntity);
            return true;
        }

        return false;
    }

    @Override
    public List<VisitDto> showAllVisits()
    {
        List<VisitDto> visitDtoList = new ArrayList<>();
        visitsRepository.findAll()
                        .forEach(v -> visitDtoList.add(new VisitDto(v.getIdVisit(), v.getDate(), v.getDoctor(), null)));
        return visitDtoList;
    }

    @Override
    public void deleteVisitById(Integer id)
    {
        visitsRepository.deleteById(id);
    }
}
