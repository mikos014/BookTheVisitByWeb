package pl.edu.wat.bookthevisit.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity(name = "Visit")
public class VisitEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdVisit;
    private Date date;

    @ManyToOne(optional = false)
    @JoinColumn(name = "IdDoctor")
    private DoctorEntity doctor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "IdPacient")
    private UserEntity pacient;

}
