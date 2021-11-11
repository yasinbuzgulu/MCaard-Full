package com.example.MCardSpring.Payload.Request;

import javax.validation.constraints.NotBlank;

/**
 * Giriş istek verilerinin tutulduğu sınıfı
 */
public class LoginRequest {
    /**
     * Giriş isteği içindeki kullanıcı ismi
     */
    @NotBlank
    private String username;

    /**
     * Giriş isteği içindeki kullanıcı şifresi
     */
    @NotBlank
    private String password;

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
}
