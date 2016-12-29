package com.slabadniak.task5.command;

import com.slabadniak.task5.configuration.ConfigurationManager;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LocalCommand implements ICommand {

    private final static String RUSSIAN = "ru_RU";
    private final static String ENGLISH = "en_US";

    @Override
    public void execute(HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        String locale = request.getParameter("button");

        if(locale.equals("RU")) {
            session.setAttribute("local", RUSSIAN);
            LOGGER.log(Level.DEBUG, "RUSSIAN");
        } else {
            session.setAttribute("local", ENGLISH);
            LOGGER.log(Level.DEBUG, "ENGLISH");
        }

        CommandFactory.create("cross").execute(request);
    }
}
