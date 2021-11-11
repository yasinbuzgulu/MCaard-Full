package com.example.MCardSpring.Payload.Request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Yeni kullanıcı oluşturma isteğinin veri sınıfı
 */
public class SignupRequest {
    /**
     * Yeni kullanıcının kullanıcı ismi
     */
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    /**
     * Yeni kullanıcının kullanıcı e-maili
     */
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    /**
     * Yeni kullanıcının kullanıcı rolü
     */
    private Set<String> role;

    /**
     * Yeni kullanıcının kullanıcı şifresi
     */
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

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

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}
