package pl.edu.wat.bookthevisit.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class VisitDto
{
    private Date date;
    private DoctorDto doctorDto;
    private UserDto userDto;
}
