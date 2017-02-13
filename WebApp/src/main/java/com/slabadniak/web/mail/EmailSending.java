package com.slabadniak.web.mail;


import com.slabadniak.web.entity.User;
import com.slabadniak.web.service.MakeMailMessageService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;


public class EmailSending implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(EmailSending.class);
    private static final String HOST ="smtp.gmail.com";
    private static final String PORT ="587";
    private static final String FROM ="movierating2017@gmail.com";
    private static final String PASSWORD ="Epam2017";
    private String page;
    private User user;

    public EmailSending(User user, String page) {
        this.user = user;
        this.page = page;
    }

    @Override
    public void run() {

        String subject = MakeMailMessageService.makeSubject(page);
        String message = MakeMailMessageService.makeMessage(user,page);

        try{
            Properties properties = new Properties();

            properties.put("mail.smtp.host", HOST);
            properties.put("mail.smtp.port", PORT);
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");

            // creates a new session with an authenticator
            Authenticator auth = new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(FROM, PASSWORD);
                }
            };

            Session session = Session.getInstance(properties, auth);

            // creates a new e-mail message
            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(FROM));
            // InternetAddress[] toAddresses = { new InternetAddress(mailTo) };
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            // set plain text message
            msg.setText(message);

            // sends the e-mail
            Transport.send(msg);

        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Messege: " + message + " doesn't send  to user.");
        }
    }
}
