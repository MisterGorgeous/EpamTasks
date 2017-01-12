package com.slabadniak.task5.command;

import com.slabadniak.task5.entity.Movie;
import com.slabadniak.task5.exeption.CommandExeption;
import com.slabadniak.task5.exeption.ServiceExeption;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class MovieCommand implements ICommand {

    @Override
    public void execute(HttpServletRequest request) throws CommandExeption {
        HttpSession session = request.getSession();
        List<Movie> movies = (ArrayList<Movie>) session.getAttribute("movies");
        int chosenIndex = Integer.parseInt(request.getParameter("index"));

        session.setAttribute("chosenMovie",movies.get(chosenIndex));

        CommandFactory.create("genre").execute(request);
        CommandFactory.create("actor").execute(request);
        CommandFactory.create("showcomments").execute(request);

        setForwardPage(request);
    }
}
