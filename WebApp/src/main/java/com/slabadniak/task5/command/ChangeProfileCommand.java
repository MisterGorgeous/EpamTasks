package com.slabadniak.task5.command;

import com.slabadniak.task5.feedback.Feedback;
import com.slabadniak.task5.entity.User;
import com.slabadniak.task5.exeption.CommandExeption;
import com.slabadniak.task5.exeption.ServiceExeption;
import com.slabadniak.task5.logic.UserValidation;
import com.slabadniak.task5.service.ChangeProfileService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class ChangeProfileCommand implements ICommand {
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
        modified.hashPassword();
        User unmodified = (User) session.getAttribute("user");

        try {
            ChangeProfileService.change(unmodified, modified);
        } catch (ServiceExeption e) {
            throw new CommandExeption("Service:", e);
        }

        setForwardPage(request);
    }

}
