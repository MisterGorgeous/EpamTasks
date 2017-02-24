package com.slabadniak.web.mail;


import com.slabadniak.web.annotation.Slobolize;
import com.slabadniak.web.annotation.UserLetter;
import com.slabadniak.web.entity.User;
import com.slabadniak.web.service.MakeMailMessageService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;


/**
 * Task that sends email to the user after registration and changing profile.
 */
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
        List<UserLetter> letters = null;
        String subject = MakeMailMessageService.makeSubject(page);
        String message = MakeMailMessageService.makeMessage(user,page);
        UserLetter newLetter = new UserLetter(user.getEmail(),subject,message);

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

            letters = Slobolize.readLetters();
            if(Objects.isNull(letters)) {
              letters = new ArrayList<>();
            }

            letters.add(newLetter);

        try {
            for(UserLetter letter: letters) {
                Message msg = new MimeMessage(session);  // creates a new e-mail message

                msg.setFrom(new InternetAddress(FROM));
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(letter.getEmail()));   // InternetAddress[] toAddresses = { new InternetAddress(mailTo) };
                msg.setSubject(letter.getSubject());
                msg.setSentDate(new Date());
                msg.setText(letter.getMessage()); // set plain text message

                Transport.send(msg); // sends the e-mail
            }

            Slobolize.cleanFile();

        } catch (Exception e) {
            Slobolize.writeLetter(newLetter);
            LOGGER.log(Level.INFO, "Messege: " + message + " doesn't send  to user.");
        }
    }
}
