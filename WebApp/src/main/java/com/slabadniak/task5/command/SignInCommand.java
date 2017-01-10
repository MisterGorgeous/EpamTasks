package com.slabadniak.task5.command;

import com.slabadniak.task5.entity.Feedback;
import com.slabadniak.task5.entity.User;
import com.slabadniak.task5.dao.DefaultDAO;
import com.slabadniak.task5.exeption.CommandExeption;
import com.slabadniak.task5.exeption.ServiceExeption;
import com.slabadniak.task5.pool.ConnectionPool;
import com.slabadniak.task5.pool.Wrapper;
import com.slabadniak.task5.service.SignInService;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;

public class SignInCommand implements ICommand {
   // private static final String DEFAULT_ICON = "/img/photo.png";

    @Override
    public void execute(HttpServletRequest request) throws CommandExeption {
        Feedback feedback = new Feedback();

        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");

        User user = new User(login,email,password,gender);
        SignInService service = new SignInService();
        boolean exist;

        try {
             exist =  service.signin(user);
        } catch (ServiceExeption e) {
            throw new CommandExeption("Service:", e);
        }


        if (exist == false ) {
            setForwardPage(request);
        } else {
            feedback.write("Incorrect login or password");
            request.setAttribute(FEEDBACK, feedback);
        }

    }
}
