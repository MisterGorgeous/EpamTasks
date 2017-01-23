package com.slabadniak.task5;

import com.slabadniak.task5.command.CommandFactory;
import com.slabadniak.task5.command.ICommand;
import com.slabadniak.task5.entity.User;
import com.slabadniak.task5.entity.UserType;
import com.slabadniak.task5.exeption.CommandExeption;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@WebServlet("/Controller")
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

       session.setAttribute("userStatus", UserType.ADMINISTRATOR);
        session.setAttribute("user",new User("slabadniaksergei","dsdsdsd","ser","expert",false,"male","icon",true));


        String command = request.getParameter("command");

        ICommand com = CommandFactory.create(command);


        try {
            com.execute(request);
        } catch (CommandExeption e) {
            throw new ServletException("Command exception ", e);
        }


        /*RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    } else {
        request.getSession().setAttribute("nullPage",
                page);
        response.sendRedirect(request.getContextPath() + page);*/


        request.getRequestDispatcher((String)session.getAttribute("currentJSP")).forward(request, response);
    }
}