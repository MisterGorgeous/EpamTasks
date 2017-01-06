package com.slabadniak.task5.command;

import com.slabadniak.task5.dao.AdminDAO;

import com.slabadniak.task5.pool.ConnectionPool;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class UploadCommand implements ICommand {
    public  static final String PREFIX = "/img/";
    public  static final String ICON = "icon";

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

