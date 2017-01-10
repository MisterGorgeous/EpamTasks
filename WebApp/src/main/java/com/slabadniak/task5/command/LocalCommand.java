package com.slabadniak.task5.command;

import com.slabadniak.task5.configuration.ConfigurationManager;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LocalCommand implements ICommand {
    private static final String LOCAL = "local";
    private final static String RUSSIAN = "ru_RU";
    private final static String ENGLISH = "en_US";
    private final static String RU = "RU";


    @Override
    public void execute(HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        String pressedButton = request.getParameter("button");

        if(pressedButton.equals(RU)) {
            session.setAttribute(LOCAL, RUSSIAN);
        } else {
            session.setAttribute(LOCAL, ENGLISH);
        }

        setForwardPage(request);
    }
}
