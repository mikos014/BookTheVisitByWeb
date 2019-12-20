package pl.edu.wat.bookthevisit.services;

import pl.edu.wat.bookthevisit.exceptions.EmailExistsException;
import pl.edu.wat.bookthevisit.dtos.UserLoginDto;
import pl.edu.wat.bookthevisit.dtos.UserRegistrationDto;
import pl.edu.wat.bookthevisit.exceptions.LengthPasswordException;


public interface UserService
{
    void registerUser(UserRegistrationDto userRegistrationDto) throws EmailExistsException, LengthPasswordException;
    void editData(UserLoginDto userChangeDataDto) throws EmailExistsException, LengthPasswordException;
//    void loginGoogle(GoogleUserDto googleUserDto);
}
