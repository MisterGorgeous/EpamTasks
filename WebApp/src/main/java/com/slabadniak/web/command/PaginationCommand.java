package com.slabadniak.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class PaginationCommand implements ICommand {
    public static final String PREVIOUS = "previous";

    @Override
    public void execute(HttpServletRequest request) {
        HttpSession session  = request.getSession();

        String attribute = request.getParameter("attribute");
        String action = request.getParameter("action");

        int atrValue = (int) session.getAttribute(attribute);

        if(action.equals(PREVIOUS)){
            --atrValue;  //previous page
        } else {
            ++atrValue;  //next page
        }

        session.setAttribute(attribute,atrValue);

        setForwardPage(request);
    }
}
