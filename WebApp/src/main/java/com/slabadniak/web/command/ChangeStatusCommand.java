package com.slabadniak.web.command;

import com.slabadniak.web.entity.User;
import com.slabadniak.web.exeption.CommandExeption;
import com.slabadniak.web.exeption.ServiceExeption;
import com.slabadniak.web.service.ChangeStatusService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ChangeStatusCommand implements ICommand {

    @Override
    public void execute(HttpServletRequest request) throws CommandExeption {
        HttpSession session = request.getSession();
        List<User> users = (List<User>) session.getAttribute("users");
        int index =  Integer.parseInt(request.getParameter("userId"));
        int statusId = Integer.parseInt(request.getParameter("status"));
        User user = users.get(index);

        try {
            ChangeStatusService.change(user,statusId);
        } catch (ServiceExeption e) {
            throw new CommandExeption("Service:", e);
        }

    }

}
