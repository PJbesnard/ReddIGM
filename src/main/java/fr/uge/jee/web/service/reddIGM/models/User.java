package fr.uge.jee.web.service.reddIGM.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;
import java.util.stream.Collectors;

@Entity(name = "Users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min = 3, max = 20)
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

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.PERSIST)
    private Set<Authority> authorities;

    public User() {}

    public User(String username, String password, String email, Set<Authority> authorities, String picture, String description, boolean newsletterSubscriber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
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



    public void setId(long id) {
        this.id = id;
    }


    @Override
    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
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
                ", authorities=" + authorities +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                newsletterSubscriber == user.newsletterSubscriber &&
                username.equals(user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(picture, user.picture) &&
                Objects.equals(description, user.description) &&
                Objects.equals(email, user.email) &&
                authorities.equals(user.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, picture, description, newsletterSubscriber, email, authorities);
    }
}
