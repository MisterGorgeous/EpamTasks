package com.slabadniak.task5;

import com.slabadniak.task5.command.CommandFactory;
import com.slabadniak.task5.entity.ClientType;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Iterator;
import java.util.List;

@WebServlet("/Controller")
@MultipartConfig
public class Controller extends HttpServlet {

    public void init() throws ServletException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void destroy() {
        super.destroy();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        //add atribute for user status
        if(session == null){
            session.setAttribute("userStatus", ClientType.GUEST);
        }

        session.setAttribute("userStatus", ClientType.ADMINISTRATOR);
        session.setAttribute("userName","slabadniaksergei");

        String command = request.getParameter("command");

        CommandFactory.create(command).execute(request);



        /*RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    } else {
        request.getSession().setAttribute("nullPage",
                page);
        response.sendRedirect(request.getContextPath() + page);*/


        request.getRequestDispatcher((String)session.getAttribute("currentJSP")).forward(request, response);
    }
}