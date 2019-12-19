package pl.edu.wat.bookthevisit.services;

import pl.edu.wat.bookthevisit.dtos.DateFilterDto;
import pl.edu.wat.bookthevisit.dtos.VisitDto;
import pl.edu.wat.bookthevisit.exceptions.VisitOccupiedException;

import java.text.ParseException;
import java.util.List;

public interface VisitService
{
    void addVisitById(Integer id) throws VisitOccupiedException;
    List<VisitDto> showUnoccupiedVisits();
    VisitDto getVisitById(Integer id);
    List<VisitDto> showUnoccupiedVisitsLimitByDate(DateFilterDto dateFilterDto) throws ParseException;
    List<VisitDto> showMyVisits();
//    void deleteVisitById(Integer id);
}
