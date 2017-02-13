package com.slabadniak.web.command;

import com.slabadniak.web.feedback.Feedback;
import com.slabadniak.web.entity.User;
import com.slabadniak.web.exeption.CommandExeption;
import com.slabadniak.web.exeption.ServiceExeption;
import com.slabadniak.web.logic.UserValidation;
import com.slabadniak.web.mail.SendEmail;
import com.slabadniak.web.service.ChangeProfileService;
import com.slabadniak.web.service.CheckUserService;
import com.slabadniak.web.util.Passwords;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class ChangeProfileCommand implements ICommand {
    private static final String LOGIN = "Such login already exist.";
    private static final String EMAIL = "Such email already exist.";
    private Feedback feedback;

    @Override
    public void execute(HttpServletRequest request) throws CommandExeption {
        HttpSession session = request.getSession();
        //UserValidation
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confpassword = request.getParameter("confpassword");
        String gender = request.getParameter("gender");
        String page = request.getParameter("page");
        String icon = (String) session.getAttribute("icon");

        request.removeAttribute(FEEDBACK);

        //validation
        if(!password.isEmpty()) {
            feedback = UserValidation.checkPassword(password);
            if (feedback.isWritten()) {
                request.setAttribute(FEEDBACK, feedback);
                return;
            }
        }
        feedback = UserValidation.passwordsEqual(password,confpassword);
        if(feedback.isWritten()){
            request.setAttribute(FEEDBACK, feedback);
            return;
        }
        feedback = UserValidation.checkLogin(login);
        if(feedback.isWritten()){
            request.setAttribute(FEEDBACK, feedback);
            return;
        }
        feedback = UserValidation.checkEmail(email);
        if(feedback.isWritten()){
            request.setAttribute(FEEDBACK, feedback);
            return;
        }



        User modified = new User(login, email, password, gender, icon);
        if(modified.getPassword() != null && !modified.getPassword().isEmpty()) {
            modified.setPassword(Passwords.hashPassword(password));
        }
        User unmodified = (User) session.getAttribute("user");

        try {
        if(CheckUserService.isLoginExist(modified) && !modified.getLogin().equals(unmodified.getLogin())) {
            feedback.setMessage(LOGIN);
            request.setAttribute(FEEDBACK, feedback);
            return;
        }

        if(CheckUserService.isEmailExist(modified) && !modified.getEmail().equals(unmodified.getEmail())) {
            feedback.setMessage(EMAIL);
            request.setAttribute(FEEDBACK, feedback);
            return;
        }

            ChangeProfileService.change(unmodified, modified);
            //sending email to the user
            SendEmail.send(unmodified, page);
        } catch (ServiceExeption e) {
            throw new CommandExeption("Service:", e);
        }

        setForwardPage(request);
    }

}
