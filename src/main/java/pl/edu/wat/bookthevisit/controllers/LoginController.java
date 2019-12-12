package pl.edu.wat.bookthevisit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.bookthevisit.config.JwtTokenProvider;
import pl.edu.wat.bookthevisit.dtos.UserLoginDto;
import pl.edu.wat.bookthevisit.exceptions.LengthPasswordException;
import pl.edu.wat.bookthevisit.services.UserService;

import javax.security.auth.login.LoginException;
import java.util.HashMap;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController
{
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public LoginController(UserService userService, JwtTokenProvider jwtTokenProvider)
    {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/api/abc")
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
        }
        catch (LengthPasswordException e)
        {
            return new ResponseEntity<>("Password too short", HttpStatus.CONFLICT);
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("email", userLoginDto.getEmail());
        map.put("role", "user");
        String token = jwtTokenProvider.generate(map, "customLogin", userLoginDto.getPassword());

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
