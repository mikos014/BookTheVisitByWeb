package pl.edu.wat.bookthevisit;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.edu.wat.bookthevisit.entities.RoleEntity;
import pl.edu.wat.bookthevisit.entities.UserEntity;
import pl.edu.wat.bookthevisit.repositories.RolesRepository;
import pl.edu.wat.bookthevisit.repositories.UsersRepository;

import java.util.stream.Stream;

@SpringBootApplication
//@EnableResourceServer
public class BookTheVisitApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(BookTheVisitApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

//    generator ról
    @Bean
    ApplicationRunner init(RolesRepository rolesRepository, UsersRepository usersRepository)
    {
        return args -> {
            Stream.of("Admin", "User").forEach(role -> {
                RoleEntity roleEntity = new RoleEntity();
                roleEntity.setRole(role);
                rolesRepository.save(roleEntity);
            });

            UserEntity userEntity = new UserEntity();
            userEntity.setEmail("admin");
            userEntity.setPassword(bCryptPasswordEncoder().encode("admin"));
            userEntity.setRole(rolesRepository.findAllByIdRole(1));
            usersRepository.save(userEntity);
        };
    }

}
