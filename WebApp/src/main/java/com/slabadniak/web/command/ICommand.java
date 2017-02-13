package com.slabadniak.web.command;

import com.slabadniak.web.configuration.ConfigurationManager;
import com.slabadniak.web.exeption.CommandExeption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface ICommand {
    String FEEDBACK = "feedback";

    /**
     *
     * @param request
     * @throws CommandExeption
     */
    void execute(HttpServletRequest request) throws CommandExeption;

    default void setForwardPage(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        String page = request.getParameter("page");
        session.setAttribute("currentJSP", ConfigurationManager.getProperty(page));
    }
}
