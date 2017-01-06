package com.slabadniak.task5.command;

import com.slabadniak.task5.entity.UserType;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogOffCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        session.setAttribute("userStatus", UserType.GUEST);
        session.removeAttribute("user");

        LOGGER.log(Level.INFO, "Logged off");
        System.out.println("Logged off");

        setForwardPage(request);
    }
}
