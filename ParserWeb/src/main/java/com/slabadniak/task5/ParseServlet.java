package com.slabadniak.task5;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet("/ParseServlet")
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
        Builder builder = new Builder();
       // ArrayList<Journey> result = null;
        String pressedButton = request.getParameter("button");

      /*  if (pressedButton.equals("DOM")) {
            result = builder.create(new DOMParser());
        } else if (pressedButton.equals("SAX")) {
            result = builder.create(new SAXParser());
        } else if (pressedButton.equals("StAX")) {
            result = builder.create(new StAXParser());
        } else {
            result = new ArrayList<>();
        }*/
        //result = builder.create(new DOMParser());
        ArrayList<Integer> result = new ArrayList<Integer>(){
            {
                add(1);
                add(2);
                add(3);
            }
        };
        request.setAttribute("results", result);
        request.getRequestDispatcher("/jsp/finish.jsp").forward(request, response);
    }
}
