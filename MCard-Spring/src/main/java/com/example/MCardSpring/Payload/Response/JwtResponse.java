package com.example.MCardSpring.Payload.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Kullanıcı girişinde ve işlemlerininde kullanılacak unique token ı ve kullanıcı bilgilerinin sınıfı
 */
public class JwtResponse implements Serializable {
    /**
     * Kayıtlı kullanıcıya verilen token
     */
    @JsonProperty("corporationId")
    private String token;

    /**
     * Token verisinin prefix'i
     */
    @JsonProperty("type")
    private String type = "Bearer";

    /**
     * Kullanıcı id si
     */
    @JsonProperty("id")
    private Long id;

    /**
     * Kullanıcı ismi
     */
    @JsonProperty("username")
    private String username;

    /**
     * Kullanıcı e-maili
     */
    @JsonProperty("email")
    private String email;

    /**
     * Kullanıcı rolü/leri
     */
    @JsonProperty("roles")
    private List<String> roles;

    public JwtResponse(String token, Long id, String username, String email, List<String> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public JwtResponse() {
    }

    public JwtResponse(String token, String type, Long id, String username, String email, List<String> roles) {
        this.token = token;
        this.type = type;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JwtResponse that = (JwtResponse) o;
        return token.equals(that.token) && type.equals(that.type) && id.equals(that.id) && username.equals(that.username) && email.equals(that.email) && roles.equals(that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, type, id, username, email, roles);
    }

    @Override
    public String toString() {
        return "JwtResponse{" +
                "token='" + token + '\'' +
                ", type='" + type + '\'' +
                ", id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}
