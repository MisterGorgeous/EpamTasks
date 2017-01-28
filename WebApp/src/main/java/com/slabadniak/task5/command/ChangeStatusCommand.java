package com.slabadniak.task5.command;

import com.slabadniak.task5.entity.User;
import com.slabadniak.task5.exeption.CommandExeption;
import com.slabadniak.task5.exeption.ServiceExeption;
import com.slabadniak.task5.service.ChangeStatusService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ChangeStatusCommand implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(ChangeStatusCommand.class);

    @Override
    public void execute(HttpServletRequest request) throws CommandExeption {
        HttpSession session = request.getSession();
        List<User> users = (List<User>) session.getAttribute("users");
        int index =  Integer.parseInt(request.getParameter("userId"));
        //int statusId = Integer.parseInt(request.getParameter("status"));
        int statusId = Integer.parseInt(request.getParameter("status"));
        LOGGER.log(Level.INFO,statusId);
        User user = users.get(index);

        try {
            ChangeStatusService.change(user,statusId);
        } catch (ServiceExeption e) {
            throw new CommandExeption("Service:", e);
        }


        //Sellect all users from DB
      //  CommandFactory.create("users").execute(request);


    }


}
