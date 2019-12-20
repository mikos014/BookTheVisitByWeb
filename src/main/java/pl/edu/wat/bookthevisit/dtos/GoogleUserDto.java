package pl.edu.wat.bookthevisit.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GoogleUserDto
{
    private String name;
    private String email;
    private String provider;
    private String providerId;
    private String image;
    private String token;
    private String idToken;
}
