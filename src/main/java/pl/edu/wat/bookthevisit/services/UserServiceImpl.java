package pl.edu.wat.bookthevisit.services;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.wat.bookthevisit.dtos.UserLoginDto;
import pl.edu.wat.bookthevisit.exceptions.EmailExistsException;
import pl.edu.wat.bookthevisit.dtos.UserRegistrationDto;
import pl.edu.wat.bookthevisit.entities.UserEntity;
import pl.edu.wat.bookthevisit.exceptions.LengthPasswordException;
import pl.edu.wat.bookthevisit.repositories.UsersRepository;

import java.util.Collections;


@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService
{
    private final UsersRepository usersRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository, BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public void registerUser(UserRegistrationDto userRegistrationDto) throws EmailExistsException, LengthPasswordException {

        if(usersRepository.existsAllByEmail(userRegistrationDto.getEmail()))
            throw new EmailExistsException("E-mail " + userRegistrationDto.getEmail() + " already in use");

        if(userRegistrationDto.getPassword().length() < 4)
            throw new LengthPasswordException("Password too short");

        UserEntity userEntity = new UserEntity();

        userEntity.setEmail(userRegistrationDto.getEmail());
        userEntity.setName(userRegistrationDto.getName());
        userEntity.setPassword(bCryptPasswordEncoder.encode(userRegistrationDto.getPassword()));
        userEntity.setSurname(userRegistrationDto.getSurname());

        usersRepository.save(userEntity);

    }

    @Override
    public void editData(UserLoginDto userChangeDataDto) throws EmailExistsException, LengthPasswordException {

        Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentUserEmail = currentUser.toString();
        String currentUserPassword = usersRepository.findByEmail(currentUserEmail).getPassword();


        if(usersRepository.existsAllByEmail(userChangeDataDto.getEmail()))
            throw new EmailExistsException(userChangeDataDto.getEmail());

        if(userChangeDataDto.getPassword().length() < 4)
            throw new LengthPasswordException("Password too short");

        String newUserEmail = userChangeDataDto.getEmail() == null ? currentUserEmail : userChangeDataDto.getEmail();
        String newUserPassword = userChangeDataDto.getPassword() == null ? currentUserPassword : userChangeDataDto.getPassword();
        usersRepository.saveUpdate(currentUserEmail, newUserEmail, newUserPassword);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UserEntity user = usersRepository.findByEmail(s);
        if(user == null)
        {
            throw new UsernameNotFoundException("Invalid username.");
        }
        return new User(user.getEmail(), user.getPassword(), Collections.emptyList());
    }
}
