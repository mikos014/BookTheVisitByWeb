package pl.edu.wat.bookthevisit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.bookthevisit.dtos.DoctorDto;
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

        doctorsRepository.findAll().forEach(d -> doctorDtoList.add(new DoctorDto(d.getName(), d.getSpec(), d.getSurname())));

        return doctorDtoList;
    }

    @Override
    public List<DoctorDto> getDoctorsBySpec(String spec)
    {
        List<DoctorDto> doctorDtoList = new ArrayList<>();

        doctorsRepository.findBySpec(spec).forEach(d -> doctorDtoList.add(new DoctorDto(d.getName(), d.getSpec(), d.getSurname())));

        return doctorDtoList;
    }
}
