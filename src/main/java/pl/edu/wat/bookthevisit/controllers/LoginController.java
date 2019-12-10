package pl.edu.wat.bookthevisit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.bookthevisit.dtos.UserLoginDto;
import pl.edu.wat.bookthevisit.services.UserService;

import javax.security.auth.login.LoginException;


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


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginDto userLoginDto) throws LoginException
    {
        userDetailsService.loadUserByUsername(userLoginDto.getEmail());
//        userService.logUser(userLoginDto);
//
//        return "/index";
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping("/loginGoogle")
    public String loginGoogle()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getPrincipal());
        return "/index";
    }

    @RequestMapping("/callback")
    public String callback()
    {
        System.out.println("redirecting to home page");
        return "/home";
    }
}
