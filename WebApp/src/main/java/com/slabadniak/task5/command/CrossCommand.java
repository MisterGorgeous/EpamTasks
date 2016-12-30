package com.slabadniak.task5.command;

import com.slabadniak.task5.configuration.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CrossCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String page = request.getParameter("page");

        if(page.equals("path.page.main")){
            CommandFactory.create("setmaincontent").execute(request);
        }

        session.setAttribute("currentJSP", ConfigurationManager.getProperty(page));
    }
}
