package com.slabadniak.web.entity;

import org.apache.commons.codec.digest.DigestUtils;

public class User {
    private String login;
    private String email;
    private String password;
    private String status;
    private String gender;
    private boolean banned;
    private String icon;
    private boolean admin;


    public User(String login, String email, String password,String gender,String icon) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.icon = icon;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login, String email,String password,String status, boolean banned,String gender,String icon,boolean admin) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.status = status;
        this.banned = banned;
        this.gender = gender;
        this.icon = icon;
        this.admin = admin;
    }

    public User(String login, String email, String password, String gender) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.gender = gender;

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

    public String getGender() {
        return gender;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setLogin(String login) {

        this.login = login;
    }

    public boolean isAdmin() {
        return admin;
    }


    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }

        if(obj == null){
            return false;
        }

        if(this.getClass() != obj.getClass()){return false;}

        User user = (User) obj;
        return this.login.equals(user.getLogin()) && this.email.equals(user.getEmail()) && this.password.equals(user.getPassword())
                && this.status.equals(user.getStatus()) && this.gender.equals(user.getGender());
    }

    @Override
    public int hashCode() {
        int result = login.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }
}