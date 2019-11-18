package pl.edu.wat.bookthevisit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.bookthevisit.dtos.VisitDto;
import pl.edu.wat.bookthevisit.services.VisitService;

@RestController
public class VisitController
{
    private final VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService)
    {
        this.visitService = visitService;
    }

    @PostMapping("/addVisit")
    public ResponseEntity addVisit(@RequestBody VisitDto visitDto)
    {
        if (visitService.addVisit(visitDto))
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/showVisit")
    public ResponseEntity showAllVisits()
    {
        visitService.showAllVisits();
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/deleteVisit/{id}")
    public ResponseEntity deleteVisit(@PathVariable ("id") Integer id)
    {
        //return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
