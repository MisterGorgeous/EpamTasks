package com.slabadniak.web.command;

import com.slabadniak.web.entity.Movie;
import com.slabadniak.web.entity.User;
import com.slabadniak.web.exeption.CommandExeption;
import com.slabadniak.web.feedback.Feedback;
import com.slabadniak.web.service.MakeMailMessageService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Properties;


public class MailCommand implements ICommand {
    private static final String HOST ="smtp.gmail.com";
    private static final String PORT ="587";
    private static final String FROM ="movierating2017@gmail.com";
    private static final String PASSWORD ="Epam2017";

    @Override
    public void execute(HttpServletRequest request) throws CommandExeption {
        Feedback feedback = (Feedback)request.getAttribute(FEEDBACK);
        HttpSession session1 = request.getSession();

        if(feedback!=null && feedback.isWritten()){
            return;
        }

        // outgoing message information
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String mailTo = request.getParameter("email");
        String gender = request.getParameter("gender");
        String page = request.getParameter("page");

        //if password haven't changed, take password from user
        User user = (User) session1.getAttribute("user");
        password = password.isEmpty() ? user.getPassword() : "the same";

        String subject = MakeMailMessageService.makeSubject(page);
        String message = MakeMailMessageService.makeMessage(login,password,gender,page);

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
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            // set plain text message
            msg.setText(message);

            // sends the e-mail
            Transport.send(msg);

        } catch (Exception e) {
             throw new CommandExeption("Email doesn't send:", e);
        }

    }
}
