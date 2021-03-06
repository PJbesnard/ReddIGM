package fr.uge.jee.web.service.reddIGM.dto;

import java.util.Objects;

public class LoginResponse {

    private final String jwt;
    private final boolean isAdmin;

    public LoginResponse(String jwt, boolean isAdmin){
        this.jwt = Objects.requireNonNull(jwt);
        this.isAdmin = isAdmin;
    }

    public String getJwt(){
        return jwt;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
