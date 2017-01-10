package com.slabadniak.task5.command;

import com.slabadniak.task5.configuration.ConfigurationManager;
import com.slabadniak.task5.exeption.CommandExeption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface ICommand {
    Logger LOGGER = LogManager.getLogger(ICommand.class);
    String FEEDBACK = "feedback";

    void execute(HttpServletRequest request) throws CommandExeption;

    default void setForwardPage(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        String page = request.getParameter("page");
        session.setAttribute("currentJSP", ConfigurationManager.getProperty(page));
    }
}
