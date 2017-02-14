package com.slabadniak.web.command;

import com.slabadniak.web.configuration.LanguageManager;
import com.slabadniak.web.feedback.Feedback;
import com.slabadniak.web.entity.User;
import com.slabadniak.web.exeption.CommandExeption;
import com.slabadniak.web.exeption.ServiceExeption;
import com.slabadniak.web.util.UserValidation;
import com.slabadniak.web.mail.SendEmail;
import com.slabadniak.web.service.CheckUserService;
import com.slabadniak.web.service.SignInService;
import com.slabadniak.web.util.Passwords;
import javax.servlet.http.HttpServletRequest;


public class SignInCommand implements ICommand {
    Feedback feedback;


    @Override
    public void execute(HttpServletRequest request) throws CommandExeption {
        String local = (String)request.getSession().getAttribute(LOCAL);
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confpassword = request.getParameter("confpassword");
        String gender = request.getParameter("gender");
        String page = request.getParameter("page");

        setForwardPage(request);
        //remove, if stay after previous query
        request.removeAttribute(FEEDBACK);
        //validation
        feedback = UserValidation.checkPassword(password,local);
        if(feedback.isWritten()){
            request.setAttribute(FEEDBACK, feedback);
            return;
        }
        feedback = UserValidation.passwordsEqual(password,confpassword,local);
        if(feedback.isWritten()){
            request.setAttribute(FEEDBACK, feedback);
            return;
        }
        feedback = UserValidation.checkLogin(login,local);
        if(feedback.isWritten()){
            request.setAttribute(FEEDBACK, feedback);
            return;
        }
        feedback = UserValidation.checkEmail(email,local);
        if(feedback.isWritten()){
            request.setAttribute(FEEDBACK, feedback);
            return;
        }


        User user = new User(login,email,password,gender);
        user.setPassword(Passwords.hashPassword(password)); //MD5

        try {

            if(CheckUserService.isLoginExist(user)) {
                feedback.setMessage(LanguageManager.getProperty("feedback.login",local));
                request.setAttribute(FEEDBACK, feedback);
                return;
            }

            if(CheckUserService.isEmailExist(user)) {
                feedback.setMessage(LanguageManager.getProperty("feedback.email",local));
                request.setAttribute(FEEDBACK, feedback);
                return;
            }

            SignInService.signin(user);
            user.setPassword(password);
            SendEmail.send(user,page); //sending email to the user
        } catch (ServiceExeption e) {
            throw new CommandExeption("Service:", e);
        }
    }
}
