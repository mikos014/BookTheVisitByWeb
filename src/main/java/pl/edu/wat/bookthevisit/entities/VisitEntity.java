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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date date;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idDoctor")
    private DoctorEntity doctor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idPacient")
    private UserEntity pacient;

}
