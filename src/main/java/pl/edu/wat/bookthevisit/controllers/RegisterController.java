package pl.edu.wat.bookthevisit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.bookthevisit.dtos.UserRegistrationDto;
import pl.edu.wat.bookthevisit.services.UserService;

@RestController
public class RegisterController
{
    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody UserRegistrationDto userRegistrationDto)
    {
        if (userService.registerUser(userRegistrationDto))
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            //przejdz do strony głownej
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
