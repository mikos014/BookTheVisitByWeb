package pl.edu.wat.bookthevisit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.bookthevisit.dtos.DoctorDto;
import pl.edu.wat.bookthevisit.entities.DoctorEntity;
import pl.edu.wat.bookthevisit.repositories.DoctorsRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService
{
    private final DoctorsRepository doctorsRepository;

    @Autowired
    public DoctorServiceImpl(DoctorsRepository doctorsRepository)
    {
        this.doctorsRepository = doctorsRepository;
    }

    @Override
    public List<DoctorDto> getDoctors()
    {
        List<DoctorDto> doctorDtoList = new ArrayList<>();

        doctorsRepository.findAll().forEach(d -> doctorDtoList.add(new DoctorDto(d.getIdDoctor(), d.getName(), d.getSpec(), d.getSurname())));

        return doctorDtoList;
    }

    @Override
    public DoctorDto getDoctorById(Integer id) {
        DoctorEntity doctorEntity = doctorsRepository.findAllByIdDoctor(id);
        return new DoctorDto(doctorEntity.getIdDoctor(), doctorEntity.getName(), doctorEntity.getSpec(), doctorEntity.getSurname());
    }

    @Override
    public List<DoctorDto> getDoctorsBySpec(DoctorDto doctorDto)
    {
        List<DoctorDto> doctorDtoList = new ArrayList<>();

        doctorsRepository.findBySpec(doctorDto.getSpec()).forEach(d -> doctorDtoList.add(new DoctorDto(d.getIdDoctor(), d.getName(), d.getSpec(), d.getSurname())));

        return doctorDtoList;
    }
}
