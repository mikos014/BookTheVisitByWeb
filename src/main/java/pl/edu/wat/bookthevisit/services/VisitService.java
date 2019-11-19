package pl.edu.wat.bookthevisit.services;

import pl.edu.wat.bookthevisit.dtos.DoctorDto;
import pl.edu.wat.bookthevisit.dtos.UserDto;
import pl.edu.wat.bookthevisit.dtos.VisitDto;

import java.util.List;

public interface VisitService
{
    boolean addVisit(VisitDto visitDto, DoctorDto doctorDto, UserDto userDto);
    List<VisitDto> showAllVisits();
    void deleteVisitById(Integer id);
}
