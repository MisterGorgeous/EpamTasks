package com.slabadniak.web.mail;

import com.slabadniak.web.entity.User;

public class SendEmail {
    private SendEmail(){}

    public static void send(User user, String page){
        EmailSending sending = new EmailSending(user,page);
        Thread thread = new Thread(sending);
        thread.start();
    }
}
