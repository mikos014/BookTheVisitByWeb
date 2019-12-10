package pl.edu.wat.bookthevisit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.bookthevisit.dtos.DoctorDto;
import pl.edu.wat.bookthevisit.dtos.UserDto;
import pl.edu.wat.bookthevisit.dtos.VisitDto;
import pl.edu.wat.bookthevisit.services.VisitService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class VisitController
{
    private final VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService)
    {
        this.visitService = visitService;
    }

    @PostMapping("/addVisit")
    public ResponseEntity addVisit(@RequestBody VisitDto visitDto, DoctorDto doctorDto, UserDto userDto)
    {
        if (visitService.addVisit(visitDto, doctorDto, userDto))
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/showVisit")
    public ResponseEntity<List<VisitDto>> showAllVisits()
    {
        List<VisitDto> visitDtoList = visitService.showAllVisits();

        if (visitDtoList != null)
        {
            return new ResponseEntity<>(visitDtoList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteVisit/{id}")
    public ResponseEntity deleteVisit(@PathVariable ("id") Integer id)
    {
        visitService.deleteVisitById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
