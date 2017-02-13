package com.slabadniak.web.servlet;

import com.slabadniak.web.factory.CommandFactory;
import com.slabadniak.web.exeption.CommandExeption;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;
import java.util.Properties;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private static final String JSP = "currentJSP";

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
        String[] commands = request.getParameterValues("command");

        try {
            CommandFactory factory = new CommandFactory();
            factory.create(commands);
            factory.execute(request);
        } catch (CommandExeption e) {
            throw new ServletException("Command exception ", e);
        }

        request.getRequestDispatcher((String)session.getAttribute(JSP)).forward(request, response);
    }
}