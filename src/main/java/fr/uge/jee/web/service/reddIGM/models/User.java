package fr.uge.jee.web.service.reddIGM.models;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity(name = "Users")
public class User implements UserDetails {

    public enum Authority {
        USER,
        ADMIN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(unique = true)
    private String username;

    //@NotNull
    private String password;

    private String picture;

    private String description;

    private boolean newsletterSubscriber;

    //@NotNull
    @Email
    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Authority authority;

    public User() {}

    public User(String username, String password, String email, Authority authority, String picture, String description, boolean newsletterSubscriber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.authority = authority;
        this.picture = picture;
        this.description = description;
        this.newsletterSubscriber = newsletterSubscriber;
    }

    public long getId() {
        return id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
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

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(authority.name()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                newsletterSubscriber == user.newsletterSubscriber &&
                username.equals(user.username) &&
                password.equals(user.password) &&
                Objects.equals(picture, user.picture) &&
                Objects.equals(description, user.description) &&
                email.equals(user.email) &&
                authority == user.authority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, picture, description, newsletterSubscriber, email, authority);
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", picture='" + picture + '\'' +
                ", description='" + description + '\'' +
                ", newsletterSubscriber=" + newsletterSubscriber +
                ", email='" + email + '\'' +
                ", authority=" + authority +
                '}';
    }
}
