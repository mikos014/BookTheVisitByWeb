package pl.edu.wat.bookthevisit;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.edu.wat.bookthevisit.entities.UserEntity;
import pl.edu.wat.bookthevisit.repositories.UsersRepository;

import java.util.stream.Stream;

@SpringBootApplication
//@EnableResourceServer
//@EnableOAuth2Sso
public class BookTheVisitApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(BookTheVisitApplication.class, args);
    }

//    generator użytkowników
    @Bean
    ApplicationRunner init(UsersRepository usersRepository) {
        return args -> {
            Stream.of("abc@abc.pl", "mikolaj@wp.pl", "jan").forEach(email -> {
                UserEntity userEntity = new UserEntity();
                userEntity.setEmail(email);
                userEntity.setPassword(bCryptPasswordEncoder().encode("1234"));
                userEntity.setName("casc");
                userEntity.setSurname("casaas");
                usersRepository.save(userEntity);
            });
        };
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    @SuppressWarnings("unchecked")
//    public FilterRegistrationBean simpleCorsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.setAllowedOrigins(Collections.singletonList("*")); // http://localhost:4200
//        config.setAllowedMethods(Collections.singletonList("*"));
//        config.setAllowedHeaders(Collections.singletonList("*"));
//        source.registerCorsConfiguration("/**", config);
//        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        return bean;
//    }

//    @Bean
//    public FilterRegistrationBean<JwtAuthenticationFilter> filterRegistrationBean()
//    {
//        FilterRegistrationBean<JwtAuthenticationFilter> filterRegistrationBean = new FilterRegistrationBean<>();
//        filterRegistrationBean.setFilter(new JwtAuthenticationFilter());
//        filterRegistrationBean.setUrlPatterns(Collections.singleton("/api/*"));
//        return filterRegistrationBean;
//    }
}
