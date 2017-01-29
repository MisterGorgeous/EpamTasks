package com.slabadniak.web;

import com.slabadniak.web.factory.CommandFactory;
import com.slabadniak.web.command.ICommand;
import com.slabadniak.web.entity.User;
import com.slabadniak.web.constant.UserType;
import com.slabadniak.web.exeption.CommandExeption;

import javax.servlet.ServletException;
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


       /*session.setAttribute("userStatus", UserType.ADMINISTRATOR);
        session.setAttribute("user",new User("slabadniaksergei","dsdsdsd","ser","expert",false,"male","icon",true));
*/

        String command = request.getParameter("command");

        try {
            CommandFactory.create(command).execute(request);
        } catch (CommandExeption e) {
            throw new ServletException("Command exception ", e);
        }

        request.getRequestDispatcher((String)session.getAttribute("currentJSP")).forward(request, response);
    }
}