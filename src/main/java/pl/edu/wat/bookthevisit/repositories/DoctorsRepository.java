package pl.edu.wat.bookthevisit.repositories;

import pl.edu.wat.bookthevisit.entities.DoctorEntity;

public interface DoctorsRepository
{
    DoctorEntity findBySpec(String spec);
}
