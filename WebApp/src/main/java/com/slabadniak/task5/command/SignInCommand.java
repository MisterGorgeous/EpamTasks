package com.slabadniak.task5.command;

import com.slabadniak.task5.entity.User;
import com.slabadniak.task5.dao.DefaultDAO;
import com.slabadniak.task5.pool.ConnectionPool;
import com.slabadniak.task5.pool.Wrapper;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;

public class SignInCommand implements ICommand {
    private static final String DEFAULT_ICON = "/img/photo.png";

    @Override
    public void execute(HttpServletRequest request) {
        ConnectionPool pool = ConnectionPool.getInstance();
        DefaultDAO defaultDAO = null;

        try {
            Wrapper connection = pool.getConnection();
            defaultDAO = new DefaultDAO(connection);
            User user = new User(request.getParameter("login"),request.getParameter("email"),
                    request.getParameter("password"),request.getParameter("gender"));
            defaultDAO.signIn(user);
            pool.closeConnection(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

       // HttpSession session = request.getSession(true);

        setForwardPage(request);

    }
}
