package com.slabadniak.web.annotation;

import java.io.Serializable;


@Slobolizable
public class UserLetter implements Serializable {
    private String email;
    private String subject;
    private  String message;

    public UserLetter(String email, String subject, String message) {
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "UserLetter{" + "email='" + email + ", subject='" + subject  + ", message='" + message + '}';
    }
}
