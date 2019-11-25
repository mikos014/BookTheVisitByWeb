package pl.edu.wat.bookthevisit.services;

import pl.edu.wat.bookthevisit.EmailExistsException;
import pl.edu.wat.bookthevisit.dtos.UserLoginDto;
import pl.edu.wat.bookthevisit.dtos.UserRegistrationDto;

import javax.security.auth.login.LoginException;

public interface UserService
{
    void logUser(UserLoginDto userLoginDto) throws LoginException;
    void registerUser(UserRegistrationDto userRegistrationDto) throws EmailExistsException;
    void editData(UserRegistrationDto userChangeDataDto) throws EmailExistsException;
}
