package com.slabadniak.web.command;

import com.slabadniak.web.feedback.Feedback;
import com.slabadniak.web.entity.User;
import com.slabadniak.web.exeption.CommandExeption;
import com.slabadniak.web.exeption.ServiceExeption;
import com.slabadniak.web.logic.UserValidation;
import com.slabadniak.web.service.CheckUserService;
import com.slabadniak.web.service.SignInService;
import com.slabadniak.web.util.Util;

import javax.servlet.http.HttpServletRequest;

public class SignInCommand implements ICommand {
    Feedback feedback;
    private static final String LOGIN = "Such login already exist.";
    private static final String EMAIL = "Such email already exist.";

    @Override
    public void execute(HttpServletRequest request) throws CommandExeption {

        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confpassword = request.getParameter("confpassword");
        String gender = request.getParameter("gender");

        setForwardPage(request);
        //remove, if stay after previous query
        request.removeAttribute(FEEDBACK);
        //validation
        feedback = UserValidation.checkPassword(password);
        if(feedback.isWritten()){
            request.setAttribute(FEEDBACK, feedback);
            return;
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


        User user = new User(login,email,password,gender);
        user.setPassword(Util.hashPassword(password)); //MD5

        try {

            if(CheckUserService.isLoginExist(user)) {
                feedback.setMessage(LOGIN);
                request.setAttribute(FEEDBACK, feedback);
                return;
            }

            if(CheckUserService.isEmailExist(user)) {
                feedback.setMessage(EMAIL);
                request.setAttribute(FEEDBACK, feedback);
                return;
            }

            SignInService.signin(user);
        } catch (ServiceExeption e) {
            throw new CommandExeption("Service:", e);
        }
    }
}
