package com.slabadniak.web.logic;

import com.slabadniak.web.feedback.Feedback;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidation {
    private static final String LOGIN = "[A-Za-z]\\w{4,32}";
    private static final String PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,32}$";
    private static final String EMAIL = "^[\\w.!#$%&’*+/=?^_`{|}~-]+@[\\w-]+(?:\\.[\\w-]+)*$";
    private static final String INCORRECTLOGIN = "Incorrect authorization.It must contain only alphabetic and numeric symbols.";
    private static final String INCORRECTPASSWORD = "Incorrect password.It must contain at least one upercase and one numeric letter.";
    private static final String INCORRECTEMAIL = "Incorrect email.";
    private static final String EQUALPASSWORDS = "Passwords must be different.";

    public static Feedback passwordsEqual(String pass1, String pass2){
        if(pass1.equals(pass2)){
            return new Feedback();
        }
        Feedback feedback = new Feedback();
        feedback.setMessage(EQUALPASSWORDS);
        return feedback;
    }

    public static Feedback checkLogin(String login){
        Pattern p = Pattern.compile(LOGIN);
        Matcher m = p.matcher(login);

        if (m.find()) {
            return new Feedback();
        }
        Feedback feedback = new Feedback();
        feedback.setMessage(INCORRECTLOGIN);
        return feedback;
    }

    public static Feedback checkEmail(String email){
        Pattern p = Pattern.compile(EMAIL);
        Matcher m = p.matcher(email);

        if (m.find()) {
            return new Feedback();
        }
        Feedback feedback = new Feedback();
        feedback.setMessage(INCORRECTEMAIL);
        return feedback;
    }

    public static Feedback checkPassword(String password){
        Pattern p = Pattern.compile(PASSWORD);
        Matcher m = p.matcher(password);
        if (m.find()) {
            return new Feedback();
        }
        Feedback feedback = new Feedback();
        feedback.setMessage(INCORRECTPASSWORD);
        return feedback;
    }

}
