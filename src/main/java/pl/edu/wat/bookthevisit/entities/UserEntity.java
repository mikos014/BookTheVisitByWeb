package pl.edu.wat.bookthevisit.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "idRole")
    private RoleEntity role;
}