package com.slabadniak.web.mail;

import com.slabadniak.web.entity.User;


/**
 * Sending of the email performs in the new thread to prevent delay in the command thread.
 * @author Slabadniak Sergei
 * @version 1.0
 */
public class SendEmail {
    private SendEmail(){}

    public static void send(User user, String page){
        EmailSending sending = new EmailSending(user,page);
        Thread thread = new Thread(sending);
        thread.start();
    }
}
