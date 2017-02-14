package com.slabadniak.web.service;

import com.slabadniak.web.entity.User;
import com.slabadniak.web.util.Passwords;
import sun.security.util.Password;

public class MakeMailMessageService {

    private MakeMailMessageService() {
    }

    public static String makeMessage(User user, String page){
        if(page.equals("path.page.signin")) {
            return "Hello, your login:" +  user.getLogin() + "\n      password:" + user.getPassword() + "\n      gender:" + user.getGender() +"\n      status:" + user.getStatus() +"\nThank you for registarion.";
        }else{
            return "Your login:" +  user.getLogin() + "\n      password:" + user.getPassword() + "\n      gender:" + user.getGender() +"\n      status:" + user.getStatus() + "\nThank you for using our site.";
        }

    }

    public static String makeSubject(String page){
        if(page.equals("path.page.signin")) {
             return "Registration on KinoRating.";
        }else{
            return "Your account has been modified.";
        }
    }
}
