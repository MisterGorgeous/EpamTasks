package com.slabadniak.web.mail;

import com.slabadniak.web.mail.EmailSending;

public class SendEmail {
    private SendEmail(){}

    public static void send(String login, String password, String gender, String email, String page){
        EmailSending sending = new EmailSending(login,password,gender,email,page);
        Thread thread = new Thread(sending);
        thread.start();
    }
}
