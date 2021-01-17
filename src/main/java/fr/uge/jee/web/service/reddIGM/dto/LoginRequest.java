package fr.uge.jee.web.service.reddIGM.dto;

import java.util.Objects;

public class LoginRequest {
    private String userName;
    private String password;

    public LoginRequest(String userName, String password) {
        this.userName = Objects.requireNonNull(userName);
        this.password = Objects.requireNonNull(password);
    }

    public LoginRequest() {
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
