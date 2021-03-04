package fr.uge.jee.web.service.reddIGM.dto;


public class RegisterRequest {
    private String email;
    private String username;
    private String password;
    private String picture;
    private String description;
    private boolean newsletterSubscriber;

    public RegisterRequest(String email, String username, String password, String picture, String description, boolean newsletterSubscriber) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.description = description;
        this.newsletterSubscriber = newsletterSubscriber;
    }

    public RegisterRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isNewsletterSubscriber() {
        return newsletterSubscriber;
    }

    public void setNewsletterSubscriber(boolean newsletterSubscriber) {
        this.newsletterSubscriber = newsletterSubscriber;
    }
}