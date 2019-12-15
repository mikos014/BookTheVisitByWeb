package pl.edu.wat.bookthevisit.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity(name = "Visit")
public class VisitEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVisit;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date date;

    private int hour;
    private boolean occupied = false;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idDoctor")
    private DoctorEntity doctor;

    @ManyToOne
    @JoinColumn(name = "idPacient")
    private UserEntity pacient;

}
