package com.slabadniak.task5.command;

import com.slabadniak.task5.dao.UserDAO;
import com.slabadniak.task5.entity.Movie;
import com.slabadniak.task5.entity.User;
import com.slabadniak.task5.entity.UsersAssessment;
import com.slabadniak.task5.exeption.CommandExeption;
import com.slabadniak.task5.exeption.ServiceExeption;
import com.slabadniak.task5.pool.ConnectionPool;
import com.slabadniak.task5.pool.Wrapper;
import com.slabadniak.task5.service.ChangeProfileService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class ChangeProfileCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request) throws CommandExeption {
        HttpSession session = request.getSession();
        //Validation
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String icon = (String) session.getAttribute("icon");

        User modified = new User(login, email, password, gender, icon);
        modified.hashPassword();
        User unmodified = (User) session.getAttribute("user");

        ChangeProfileService service = new ChangeProfileService();
        try {
            service.change(unmodified, modified);
        } catch (ServiceExeption e) {
            throw new CommandExeption("Service:", e);
        }
        setForwardPage(request);

    }

}
