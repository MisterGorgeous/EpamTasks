package com.slabadniak.task5.command;

import com.slabadniak.task5.entity.Movie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class MovieCimmand implements ICommand {
    @Override
    public void execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<Movie> movies = (ArrayList<Movie>) session.getAttribute("films");
        int chosenIndex = Integer.parseInt(request.getParameter("index"));
        session.setAttribute("chosenMovie",movies.get(chosenIndex));

        CommandFactory.create("genre").execute(request);
        CommandFactory.create("actor").execute(request);
        CommandFactory.create("showcomments").execute(request);

        CommandFactory.create("cross").execute(request);
    }
}
