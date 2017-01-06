package com.slabadniak.task5.command;

import com.slabadniak.task5.dao.AdminDAO;
import com.slabadniak.task5.dao.DefaultDAO;
import com.slabadniak.task5.entity.Movie;
import com.slabadniak.task5.entity.User;
import com.slabadniak.task5.pool.ConnectionPool;
import com.slabadniak.task5.pool.Wrapper;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UserBannnedCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        List<User> users = (List<User>) session.getAttribute("users");
        int index =  Integer.parseInt(request.getParameter("userId"));
        User user = users.get(index);

        //reverse value
        user.setBanned(!user.isBanned());

        ConnectionPool pool = ConnectionPool.getInstance();
        AdminDAO adminDAO = null;


        try {
            Wrapper connection = pool.getConnection();
            adminDAO = new AdminDAO(connection);
            adminDAO.userBaning(user);
            pool.closeConnection(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


       // CommandFactory.create("users").execute(request);

       // CommandFactory.create("cross").execute(request);

    }
}