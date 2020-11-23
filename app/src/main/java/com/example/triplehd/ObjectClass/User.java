package com.example.triplehd.ObjectClass;

public class User {
    private String username;
    private String email;
    private Boolean isLogin;
    private String id;
    private String role;

    public User(String username, String email, Boolean isLogin, String id, String role) {
        this.username = username;
        this.email = email;
        this.isLogin = isLogin;
        this.id = id;
        this.role = role;
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

    public Boolean getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(Boolean isLogin) {
        this.isLogin = isLogin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", isLogin='" + isLogin + '\'' +
                ", id='" + id + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
