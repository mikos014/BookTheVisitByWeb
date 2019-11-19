package pl.edu.wat.bookthevisit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.bookthevisit.dtos.UserDto;
import pl.edu.wat.bookthevisit.dtos.UserLoginDto;
import pl.edu.wat.bookthevisit.services.UserService;


@RestController
//@CrossOrigin
//@RequestMapping(name = "/login")
public class LoginController
{
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService)
    {
        this.userService = userService;
    }


    @PostMapping("/login")
    public ResponseEntity logUser(@RequestBody UserLoginDto userLoginDto)
    {
        if (userService.logUser(userLoginDto)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            //przełącz na strone głowną
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
