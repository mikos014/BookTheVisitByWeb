package pl.edu.wat.bookthevisit.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginDto
{
    private String email;
    private String password;
}
