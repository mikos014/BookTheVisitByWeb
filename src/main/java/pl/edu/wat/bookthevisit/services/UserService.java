package pl.edu.wat.bookthevisit.services;

import pl.edu.wat.bookthevisit.dtos.UserLoginDto;
import pl.edu.wat.bookthevisit.dtos.UserRegistrationDto;

public interface UserService
{
    boolean logUser(UserLoginDto userLoginDto);
    boolean registerUser(UserRegistrationDto userRegistrationDto);
    boolean editData(UserLoginDto userLoginDto, UserRegistrationDto userChangeDataDto);
}
