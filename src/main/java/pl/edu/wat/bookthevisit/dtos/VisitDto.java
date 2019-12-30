package pl.edu.wat.bookthevisit.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class VisitDto
{
    private Integer id;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date date;
    private String time;
    private Integer doctor;
    private Integer user;
}
