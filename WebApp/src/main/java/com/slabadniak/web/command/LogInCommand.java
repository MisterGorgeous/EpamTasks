package com.slabadniak.web.command;

import com.slabadniak.web.configuration.LanguageManager;
import com.slabadniak.web.feedback.Feedback;
import com.slabadniak.web.constant.UserType;
import com.slabadniak.web.entity.User;
import com.slabadniak.web.exeption.CommandExeption;
import com.slabadniak.web.exeption.ServiceExeption;
import com.slabadniak.web.util.UserValidation;
import com.slabadniak.web.service.CheckUserService;
import com.slabadniak.web.service.AuthorizationService;
import com.slabadniak.web.content.UserContent;
import com.slabadniak.web.util.Passwords;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


public class LogInCommand implements ICommand {
    private static final int UNIQUE = 0;
    private Feedback feedback;


    public LogInCommand() {
        this.feedback = new Feedback();
    }

    @Override
    public void execute(HttpServletRequest request) throws CommandExeption {
        String local = (String)request.getSession().getAttribute(LOCAL);

        String login = request.getParameter("login");
        String password = request.getParameter("pass");

        //if nothing is specified, don't close modal
        if(login == null || password == null){
            feedback = new Feedback();
            feedback.setMessage("");
            request.setAttribute(FEEDBACK, feedback);
            return;
        }

        //remove, if stay after previous query
        request.removeAttribute(FEEDBACK);

        feedback = UserValidation.checkPassword(password,local);
        if (feedback.isWritten()) {
            request.setAttribute(FEEDBACK, feedback);
            return;
        }
        feedback = UserValidation.checkLogin(login,local);
        if (feedback.isWritten()) {
            request.setAttribute(FEEDBACK, feedback);
            return;
        }

        User user = new User(login, password);
        user.setPassword(Passwords.hashPassword(password)); //MD5
        UserContent content;

        try {

            if (!CheckUserService.isLoginExist(user)) {
                feedback.setMessage(LanguageManager.getProperty("feedback.ilogin",local));
                request.setAttribute(FEEDBACK, feedback);
                return;
            }

            if (!CheckUserService.checkPassword(user)) {
                feedback.setMessage(LanguageManager.getProperty("feedback.ipassword",local));
                request.setAttribute(FEEDBACK, feedback);
                return;
            }


            content = AuthorizationService.authorization(user);
        } catch (ServiceExeption e) {
            throw new CommandExeption("Service:", e);
        }


        setAtributes(content, request);
        if(!feedback.isWritten()){
            feedback.setMessage(LanguageManager.getProperty("feedback.success",local));
            setForwardPage(request);
        }

    }

    private void setAtributes(UserContent content, HttpServletRequest request) {
        String local = (String)request.getSession().getAttribute(LOCAL);
        HttpSession session = request.getSession();
        List<User> users = content.get();

        if(users.isEmpty()){
            feedback.setMessage(LanguageManager.getProperty("feedback.empty",local));
            request.setAttribute(FEEDBACK, feedback);
        }

        User user = users.get(UNIQUE);

        if (!user.isBanned()) {  //if doesn't banned
            session.setAttribute("user", user);
            if (user.isAdmin()) {
                session.setAttribute("userStatus", UserType.ADMINISTRATOR);
            } else {
                session.setAttribute("userStatus", UserType.USER);
            }
        } else {
            feedback.setMessage(LanguageManager.getProperty("feedback.banned",local));
            request.setAttribute(FEEDBACK, feedback);
        }
    }
}