package pl.edu.wat.bookthevisit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.wat.bookthevisit.dtos.UserDto;
import pl.edu.wat.bookthevisit.dtos.UserLoginDto;
import pl.edu.wat.bookthevisit.entities.RoleEntity;
import pl.edu.wat.bookthevisit.exceptions.EmailExistsException;
import pl.edu.wat.bookthevisit.dtos.UserRegistrationDto;
import pl.edu.wat.bookthevisit.entities.UserEntity;
import pl.edu.wat.bookthevisit.exceptions.LengthPasswordException;
import pl.edu.wat.bookthevisit.repositories.RolesRepository;
import pl.edu.wat.bookthevisit.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService
{
    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RolesRepository rolesRepository;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RolesRepository rolesRepository)
    {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.rolesRepository = rolesRepository;
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
        userEntity.setRole(rolesRepository.findAllByRole("User"));

        usersRepository.save(userEntity);

    }

    @Override
    public void editData(UserLoginDto userChangeDataDto) throws EmailExistsException, LengthPasswordException {

        Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentUserEmail = currentUser.toString();
        String currentUserPassword = usersRepository.findByEmail(currentUserEmail).getPassword();


        if(usersRepository.existsAllByEmail(userChangeDataDto.getEmail()))
            throw new EmailExistsException(userChangeDataDto.getEmail());

        if(userChangeDataDto.getPassword() != null && userChangeDataDto.getPassword().length() < 4)
            throw new LengthPasswordException("Password too short");

        String newUserEmail = userChangeDataDto.getEmail() == null ? currentUserEmail : userChangeDataDto.getEmail();
        String newUserPassword = userChangeDataDto.getPassword() == null ? currentUserPassword : bCryptPasswordEncoder.encode(userChangeDataDto.getPassword());
        usersRepository.saveUpdate(currentUserEmail, newUserEmail, newUserPassword);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UserEntity user = usersRepository.findByEmail(s);
        if(user == null)
        {
            throw new UsernameNotFoundException("Invalid username.");
        }
        System.out.println(user.getEmail() + " " + user.getPassword());
        return new User(user.getEmail(), user.getPassword(), Collections.emptyList());
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> userDtoList = new ArrayList<>();

        usersRepository.findAll()
                .forEach(v -> userDtoList.add(new UserDto(v.getIdPacient(), v.getEmail())));
        return userDtoList;
    }

    public void saveGoogle(UserEntity userEntity)
    {
        if(usersRepository.existsAllByEmail((userEntity.getEmail())))
            return;

        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        userEntity.setRole(rolesRepository.findAllByRole("User"));
        System.out.println(userEntity.getEmail() + " " + userEntity.getPassword());
        usersRepository.save(userEntity);
    }

    public String getRole(String username)
    {
//        Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer idRole = usersRepository.findByEmail(username).getRole().getIdRole();
        String userRole = rolesRepository.findAllByIdRole(idRole).getRole();

        return userRole;
    }
}
