package pl.edu.wat.bookthevisit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.bookthevisit.dtos.DoctorDto;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService
{
    private final DoctorService doctorService;

    @Autowired
    public DoctorServiceImpl(DoctorService doctorService)
    {
        this.doctorService = doctorService;
    }

    @Override
    public List<DoctorDto> getDoctors() {
        return null;
    }

    @Override
    public List<DoctorDto> getDoctorsBySpec(String spec)
    {

        return null;
    }
}
