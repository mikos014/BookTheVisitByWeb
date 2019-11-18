package pl.edu.wat.bookthevisit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.bookthevisit.dtos.UserDto;
import pl.edu.wat.bookthevisit.dtos.UserRegistrationDto;
import pl.edu.wat.bookthevisit.services.UserService;

@RestController
public class EditDataController
{
    private final UserService userService;

    @Autowired
    public EditDataController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping("/editData")
    public ResponseEntity editData(@RequestBody UserDto userDto, @RequestBody UserRegistrationDto userChangeDataDto)
    {
        if(userService.editData(userDto, userChangeDataDto))
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);

    }
}
