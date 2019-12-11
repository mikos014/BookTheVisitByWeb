package pl.edu.wat.bookthevisit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.bookthevisit.exceptions.EmailExistsException;
import pl.edu.wat.bookthevisit.dtos.UserRegistrationDto;
import pl.edu.wat.bookthevisit.exceptions.LengthPasswordException;
import pl.edu.wat.bookthevisit.services.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EditDataController
{
    private final UserService userService;

    @Autowired
    public EditDataController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping("/api/editData")
    public ResponseEntity editData(@RequestBody UserRegistrationDto userChangeDataDto)
    {
        try
        {
            userService.editData(userChangeDataDto);
        }
        catch (LengthPasswordException e)
        {
            return new ResponseEntity<>("Password too short.", HttpStatus.CONFLICT);
        }
        catch (EmailExistsException e)
        {
            return new ResponseEntity<>("Email exists ! Set unique one.", HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
