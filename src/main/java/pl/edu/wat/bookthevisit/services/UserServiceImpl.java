package pl.edu.wat.bookthevisit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wat.bookthevisit.dtos.UserLoginDto;
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
    public boolean logUser(UserLoginDto userLoginDto)
    {
        return usersRepository.existsByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword());
    }

    @Override
    public boolean registerUser(UserRegistrationDto userRegistrationDto)
    {

        if (!usersRepository.existsByEmail(userRegistrationDto.getEmail()))
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
    public boolean editData(UserLoginDto userLoginDto, UserRegistrationDto userChangeDataDto)
    {
        int i = 0;
        UserEntity userEntityToCreate = new UserEntity();

        if (userChangeDataDto.getEmail() != null && !usersRepository.existsByEmail(userChangeDataDto.getEmail())
                && userChangeDataDto.getPassword() != null)
        {
            userEntityToCreate = new UserEntity(null, userChangeDataDto.getEmail(), usersRepository.findByEmail(userLoginDto.getEmail()).getName(), userChangeDataDto.getPassword(), usersRepository.findByEmail(userLoginDto.getEmail()).getSurname());
            i++;
        }
        else if (userChangeDataDto.getEmail() != null && !usersRepository.existsByEmail(userChangeDataDto.getEmail()))
        {
            userEntityToCreate = new UserEntity(null, userChangeDataDto.getEmail(), usersRepository.findByEmail(userLoginDto.getEmail()).getName(), userLoginDto.getPassword(), usersRepository.findByEmail(userLoginDto.getEmail()).getSurname());
            i++;
        }
        else if (userChangeDataDto.getPassword() != null)
        {
            userEntityToCreate = new UserEntity(null, userLoginDto.getEmail(), usersRepository.findByEmail(userLoginDto.getEmail()).getName(), userChangeDataDto.getPassword(), usersRepository.findByEmail(userLoginDto.getEmail()).getSurname());
            i++;
        }


        if (i > 0)
        {
            UserEntity userEntityToDelete = usersRepository.findByEmail(userLoginDto.getEmail());
            usersRepository.delete(userEntityToDelete);
            usersRepository.save(userEntityToCreate);
            return true;
        }
        else
            return false;
    }

}
