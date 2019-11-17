package pl.edu.wat.bookthevisit.services;

import pl.edu.wat.bookthevisit.dtos.DoctorDto;

import java.util.List;

public interface DoctorService
{
    List<DoctorDto> getDoctors();
    List<DoctorDto> getDoctorsBySpec(String spec);
}
