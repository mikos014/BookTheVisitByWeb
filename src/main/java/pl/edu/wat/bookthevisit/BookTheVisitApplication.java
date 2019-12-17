package pl.edu.wat.bookthevisit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
//@EnableResourceServer
//@EnableOAuth2Sso
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

//    generator użytkowników
//    @Bean
//    ApplicationRunner init(VisitsRepository visitsRepository) {
//        return args -> {
//            Stream.of(13.30,14.00, 15.30, 16.00).forEach(email -> {
//                VisitEntity visitEntity = new VisitEntity();
//                visitEntity.setEmail(email);
//                visitEntity.setPassword(bCryptPasswordEncoder().encode("1234"));
//                visitEntity.setName("casc");
//                visitEntity.setSurname("casaas");
//                visitsRepository.save(userEntity);
//            };
//        }
//    }

}
