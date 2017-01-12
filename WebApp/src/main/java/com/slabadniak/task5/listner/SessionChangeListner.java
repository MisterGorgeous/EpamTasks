package com.slabadniak.task5.listner;

import com.slabadniak.task5.entity.UserType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionChangeListner implements HttpSessionListener {
    private static final Logger LOGGER = LogManager.getLogger(SessionChangeListner.class);
    private static final String CURRENTJSP = "currentJSP";
    private static final String LOCAL = "local";
    private final static String ENGLISH = "en_US";
    private static final String USERSATUS = "userStatus";

    public void sessionCreated(HttpSessionEvent sessionEvent) {
        HttpSession session = sessionEvent.getSession();
        session.setAttribute(USERSATUS, UserType.GUEST);
        session.setAttribute(LOCAL, ENGLISH);
        session.setAttribute(CURRENTJSP, "path.page.main");
        LOGGER.log(Level.INFO, "Session created "+sessionEvent.getSession().getId());
    }

    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        LOGGER.log(Level.INFO,"Session Destroyed "+sessionEvent.getSession().getId());
    }
}