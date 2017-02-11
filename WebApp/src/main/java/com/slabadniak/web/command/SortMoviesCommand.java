package com.slabadniak.web.command;


import com.slabadniak.web.entity.Movie;
import com.slabadniak.web.exeption.CommandExeption;
import com.slabadniak.web.exeption.ServiceExeption;
import com.slabadniak.web.service.SortMovies;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SortMoviesCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request) throws CommandExeption {
        String order = request.getParameter("order");

        HttpSession session = request.getSession();
        List<Movie> movies = (List<Movie>) session.getAttribute("movies");
        String attribute = (String) session.getAttribute("movieOrder");
        String newAttribute;

        try {
            newAttribute =  SortMovies.sort(movies,order,attribute);
        } catch (ServiceExeption e) {
            throw new CommandExeption("Service:", e);
        }
        if(!newAttribute.equals("")){
            session.setAttribute("movieOrder",newAttribute);
        }

        session.setAttribute("movies",movies);
        setForwardPage(request);
    }
}
