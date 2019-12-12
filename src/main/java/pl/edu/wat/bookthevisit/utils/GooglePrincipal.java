package pl.edu.wat.bookthevisit.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.stereotype.Service;
import pl.edu.wat.bookthevisit.entities.UserEntity;
import pl.edu.wat.bookthevisit.repositories.UsersRepository;

import java.util.Map;

@Service
public class GooglePrincipal implements PrincipalExtractor
{
    @Autowired
    private final UsersRepository usersRepository;

    public GooglePrincipal(UsersRepository usersRepository)
    {
        this.usersRepository = usersRepository;
    }

    @Override
    public Object extractPrincipal(Map<String, Object> map)
    {
        boolean userExists = usersRepository.existsByEmail((String)map.get("email"));

        if(userExists)
            return usersRepository.findByEmail((String)map.get("email"));

        UserEntity newUser;
        newUser = new UserEntity();
        newUser.setEmail((String)map.get("email"));
        newUser.setName((String)map.get("username"));
        newUser.setEmail((String)map.get("email"));
        usersRepository.save(newUser);
        return newUser;
    }
}
