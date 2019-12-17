package pl.edu.wat.bookthevisit.services;

import pl.edu.wat.bookthevisit.exceptions.EmailExistsException;
import pl.edu.wat.bookthevisit.dtos.UserLoginDto;
import pl.edu.wat.bookthevisit.dtos.UserRegistrationDto;
import pl.edu.wat.bookthevisit.exceptions.LengthPasswordException;

import javax.security.auth.login.LoginException;

public interface UserService
{
//    void logUser(UserLoginDto userLoginDto) throws LoginException, LengthPasswordException;
    void registerUser(UserRegistrationDto userRegistrationDto) throws EmailExistsException, LengthPasswordException;
    void editData(UserLoginDto userChangeDataDto) throws EmailExistsException, LengthPasswordException;
}
