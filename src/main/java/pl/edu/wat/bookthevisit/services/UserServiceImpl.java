package pl.edu.wat.bookthevisit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.bookthevisit.dtos.UserDto;
import pl.edu.wat.bookthevisit.dtos.UserRegistrationDto;
import pl.edu.wat.bookthevisit.entities.UserEntity;
import pl.edu.wat.bookthevisit.repositories.UsersRepository;


@Service
public class UserServiceImpl implements UserService
{
    private final UsersRepository usersRepository;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository)
    {
        this.usersRepository = usersRepository;
    }

    @Override
    public boolean logUser(UserDto userDto)
    {
        if (usersRepository.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword()) != null)
            return true;
        else
            return false;
    }

    @Override
    public boolean registerUser(UserRegistrationDto userRegistrationDto) {

        if (usersRepository.findByEmail(userRegistrationDto.getEmail()) == null)
        {
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail(userRegistrationDto.getEmail());
            userEntity.setName(userRegistrationDto.getName());
            userEntity.setPassword(userRegistrationDto.getPassword());
            userEntity.setSurname(userRegistrationDto.getSurname());

            usersRepository.save(userEntity);

            return true;
        }
        else
            return false;
    }

    @Override
    public boolean editData(UserDto userDto, UserRegistrationDto userChangeDataDto)
    {
        if (!userDto.getEmail().equals(userChangeDataDto.getEmail())
                && usersRepository.findByEmail(userChangeDataDto.getEmail()) == null)
        {
            usersRepository.
        }
        return false;
    }
}
