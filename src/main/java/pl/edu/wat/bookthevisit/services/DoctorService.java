package pl.edu.wat.bookthevisit.services;

import pl.edu.wat.bookthevisit.dtos.DoctorDto;
import pl.edu.wat.bookthevisit.exceptions.DoctorExistsException;

import java.util.List;

public interface DoctorService
{
    List<DoctorDto> getDoctors();
    DoctorDto getDoctorById(Integer id);
    List<DoctorDto> getDoctorsBySpec(DoctorDto doctorDto);
    void setNewDoctor(DoctorDto doctorDto) throws DoctorExistsException;
}
