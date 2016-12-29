package com.slabadniak.task5.command;

import com.slabadniak.task5.configuration.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class EmptyCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute("currentJSP", ConfigurationManager.getProperty("path.page.main"));

        CommandFactory.create("setmaincontent").execute(request);
       // CommandFactory.create("cross").execute(request);
    }
}
