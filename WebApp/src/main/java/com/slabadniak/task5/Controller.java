package com.slabadniak.task5;

import com.slabadniak.task5.command.CommandFactory;
import com.slabadniak.task5.command.ICommand;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
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

        HttpSession session = request.getSession();
        //add atribute for user status
       /* if(session.isNew()){
            session.setAttribute("userStatus", UserType.GUEST);
        }*/

      /*  session.setAttribute("userStatus", UserType.ADMINISTRATOR);
        session.setAttribute("userName","slabadniaksergei");*/

        String command = request.getParameter("command");

        ICommand com = CommandFactory.create(command);
        com.execute(request);


        /*RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    } else {
        request.getSession().setAttribute("nullPage",
                page);
        response.sendRedirect(request.getContextPath() + page);*/


        request.getRequestDispatcher((String)session.getAttribute("currentJSP")).forward(request, response);
    }
}