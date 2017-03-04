package com.slabadniak.web.command;

import com.slabadniak.web.configuration.ConfigurationManager;
import com.slabadniak.web.exeption.CommandExeption;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Implementation of GOF Command pattern.Convert user's request into the object
 * @author Slabadniak Sergei
 * @version 1.0
 */
public interface ICommand {
    public static final String FEEDBACK = "feedback";
    public static final String JSP = "currentJSP";
    public static final String PAGE = "page";
    public static final String LOCAL = "local";

    /**
     *
     * @param request
     * @throws CommandExeption
     */
    void execute(HttpServletRequest request) throws CommandExeption;

    /**
     * Save current page in the session
     * @param request
     */
    default void setForwardPage(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        String page = request.getParameter(PAGE);
        session.setAttribute(JSP, ConfigurationManager.getProperty(page));
    }
}
