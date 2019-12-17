package pl.edu.wat.bookthevisit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.bookthevisit.dtos.DoctorDto;
import pl.edu.wat.bookthevisit.services.DoctorService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class FindDoctorController
{
    private final DoctorService doctorService;

    @Autowired
    public FindDoctorController(DoctorService doctorService)
    {
        this.doctorService = doctorService;
    }

    @GetMapping("/api/doctors")
    public ResponseEntity<List<DoctorDto>> getDoctors()
    {
        return new ResponseEntity<>(doctorService.getDoctors(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/api/doctors/{spec}")
    public ResponseEntity<List<DoctorDto>> getDoctorsBySpec(@PathVariable("spec") String spec)
    {
        List<DoctorDto> doctorDtoList = doctorService.getDoctorsBySpec(spec);

        if (doctorDtoList == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(doctorDtoList, HttpStatus.NO_CONTENT);
    }

}
