package pl.edu.wat.bookthevisit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BookTheVisitApplication extends SpringBootServletInitializer
{

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(BookTheVisitApplication.class);
//    }

    public static void main(String[] args) {
        SpringApplication.run(BookTheVisitApplication.class, args);
    }

}
