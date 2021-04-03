
package def.services;

import def.models.Redactor;
import def.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ThesaurusUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        List<Redactor> redactorResponse = userRepository.getRedactorByLogin(login);
        if (redactorResponse.isEmpty())
            throw new UsernameNotFoundException("Пользователь не найден");
        Redactor redactor = redactorResponse.get(0);
        return new User(redactor.getLogin(), redactor.getPassword(), new ArrayList<>());
    }
}

