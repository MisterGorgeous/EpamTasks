package com.slabadniak.task5.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LocalCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String locale = request.getParameter("button");

        session.setAttribute("locale", locale);
    }
}
