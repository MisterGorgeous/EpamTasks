package com.slabadniak.task5.command;

import com.slabadniak.task5.entity.Feedback;
import com.slabadniak.task5.entity.UserType;
import com.slabadniak.task5.entity.User;
import com.slabadniak.task5.exeption.CommandExeption;
import com.slabadniak.task5.exeption.ServiceExeption;
import com.slabadniak.task5.service.LogInService;
import com.slabadniak.task5.content.DataContext;
import com.slabadniak.task5.content.UserContent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


public class LogInCommand implements ICommand {
    private static final int UNIQUE = 0;

    @Override
    public void execute(HttpServletRequest request) throws CommandExeption {
        Feedback feedback = new Feedback();
        String login = request.getParameter("login");
        String password = request.getParameter("pass");

        User user = new User(login, password);
        UserContent content;


        LogInService service = new LogInService();

        try {
            content = service.login(user);
        } catch (ServiceExeption e) {
            throw new CommandExeption("Service:", e);
        }

        if (content != null) {
            setAtributes(content, request);
            setForwardPage(request);
        } else {
            feedback.write("Incorrect login or password");
            request.setAttribute(FEEDBACK, feedback);
        }

    }

    private void setAtributes(DataContext content, HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<User> users = (List<User>) content.get();

        User user = users.get(UNIQUE);
        session.setAttribute("user", user);
        if (user.isAdmin()) {
            session.setAttribute("userStatus", UserType.ADMINISTRATOR);
        } else {
            session.setAttribute("userStatus", UserType.USER);
        }
        //LOGGER.log(Level.DEBUG, "Loged in");
        System.out.println("Logged in");
    }
}