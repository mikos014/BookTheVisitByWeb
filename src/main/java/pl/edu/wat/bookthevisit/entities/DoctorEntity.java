package pl.edu.wat.bookthevisit.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity(name = "Doctor")
public class DoctorEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdDoctor;

    private String name;
    private String spec;
    private String surname;
}
