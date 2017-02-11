package com.slabadniak.web.service;

/**
 * Created by Siarhei on 11.02.2017.
 */
public class MakeMailMessageService {

    private MakeMailMessageService() {
    }

    public static String makeMessage(String login, String password, String gender, String page){
        if(page.equals("path.page.signin")) {
            return "Hello, your login:" + login + "\n      password:" + password + "\n      gender:" + gender + "\nThank you for registarion.";
        }else{
            return "Your login:" + login + "\n      password:" + password + "\n      gender:" + gender + "\nThank you for using our site.";
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
