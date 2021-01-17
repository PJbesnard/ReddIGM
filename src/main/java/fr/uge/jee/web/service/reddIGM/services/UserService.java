package fr.uge.jee.web.service.reddIGM.services;

import fr.uge.jee.web.service.reddIGM.models.User;
import fr.uge.jee.web.service.reddIGM.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    public Optional<User> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }
        return user.get();
    }
}
