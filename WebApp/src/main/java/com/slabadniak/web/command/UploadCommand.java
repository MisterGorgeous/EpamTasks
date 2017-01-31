package com.slabadniak.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class UploadCommand implements ICommand {
    private static final String PREFIX = "/img/";
    private static final String ICON = "icon";

    @Override
    public void execute(HttpServletRequest request) {

        String fileName = null;

        fileName = (String) request.getAttribute("filename");

        //set path + file NAME
        fileName = PREFIX + fileName;

        HttpSession session = request.getSession(true);

        session.setAttribute(ICON, fileName);


    }

}

