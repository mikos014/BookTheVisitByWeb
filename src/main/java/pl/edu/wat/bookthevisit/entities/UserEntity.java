package pl.edu.wat.bookthevisit.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "Pacient")
public class UserEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPacient;

    private String email;
    private String name;
    private String password;
    private String surname;
}
