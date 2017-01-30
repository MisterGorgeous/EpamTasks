package com.slabadniak.web.listner;

import com.slabadniak.web.constant.UserType;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionChangeListner implements HttpSessionListener {
    private static final String CURRENTJSP = "currentJSP";
    private static final String LOCAL = "local";
    private final static String ENGLISH = "en_US";
    private static final String USERSATUS = "userStatus";
    private static final String MOVIEORDER = "movieOrder";
    private static final String DEFAULT = "Alphabetic";

    public void sessionCreated(HttpSessionEvent sessionEvent) {
        HttpSession session = sessionEvent.getSession();
        session.setAttribute(USERSATUS, UserType.GUEST);
        session.setAttribute(LOCAL, ENGLISH);
        session.setAttribute(CURRENTJSP, "path.page.main");
        session.setAttribute(MOVIEORDER, DEFAULT);
    }

    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
    }
}