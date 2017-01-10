package com.slabadniak.task5.command;

import com.slabadniak.task5.dao.AdminDAO;
import com.slabadniak.task5.dao.DefaultDAO;
import com.slabadniak.task5.entity.Movie;
import com.slabadniak.task5.entity.User;
import com.slabadniak.task5.exeption.CommandExeption;
import com.slabadniak.task5.exeption.ServiceExeption;
import com.slabadniak.task5.pool.ConnectionPool;
import com.slabadniak.task5.pool.Wrapper;
import com.slabadniak.task5.service.UserBannedService;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UserBannnedCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request) throws CommandExeption {

        HttpSession session = request.getSession();
        List<User> users = (List<User>) session.getAttribute("users");
        int index =  Integer.parseInt(request.getParameter("userId"));
        User user = users.get(index);

        UserBannedService service = new UserBannedService();

        try {
            service.ban(user);
        } catch (ServiceExeption e) {
            throw new CommandExeption("Service:", e);
        }
    }
}