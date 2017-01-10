package com.slabadniak.task5.command;


import javax.servlet.http.HttpServletRequest;


public class EmptyCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request) {
        String page = request.getParameter("page");
        if(page != null){
            setForwardPage(request);
        }
    }
}
