package com.slabadniak.task5.command;

import com.slabadniak.task5.dao.AdminDAO;
import com.slabadniak.task5.entity.User;
import com.slabadniak.task5.exeption.CommandExeption;
import com.slabadniak.task5.exeption.ServiceExeption;
import com.slabadniak.task5.pool.ConnectionPool;
import com.slabadniak.task5.pool.Wrapper;
import com.slabadniak.task5.service.ChangeProfileService;
import com.slabadniak.task5.service.ChangeStatusService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ChangeStatusCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request) throws CommandExeption {

        HttpSession session = request.getSession();
        List<User> users = (List<User>) session.getAttribute("users");
        int index =  Integer.parseInt(request.getParameter("userId"));
        //int statusId = Integer.parseInt(request.getParameter("status"));
        float statusId = Float.parseFloat(request.getParameter("status"));
        User user = users.get(index);

        ChangeStatusService service = new ChangeStatusService();


        try {
            service.change(user,(int)statusId);
        } catch (ServiceExeption e) {
            throw new CommandExeption("Service:", e);
        }



        //Sellect all users from DB
      //  CommandFactory.create("users").execute(request);


    }

   private void setStatus(User user,int status){
        switch (status){
            case 1: user.setStatus("beginer");
                break;
            case 2: user.setStatus("fan");
                break;
            default: user.setStatus("expert");
        }
    }
}
