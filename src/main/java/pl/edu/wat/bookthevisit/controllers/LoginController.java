package pl.edu.wat.bookthevisit.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.bookthevisit.JwtToken;
import pl.edu.wat.bookthevisit.dtos.UserLoginDto;
import pl.edu.wat.bookthevisit.exceptions.LengthPasswordException;
import pl.edu.wat.bookthevisit.services.UserService;

import javax.security.auth.login.LoginException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;


@RestController
//@CrossOrigin(origins = "http://localhost:4200")
public class LoginController
{
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping("/abc")
    public ResponseEntity<String> abc()
    {
        return new ResponseEntity<>("index", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDto userLoginDto)
    {
        try
        {
            userService.logUser(userLoginDto);
        }
        catch (LoginException e)
        {
            return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
        } catch (LengthPasswordException e) {
            return new ResponseEntity<>("Password too short", HttpStatus.CONFLICT);
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("email", userLoginDto.getEmail());
        map.put("role", "user");
        String token = JwtToken.generate(map, "customLogin", userLoginDto.getPassword());

//        String jwt = Jwts.builder().setClaims(map).setIssuer("customLogin").compact();
//        httpServletResponse.addCookie(new Cookie("Authorization", String.format("Bearer {}", jwt)));
//        addHeader("Set-Cookie", "Authorization ...")


        return new ResponseEntity<>(token, HttpStatus.OK);
    }

//    @RequestMapping("/loginGoogle")
//    public String loginGoogle()
//    {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(auth.getPrincipal());
//        return "/index";
//    }

//    @RequestMapping("/callback")
//    public String callback()
//    {
//        System.out.println("redirecting to home page");
//        return "/home";
//    }
}
