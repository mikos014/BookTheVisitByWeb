package pl.edu.wat.bookthevisit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.wat.bookthevisit.EmailExistsException;
import pl.edu.wat.bookthevisit.dtos.UserLoginDto;
import pl.edu.wat.bookthevisit.dtos.UserRegistrationDto;
import pl.edu.wat.bookthevisit.entities.UserEntity;
import pl.edu.wat.bookthevisit.repositories.UsersRepository;

import javax.security.auth.login.LoginException;


@Service
public class UserServiceImpl implements UserService
{
    private final UsersRepository usersRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void logUser(UserLoginDto userLoginDto) throws LoginException
    {
        if (!usersRepository.existsByEmailAndPassword(userLoginDto.getEmail(), bCryptPasswordEncoder.encode(userLoginDto.getPassword())))
            throw new LoginException("Bad e-mail or password");
    }

    @Override
    public void registerUser(UserRegistrationDto userRegistrationDto) throws EmailExistsException
    {

        if(usersRepository.existsByEmail(userRegistrationDto.getEmail()))
            throw new EmailExistsException("E-mail " + userRegistrationDto.getEmail() + " already in use");


        UserEntity userEntity = new UserEntity();

        userEntity.setEmail(userRegistrationDto.getEmail());
        userEntity.setName(userRegistrationDto.getName());
        userEntity.setPassword(bCryptPasswordEncoder.encode(userRegistrationDto.getPassword()));
        userEntity.setSurname(userRegistrationDto.getSurname());

        usersRepository.save(userEntity);

    }

    @Override
    public void editData(UserRegistrationDto userChangeDataDto) throws EmailExistsException {

        Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentUserEmail = currentUser.toString();
        String currentUserPassword = usersRepository.findByEmail(currentUserEmail).getPassword();

//        if(currentUser instanceof UserDetails)
//        {
//            currentUserEmail = ((UserDetails)currentUser).getUsername();
//        }

        if(!usersRepository.existsByEmail(userChangeDataDto.getEmail()))
            throw new EmailExistsException(userChangeDataDto.getEmail());

        String newUserEmail = userChangeDataDto.getEmail() == null ? currentUserEmail : userChangeDataDto.getEmail();
        String newUserPassword = userChangeDataDto.getPassword() == null ? currentUserPassword : userChangeDataDto.getPassword();
        usersRepository.saveUpdate(currentUserEmail, newUserEmail, newUserPassword);
    }

}
