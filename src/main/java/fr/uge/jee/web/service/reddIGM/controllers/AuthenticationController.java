package fr.uge.jee.web.service.reddIGM.controllers;

import fr.uge.jee.web.service.reddIGM.dto.AuthenticationResponse;
import fr.uge.jee.web.service.reddIGM.dto.LoginRequest;
import fr.uge.jee.web.service.reddIGM.models.User;
import fr.uge.jee.web.service.reddIGM.services.UserService;
import fr.uge.jee.web.service.reddIGM.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping("/hello")
    public String hello(){ return "hello world ";}

    @RequestMapping("/login")
    @PostMapping
    public AuthenticationResponse createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws  Exception {
       /* try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword())
            );
        } catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password", e);
        }*/
        //User user = userService.loadUserByUsername(loginRequest.getUserName());
        User user = new User("coco", "ococo", "truc@gmail.com", User.Authority.USER);
        final String jtwToken = jwtUtil.generateToken(user);
        return new AuthenticationResponse(jtwToken);
    }

}
