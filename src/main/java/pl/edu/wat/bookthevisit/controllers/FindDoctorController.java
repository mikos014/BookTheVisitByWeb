package pl.edu.wat.bookthevisit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.bookthevisit.dtos.DoctorDto;
import pl.edu.wat.bookthevisit.exceptions.DoctorExistsException;
import pl.edu.wat.bookthevisit.services.DoctorService;

import java.util.List;

@RestController
public class FindDoctorController
{
    private final DoctorService doctorService;

    @Autowired
    public FindDoctorController(DoctorService doctorService)
    {
        this.doctorService = doctorService;
    }

    @GetMapping("/api/getDoctors")
    public ResponseEntity<List<DoctorDto>> getDoctors()
    {
        return new ResponseEntity<>(doctorService.getDoctors(), HttpStatus.OK);
    }

    @GetMapping("/api/getDoctorById/{id}")
    public ResponseEntity<DoctorDto> getDoctor(@PathVariable("id") Integer id)
    {
        return new ResponseEntity<>(doctorService.getDoctorById(id), HttpStatus.OK);
    }

    @PostMapping("/api/getDoctorsFiltered")
    public ResponseEntity<List<DoctorDto>> getDoctorsBySpec(@RequestBody DoctorDto doctorDto)
    {
        List<DoctorDto> doctorDtoList = doctorService.getDoctorsBySpec(doctorDto);

        if (doctorDtoList == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(doctorDtoList, HttpStatus.OK);
    }

    @PostMapping("/api/setNewDoctor")
    public ResponseEntity addDoctor(@RequestBody DoctorDto doctorDto)
    {
        try
        {
            doctorService.setNewDoctor(doctorDto);
        }
        catch (DoctorExistsException e)
        {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}