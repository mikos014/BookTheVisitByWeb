package pl.edu.wat.bookthevisit;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import pl.edu.wat.bookthevisit.config.JwtTokenFilter;
import pl.edu.wat.bookthevisit.entities.UserEntity;
import pl.edu.wat.bookthevisit.repositories.UsersRepository;

import java.util.Collections;
import java.util.stream.Stream;

@SpringBootApplication
//@EnableResourceServer
@EnableOAuth2Sso
public class BookTheVisitApplication
{

    public static void main(String[] args) {
        SpringApplication.run(BookTheVisitApplication.class, args);
    }

//    generator użytkowników
    @Bean
    ApplicationRunner init(UsersRepository usersRepository) {
        return args -> {
            Stream.of("abc@abc.pl", "mikolaj@wp.pl", "jan").forEach(email -> {
                UserEntity userEntity = new UserEntity();
                userEntity.setEmail(email);
                userEntity.setPassword("1234");
                userEntity.setName("casc");
                userEntity.setSurname("casaas");
                usersRepository.save(userEntity);
            });
        };
    }

    @Bean
    @SuppressWarnings("unchecked")
    public FilterRegistrationBean simpleCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

    @Bean
    public FilterRegistrationBean<JwtTokenFilter> filterRegistrationBean()
    {
        FilterRegistrationBean<JwtTokenFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new JwtTokenFilter());
        filterRegistrationBean.setUrlPatterns(Collections.singleton("/api/*"));
        return filterRegistrationBean;
    }
}
