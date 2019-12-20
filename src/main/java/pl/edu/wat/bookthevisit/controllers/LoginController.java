package pl.edu.wat.bookthevisit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.bookthevisit.services.UserService;


@RestController
public class LoginController
{
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService)
    {
        this.userService = userService;
    }


}
