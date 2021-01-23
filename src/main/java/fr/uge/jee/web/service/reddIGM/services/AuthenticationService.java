package fr.uge.jee.web.service.reddIGM.services;

import fr.uge.jee.web.service.reddIGM.dto.LoginResponse;
import fr.uge.jee.web.service.reddIGM.dto.LoginRequest;
import fr.uge.jee.web.service.reddIGM.dto.RegisterRequest;
import fr.uge.jee.web.service.reddIGM.dto.RegisterResponse;
import fr.uge.jee.web.service.reddIGM.models.User;
import fr.uge.jee.web.service.reddIGM.repositories.UserRepository;
import fr.uge.jee.web.service.reddIGM.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

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
        user.setAuthority(User.Authority.USER);
        userRepository.save(user);
        return new RegisterResponse(registerRequest.getUsername());
    }
}
