package com.slabadniak.task5.command;

import com.slabadniak.task5.configuration.ConfigurationManager;
import com.slabadniak.task5.entity.ClientType;
import com.slabadniak.task5.entity.User;
import com.slabadniak.task5.dao.DefaultDAO;
import com.slabadniak.task5.pool.ConnectionPool;
import com.slabadniak.task5.pool.Wrapper;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LogInCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request) {
        LOGGER.log(Level.DEBUG, "LOGIN");

        ConnectionPool pool = ConnectionPool.getInstance();
        DefaultDAO defaultDAO = null;
        //boolean logIn = false;
        ResultSet set = null;
        try {
            Wrapper connection = pool.getConnection();
            defaultDAO = new DefaultDAO(connection);
            User user = new User(request.getParameter("login"), request.getParameter("pass"));
            set = defaultDAO.LogIn(user);
            pool.closeConnection(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        HttpSession session = request.getSession();

        try {
            if(set.next()){
                //session.g("page", ConfigurationManager.getProperty("path.page.main"));
                session.setAttribute("userStatus", ClientType.USER);
                session.setAttribute("userName", request.getParameter("login"));
                CommandFactory.create("cross").execute(request);
                //LOGGER.log(Level.DEBUG, "Seccess");
            } else{
                //LOGGER.log(Level.DEBUG, "Fail");
                session.setAttribute("currentJSP", ConfigurationManager.getProperty("path.page.login"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
