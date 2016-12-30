package com.slabadniak.task5.command;

import com.slabadniak.task5.entity.ClientType;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class LogOffCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        session.setAttribute("userStatus", ClientType.GUEST);
        session.removeAttribute("userName");

        LOGGER.log(Level.DEBUG, "Logged off");

        CommandFactory.create("cross").execute(request);
    }
}
