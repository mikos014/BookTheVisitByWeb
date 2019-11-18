package pl.edu.wat.bookthevisit.services;

import pl.edu.wat.bookthevisit.dtos.VisitDto;

import java.util.List;

public interface VisitService
{
    boolean addVisit(VisitDto visitDto);
    List<VisitDto> showAllVisits();
    void deleteVisitById();
}
