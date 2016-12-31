package com.slabadniak.task5.entity;

public class User {
    private String login;
    private String email;
    private String password;
    private String status;
    private boolean banned;

    public User(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login, String email,String status, boolean banned) {
        this.login = login;
        this.email = email;
        this.status = status;
        this.banned = banned;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getStatus() {
        return status;
    }

    public String getPassword() {
        return password;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }
}
