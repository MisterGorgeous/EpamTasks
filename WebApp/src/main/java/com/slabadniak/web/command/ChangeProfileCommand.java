package com.slabadniak.web.command;

import com.slabadniak.web.configuration.LanguageManager;
import com.slabadniak.web.feedback.Feedback;
import com.slabadniak.web.entity.User;
import com.slabadniak.web.exeption.CommandExeption;
import com.slabadniak.web.exeption.ServiceExeption;
import com.slabadniak.web.util.UserValidation;
import com.slabadniak.web.mail.SendEmail;
import com.slabadniak.web.service.ChangeProfileService;
import com.slabadniak.web.service.CheckUserService;
import com.slabadniak.web.util.Passwords;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class ChangeProfileCommand implements ICommand {
    private Feedback feedback;

    @Override
    public void execute(HttpServletRequest request) throws CommandExeption {
        HttpSession session = request.getSession();
        String local = (String) request.getSession().getAttribute(LOCAL);
        //UserValidation
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confpassword = request.getParameter("confpassword");
        String gender = request.getParameter("gender");
        String page = request.getParameter("page");
        String currentPassword = request.getParameter("oldpassword");
        String icon = (String) session.getAttribute("icon");

        request.removeAttribute(FEEDBACK);

        //validation
        if (!password.isEmpty()) {
            feedback = UserValidation.checkPassword(password, local);
            if (feedback.isWritten()) {
                request.setAttribute(FEEDBACK, feedback);
                return;
            }
        }
        feedback = UserValidation.passwordsEqual(password, confpassword, local);
        if (feedback.isWritten()) {
            request.setAttribute(FEEDBACK, feedback);
            return;
        }
        feedback = UserValidation.checkLogin(login, local);
        if (feedback.isWritten()) {
            request.setAttribute(FEEDBACK, feedback);
            return;
        }
        feedback = UserValidation.checkEmail(email, local);
        if (feedback.isWritten()) {
            request.setAttribute(FEEDBACK, feedback);
            return;
        }


        User modified = new User(login, email, password, gender, icon);
        if (modified.getPassword() != null && !modified.getPassword().isEmpty()) {
            modified.setPassword(Passwords.hashPassword(password));
        }
        User unmodified = (User) session.getAttribute("user");

        try {
            if (CheckUserService.isLoginExist(modified) && !modified.getLogin().equals(unmodified.getLogin())) {
                feedback.setMessage(LanguageManager.getProperty("feedback.login", local));
                request.setAttribute(FEEDBACK, feedback);
                return;
            }

            if (CheckUserService.isEmailExist(modified) && !modified.getEmail().equals(unmodified.getEmail())) {
                feedback.setMessage(LanguageManager.getProperty("feedback.email", local));
                request.setAttribute(FEEDBACK, feedback);
                return;
            }


            if (!CheckUserService.checkPassword(new User(unmodified.getLogin(), Passwords.hashPassword(currentPassword)))) {
                feedback.setMessage(LanguageManager.getProperty("feedback.icurpassword", local));
                request.setAttribute(FEEDBACK, feedback);
                return;
            }

            ChangeProfileService.change(unmodified, modified);
            modified.setPassword(password.isEmpty() ? currentPassword : password);
            SendEmail.send(modified, page);//sending email to the user
        } catch (ServiceExeption e) {
            throw new CommandExeption("Service:", e);
        }

        setForwardPage(request);
    }

}
