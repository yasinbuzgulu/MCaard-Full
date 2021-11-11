package com.example.MCardSpring.MainModel;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Giriş için kulanılan kullanıcı entity si
 */
@Entity
public class User {
    /**
     * Kullanıcının uqiue id si
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Kullanıcı ismi
     */
    @NotBlank
    @Size(max = 20)
    private String username;

    /**
     * Kullanıcı e-maili
     */
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    /**
     * Kullanıcı şifresi
     */
    @NotBlank
    @Size(max = 120)
    private String password;

    /**
     * Kullanıcı şifresi
     */
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
