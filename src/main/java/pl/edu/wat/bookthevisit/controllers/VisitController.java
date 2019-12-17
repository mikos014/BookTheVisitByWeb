package pl.edu.wat.bookthevisit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.bookthevisit.dtos.DoctorDto;
import pl.edu.wat.bookthevisit.dtos.UserDto;
import pl.edu.wat.bookthevisit.dtos.VisitDto;
import pl.edu.wat.bookthevisit.exceptions.VisitOccupiedException;
import pl.edu.wat.bookthevisit.services.VisitService;

import java.util.Date;
import java.util.List;

@RestController
//@CrossOrigin(origins = "*")
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

//    @RequestMapping(path = "/api/showVisitsFiltered", method = RequestMethod.GET)
    @GetMapping("/api/showVisits/{spec}/{dateFrom}/{dateTo}")
    public ResponseEntity<List<VisitDto>> showUnoccuppiedVisitsByDate(@PathVariable String spec, @PathVariable Date dateFrom, @PathVariable Date dateTo)
    {
        List<VisitDto> visitDtoList = visitService.showUnoccupiedVisitsLimitByDate(spec, dateFrom, dateTo);

        if (visitDtoList != null)
        {
            return new ResponseEntity<>(visitDtoList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/showVisits")
    public ResponseEntity<List<VisitDto>> showUnoccuppiedVisits()
    {
        List<VisitDto> visitDtoList = visitService.showUnoccupiedVisits();

        if (visitDtoList != null)
        {
            return new ResponseEntity<>(visitDtoList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/showMyVisits")
    public ResponseEntity<List<VisitDto>> showMyVisits()
    {
        List<VisitDto> visitDtoList = visitService.showMyVisits();

        if (visitDtoList != null)
        {
            return new ResponseEntity<>(visitDtoList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    --****  Usuwanie wizyt

//    @DeleteMapping("/api/deleteVisit/{id}")
//    public ResponseEntity deleteVisit(@PathVariable ("id") Integer id)
//    {
//        visitService.deleteVisitById(id);
//        return new ResponseEntity(HttpStatus.OK);
//    }
}
