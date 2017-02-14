package com.slabadniak.web.util;

import com.slabadniak.web.configuration.LanguageManager;
import com.slabadniak.web.feedback.Feedback;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * This utils class is used to validate user's info.
 **/
public class UserValidation {
    private static final String LOGIN = "[A-Za-z]\\w{4,32}";
    private static final String PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,32}$";
    private static final String EMAIL = "^[\\w.!#$%&’*+/=?^_`{|}~-]+@[\\w-]+(?:\\.[\\w-]+)*$";

    public static Feedback passwordsEqual(String pass1, String pass2,String local){
        if(pass1.equals(pass2)){
            return new Feedback();
        }
        Feedback feedback = new Feedback();
        feedback.setMessage( LanguageManager.getProperty("feedback.equalpassword",local));
        return feedback;
    }

    public static Feedback checkLogin(String login,String local){
        Pattern p = Pattern.compile(LOGIN);
        Matcher m = p.matcher(login);

        if (m.find()) {
            return new Feedback();
        }
        Feedback feedback = new Feedback();
        feedback.setMessage( LanguageManager.getProperty("feedback.incorlogin",local));
        return feedback;
    }

    public static Feedback checkEmail(String email,String local){
        Pattern p = Pattern.compile(EMAIL);
        Matcher m = p.matcher(email);
        if (m.find()) {
            return new Feedback();
        }
        Feedback feedback = new Feedback();
        feedback.setMessage( LanguageManager.getProperty("feedback.incoremil",local));
        return feedback;
    }

    public static Feedback checkPassword(String password,String local){
        Pattern p = Pattern.compile(PASSWORD);
        Matcher m = p.matcher(password);
        if (m.find()) {
            return new Feedback();
        }
        Feedback feedback = new Feedback();
        feedback.setMessage( LanguageManager.getProperty("feedback.incorpassword",local));
        return feedback;
    }
}
