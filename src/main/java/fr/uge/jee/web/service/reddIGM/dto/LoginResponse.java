package fr.uge.jee.web.service.reddIGM.dto;

import java.util.Objects;

public class LoginResponse {

    private final String jwt;
    private final boolean admin;

    public LoginResponse(String jwt, boolean admin){
        this.jwt = Objects.requireNonNull(jwt);
        this.admin = admin;
    }

    public String getJwt(){
        return jwt;
    }

    public boolean isAdmin() {
        return admin;
    }
}
