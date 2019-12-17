package pl.edu.wat.bookthevisit.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import pl.edu.wat.bookthevisit.entities.DoctorEntity;
import pl.edu.wat.bookthevisit.entities.UserEntity;

import java.util.Date;

@Data
@AllArgsConstructor
public class VisitDto
{
    private Integer id;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date date;
    private String time;
    private DoctorEntity doctorEntity;
//    private UserEntity userEntity;
}
