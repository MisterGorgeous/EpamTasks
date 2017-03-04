package com.slabadniak.web.annotation;

import java.io.Serializable;


/**
 * This class is represent letter that is going to be sent on the user's email address.
 * @author Slabadniak Sergei
 * @version 1.0
 */
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
