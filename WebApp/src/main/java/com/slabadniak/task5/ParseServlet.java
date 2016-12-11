package com.slabadniak.task5;

import com.slabadniak.task5.command.CommandFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/ParseServlet")
@MultipartConfig
public class ParseServlet extends HttpServlet {
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
        String pressedButton = request.getParameter("button");

        CommandFactory.create(pressedButton).execute(request);

        HttpSession session = request.getSession(true);

        request.getRequestDispatcher((String)session.getAttribute("currentJSP")).forward(request, response);
    }
}
