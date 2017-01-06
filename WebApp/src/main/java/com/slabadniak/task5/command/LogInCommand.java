package com.slabadniak.task5.command;

import com.slabadniak.task5.configuration.ConfigurationManager;
import com.slabadniak.task5.entity.UserType;
import com.slabadniak.task5.entity.User;
import com.slabadniak.task5.dao.DefaultDAO;
import com.slabadniak.task5.pool.ConnectionPool;
import com.slabadniak.task5.pool.Wrapper;
import com.slabadniak.task5.sessioncontent.DataContext;
import com.slabadniak.task5.sessioncontent.UserContent;
import org.apache.logging.log4j.Level;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class LogInCommand implements ICommand {

    @Override
    public void execute(HttpServletRequest request) {


        ConnectionPool pool = ConnectionPool.getInstance();
        DefaultDAO defaultDAO = null;
        //boolean logIn = false;
        UserContent content = new UserContent();

        try {
            Wrapper connection = pool.getConnection();
            defaultDAO = new DefaultDAO(connection);
            User user = new User(request.getParameter("login"), request.getParameter("pass"));
            content.insert(defaultDAO.LogIn(user));
            setAtributes(content, request);
            pool.closeConnection(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setForwardPage(request);

    }

    private void setAtributes(DataContext content, HttpServletRequest request) {
        //request.setAttribute("users", (List<User>) content.get());
        HttpSession session = request.getSession();
        List<User> users = (List<User>) content.get();

        if(! users.isEmpty()) {
            User user = users.get(0);
            session.setAttribute("user", user);
            if(user.isAdmin()) {
                session.setAttribute("userStatus", UserType.ADMINISTRATOR);
            }
            else{
                session.setAttribute("userStatus", UserType.USER);
            }
            LOGGER.log(Level.INFO, "Loged in");
            System.out.println("Logged in");
        } else {
            //feedback
        }
    }
}