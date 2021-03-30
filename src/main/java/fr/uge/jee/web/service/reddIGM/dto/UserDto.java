package fr.uge.jee.web.service.reddIGM.dto;

import fr.uge.jee.web.service.reddIGM.models.Authority;

import java.util.Set;

public class UserDto {
    private Long id;
    private String username;
    private String picture;
    private String description;
    private Boolean newsletterSubscriber;
    private String email;
    private Set<Authority> authorities;

    public UserDto(){}

    public UserDto(Long id, String username, String picture, String description, Boolean newsletterSubscriber, String email, Set<Authority> authorities) {
        this.id = id;
        this.username = username;
        this.picture = picture;
        this.description = description;
        this.newsletterSubscriber = newsletterSubscriber;
        this.email = email;
        this.authorities = authorities;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNewsletterSubscriber(Boolean newsletterSubscriber) {
        this.newsletterSubscriber = newsletterSubscriber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPicture() {
        return picture;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getNewsletterSubscriber() {
        return newsletterSubscriber;
    }

    public String getEmail() {
        return email;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", picture='" + picture + '\'' +
                ", description='" + description + '\'' +
                ", newsletterSubscriber=" + newsletterSubscriber +
                ", email='" + email + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
