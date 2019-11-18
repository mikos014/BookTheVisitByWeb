package pl.edu.wat.bookthevisit.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DoctorDto
{
    private String name;
    private String spec;
    private String surname;
}
