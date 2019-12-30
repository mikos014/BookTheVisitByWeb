package pl.edu.wat.bookthevisit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.bookthevisit.dtos.DateFilterDto;
import pl.edu.wat.bookthevisit.dtos.VisitDto;
import pl.edu.wat.bookthevisit.exceptions.VisitExistsException;
import pl.edu.wat.bookthevisit.exceptions.VisitOccupiedException;
import pl.edu.wat.bookthevisit.services.VisitService;

import java.text.ParseException;
import java.util.List;

@RestController
public class VisitController
{
    private final VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService)
    {
        this.visitService = visitService;
    }

    @PostMapping("/api/addVisit/{idVisit}")
    public ResponseEntity addVisit(@PathVariable ("idVisit") Integer id)
    {
        try
        {
            visitService.addVisitById(id);
        }
        catch (VisitOccupiedException e)
        {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/api/getVisit/{idVisit}")
    public ResponseEntity<VisitDto> getVisit(@PathVariable ("idVisit") Integer id)
    {

        return new ResponseEntity<>(visitService.getVisitById(id), HttpStatus.OK);
    }

    @PostMapping("/api/getVisitsFiltered")
    public ResponseEntity<List<VisitDto>> showUnoccuppiedVisitsByFilter(@RequestBody DateFilterDto dateFilterDto) throws ParseException {
        List<VisitDto> visitDtoList = visitService.showUnoccupiedVisitsLimitByDate(dateFilterDto);

        if (visitDtoList != null)
        {
            return new ResponseEntity<>(visitDtoList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/getVisits")
    public ResponseEntity<List<VisitDto>> showUnoccuppiedVisits()
    {
        List<VisitDto> visitDtoList = visitService.showUnoccupiedVisits();

        if (visitDtoList != null)
        {
            return new ResponseEntity<>(visitDtoList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/getMyVisits")
    public ResponseEntity<List<VisitDto>> showMyVisits()
    {
        List<VisitDto> visitDtoList = visitService.showMyVisits();

        if (visitDtoList != null)
        {
            return new ResponseEntity<>(visitDtoList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/getAllVisits")
    public ResponseEntity<List<VisitDto>> showAllVisits()
    {
        List<VisitDto> visitDtoList = visitService.showAllVisits();

        if (visitDtoList != null)
        {
            System.out.println(visitDtoList.get(0));
            return new ResponseEntity<>(visitDtoList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/api/setNewVisit")
    public ResponseEntity addDoctor(@RequestBody VisitDto visitDto)
    {
        try
        {
            visitService.setNewVisit(visitDto);
        }
        catch (VisitExistsException e)
        {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

//    --****  Usuwanie wizyt

//    @DeleteMapping("/api/deleteVisit/{id}")
//    public ResponseEntity deleteVisit(@PathVariable ("id") Integer id)
//    {
//        visitService.deleteVisitById(id);
//        return new ResponseEntity(HttpStatus.OK);
//    }
}
