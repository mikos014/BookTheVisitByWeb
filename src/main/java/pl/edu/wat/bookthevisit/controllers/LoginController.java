package pl.edu.wat.bookthevisit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.bookthevisit.services.UserService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController
{
    private final UserService userService;
    private final UserDetailsService userDetailsService;

    @Autowired
    public LoginController(UserService userService, UserDetailsService userDetailsService)
    {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/api/abc")
    public ResponseEntity<String> abc()
    {
        return new ResponseEntity<>("index", HttpStatus.OK);
    }


//    @RequestMapping("/loginGoogle")
//    public String loginGoogle()
//    {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(auth.getPrincipal());
//        return "/index";
//    }

}
