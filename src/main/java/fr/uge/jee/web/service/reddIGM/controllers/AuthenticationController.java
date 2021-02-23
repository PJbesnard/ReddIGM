package fr.uge.jee.web.service.reddIGM.controllers;

import fr.uge.jee.web.service.reddIGM.dto.*;
import fr.uge.jee.web.service.reddIGM.models.User;
import fr.uge.jee.web.service.reddIGM.repositories.UserRepository;
import fr.uge.jee.web.service.reddIGM.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;


    @RequestMapping("/hello")
    public String hello(){ return "hello world ";}

    @RequestMapping("/login")
    @PostMapping
    public LoginResponse createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws  Exception {
       return authenticationService.login(loginRequest);
    }
    @RequestMapping("/logout")
    @GetMapping
    public LogoutResponse deleteAuthenticationToken()  {
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        return null;
    }

    @RequestMapping("/register")
    @PostMapping
    public RegisterResponse registerUser(@RequestBody RegisterRequest registerRequest) {
        return authenticationService.register(registerRequest);
    }

    @RequestMapping("/showLogin")
    @GetMapping
    public String showLogin() {
        User principal =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "User " + principal.getUsername() + " is currently logged in";
    }

}
