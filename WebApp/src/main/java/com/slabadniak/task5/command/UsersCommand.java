package com.slabadniak.task5.command;

import com.slabadniak.task5.dao.AdminDAO;
import com.slabadniak.task5.dao.DefaultDAO;
import com.slabadniak.task5.entity.Actor;
import com.slabadniak.task5.entity.User;
import com.slabadniak.task5.pool.ConnectionPool;
import com.slabadniak.task5.pool.Wrapper;
import com.slabadniak.task5.sessioncontent.DataContext;
import com.slabadniak.task5.sessioncontent.UserContent;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UsersCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request) {
        ConnectionPool pool = ConnectionPool.getInstance();
        AdminDAO adminDAO = null;
        UserContent content = new UserContent();

        try {
            Wrapper connection = pool.getConnection();
            adminDAO = new AdminDAO(connection);
            content.insert(adminDAO.users());
            //add genres to request atr
            setAtributes(content,request);
            pool.closeConnection(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // HttpSession session = request.getSession(true);

        CommandFactory.create("cross").execute(request);
    }

    private void setAtributes(DataContext content, HttpServletRequest request){
        //request.setAttribute("users", (List<User>) content.get());
        HttpSession session = request.getSession();
        session.setAttribute("users",(List<User>) content.get());
    }
}
