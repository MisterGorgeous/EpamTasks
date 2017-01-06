package com.slabadniak.task5.command;

import com.slabadniak.task5.dao.AdminDAO;
import com.slabadniak.task5.entity.User;
import com.slabadniak.task5.pool.ConnectionPool;
import com.slabadniak.task5.pool.Wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ChangeStatusCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        List<User> users = (List<User>) session.getAttribute("users");
        int index =  Integer.parseInt(request.getParameter("userId"));
        //int statusId = Integer.parseInt(request.getParameter("status"));
        float statusId = Float.parseFloat(request.getParameter("status"));
        User user = users.get(index);

        //serStatus value
        setStatus(user,(int)statusId);

        ConnectionPool pool = ConnectionPool.getInstance();
        AdminDAO adminDAO = null;


        try {
            Wrapper connection = pool.getConnection();
            adminDAO = new AdminDAO(connection);
            adminDAO.changeStatus(user);
            pool.closeConnection(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        //Sellect all users from DB
      //  CommandFactory.create("users").execute(request);


    }

    void setStatus(User user,int status){
        switch (status){
            case 1: user.setStatus("beginer");
                break;
            case 2: user.setStatus("fan");
                break;
            default: user.setStatus("expert");
        }
    }
}
