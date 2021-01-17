package fr.uge.jee.web.service.reddIGM.dto;

import java.util.Objects;

public class AuthenticationResponse {

    private final String jwt;

    public AuthenticationResponse(String jwt){
        this.jwt = Objects.requireNonNull(jwt);
    }

    public String getJwt(){
        return jwt;
    }
}
