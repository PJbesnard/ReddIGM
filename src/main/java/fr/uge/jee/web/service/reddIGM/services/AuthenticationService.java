package fr.uge.jee.web.service.reddIGM.services;

import fr.uge.jee.web.service.reddIGM.dto.LoginResponse;
import fr.uge.jee.web.service.reddIGM.dto.LoginRequest;
import fr.uge.jee.web.service.reddIGM.dto.RegisterRequest;
import fr.uge.jee.web.service.reddIGM.dto.RegisterResponse;
import fr.uge.jee.web.service.reddIGM.models.Authority;
import fr.uge.jee.web.service.reddIGM.models.User;
import fr.uge.jee.web.service.reddIGM.repositories.UserRepository;
import fr.uge.jee.web.service.reddIGM.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class AuthenticationService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest loginRequest) throws Exception{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        } catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password", e);
        }
        System.out.println("received Username : " + loginRequest.getUsername() + " password : " + loginRequest.getPassword());
        User user = userService.loadUserByUsername(loginRequest.getUsername());
        final String jtwToken = jwtUtil.generateToken(user);
        return new LoginResponse(jtwToken);
    }

    public RegisterResponse register(RegisterRequest registerRequest) {
        User user = new User();
        System.out.println("username : " + registerRequest.getUsername() + " " + "email : " + registerRequest.getEmail() + " " + "password : " + registerRequest.getPassword());
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        Set<Authority> authorities = new HashSet<>();
        authorities.add(new Authority("USER"));
        user.setAuthorities(authorities);
        userService.save(user);
        return new RegisterResponse(registerRequest.getUsername());
    }

    public boolean isAuthenticated() {
        return !Objects.isNull(SecurityContextHolder.getContext().getAuthentication()) &&
                !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
    }

    public User getCurrentUser() {
        if (!isAuthenticated()) {
            return null;
        }

        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
