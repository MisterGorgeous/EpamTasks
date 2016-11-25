package com.slabadniak.task5;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ParseServlet extends HttpServlet {
    public void init() throws ServletException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
              processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
             processRequest(request, response);
    }

    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String domButton = request.getParameter("submitDOM");
        String saxButton = request.getParameter("submitSAX");
        String staxButton = request.getParameter("submitStAX");
        request.setAttribute("res", domButton + " " + saxButton + " " + staxButton);
        request.getRequestDispatcher("result.jsp").forward(request, response);
    }
}
