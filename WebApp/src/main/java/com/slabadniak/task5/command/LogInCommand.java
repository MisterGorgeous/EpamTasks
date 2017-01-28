package com.slabadniak.task5.command;

import com.slabadniak.task5.feedback.Feedback;
import com.slabadniak.task5.entity.UserType;
import com.slabadniak.task5.entity.User;
import com.slabadniak.task5.exeption.CommandExeption;
import com.slabadniak.task5.exeption.ServiceExeption;
import com.slabadniak.task5.logic.UserValidation;
import com.slabadniak.task5.service.CheckUserService;
import com.slabadniak.task5.service.AuthorizationService;
import com.slabadniak.task5.content.UserContent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


public class LogInCommand implements ICommand {
    private static final int UNIQUE = 0;
    private Feedback feedback;
    private static final String LOGIN = "Such login doesn't exist.";
    private static final String PASSWORD = "Incorrect password.";
    private static final String BANNED = "You've been banned.";
    private static final String EMPTY = "No such user.";

    public LogInCommand() {
        this.feedback = new Feedback();
    }

    @Override
    public void execute(HttpServletRequest request) throws CommandExeption {

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

        feedback = UserValidation.checkPassword(password);
        if (feedback.isWritten()) {
            request.setAttribute(FEEDBACK, feedback);
            return;
        }
        feedback = UserValidation.checkLogin(login);
        if (feedback.isWritten()) {
            request.setAttribute(FEEDBACK, feedback);
            return;
        }

        User user = new User(login, password);
        user.hashPassword(); //MD5
        UserContent content;

        try {

            if (!CheckUserService.isLoginExist(user)) {
                feedback.setMessage(LOGIN);
                request.setAttribute(FEEDBACK, feedback);
                return;
            }

            if (!CheckUserService.checkPassword(user)) {
                feedback.setMessage(PASSWORD);
                request.setAttribute(FEEDBACK, feedback);
                return;
            }


            content = AuthorizationService.authorization(user);
        } catch (ServiceExeption e) {
            throw new CommandExeption("Service:", e);
        }


        setAtributes(content, request);
        if(!feedback.isWritten()){
            setForwardPage(request);
        }

    }

    private void setAtributes(UserContent content, HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<User> users = content.get();

        if(users.isEmpty()){
            feedback.setMessage(EMPTY);
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
            //LOGGER.log(Level.DEBUG, "Loged in");
        } else {
            feedback.setMessage(BANNED);
            request.setAttribute(FEEDBACK, feedback);
        }
    }
}