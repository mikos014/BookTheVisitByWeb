package pl.edu.wat.bookthevisit.services;

import pl.edu.wat.bookthevisit.dtos.VisitDto;
import pl.edu.wat.bookthevisit.exceptions.VisitOccupiedException;

import java.util.Date;
import java.util.List;

public interface VisitService
{
    void addVisitById(Integer id) throws VisitOccupiedException;
    List<VisitDto> showUnoccupiedVisits();
    List<VisitDto> showUnoccupiedVisitsLimitByDate(String spec, Date dateFrom, Date dateTo);
    List<VisitDto> showMyVisits();
//    void deleteVisitById(Integer id);
}
