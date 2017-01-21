package com.slabadniak.task5.command;

import com.slabadniak.task5.entity.Feedback;
import com.slabadniak.task5.entity.User;
import com.slabadniak.task5.exeption.CommandExeption;
import com.slabadniak.task5.exeption.ServiceExeption;
import com.slabadniak.task5.logic.Validation;
import com.slabadniak.task5.service.CheckUserService;
import com.slabadniak.task5.service.SignInService;

import javax.servlet.http.HttpServletRequest;

public class SignInCommand implements ICommand {
   // private static final String DEFAULT_ICON = "/img/photo.png";
    Feedback feedback;
    private static final String LOGIN = "Such authorization already exist.";
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
        feedback = Validation.checkPassword(password);
        if(feedback.isWritten()){
            request.setAttribute(FEEDBACK, feedback);
            return;
        }
        feedback = Validation.passwordsEqual(password,confpassword);
        if(feedback.isWritten()){
            request.setAttribute(FEEDBACK, feedback);
            return;
        }
        feedback = Validation.checkLogin(login);
        if(feedback.isWritten()){
            request.setAttribute(FEEDBACK, feedback);
            return;
        }
        feedback = Validation.checkEmail(email);
        if(feedback.isWritten()){
            request.setAttribute(FEEDBACK, feedback);
            return;
        }


        User user = new User(login,email,password,gender);
        user.hashPassword(); //MD5

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
