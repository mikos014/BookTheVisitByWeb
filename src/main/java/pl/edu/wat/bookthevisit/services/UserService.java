package pl.edu.wat.bookthevisit.services;

import pl.edu.wat.bookthevisit.dtos.UserDto;
import pl.edu.wat.bookthevisit.dtos.UserRegistrationDto;

public interface UserService
{
    boolean logUser(UserDto userDto);
    boolean registerUser(UserRegistrationDto userRegistrationDto);
}
