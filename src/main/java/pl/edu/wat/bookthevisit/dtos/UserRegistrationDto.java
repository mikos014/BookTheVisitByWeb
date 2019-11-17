package pl.edu.wat.bookthevisit.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegistrationDto
{
    private String name;
    private String surname;
    private String email;
    private String password;
}
