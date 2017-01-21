package com.slabadniak.task5.command;

import com.slabadniak.task5.dao.AdminDAO;
import com.slabadniak.task5.dao.UserDAO;
import com.slabadniak.task5.entity.Movie;
import com.slabadniak.task5.entity.UsersAssessment;
import com.slabadniak.task5.exeption.CommandExeption;
import com.slabadniak.task5.exeption.ServiceExeption;
import com.slabadniak.task5.pool.ConnectionPool;
import com.slabadniak.task5.pool.Wrapper;
import com.slabadniak.task5.service.AddMovieSevice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class AddMovieCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request) throws CommandExeption {

        HttpSession session = request.getSession();
        //validate
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String counrty = request.getParameter("country");
        String year = request.getParameter("movieYear");
        String icon = (String) session.getAttribute("icon");
        float rating = Float.parseFloat(request.getParameter("rating"));

        Movie movie = new Movie(title, rating, icon, year, counrty, description);
        List<String> movieGenres = genreIds(request);

        try {
            AddMovieSevice.add(movie, movieGenres);
        } catch (ServiceExeption e) {
            throw new CommandExeption("Service:", e);
        }


        // HttpSession session = request.getSession(true);

        setForwardPage(request);
    }

    private List<String> genreIds(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<String> genreList = (ArrayList<String>) session.getAttribute("genrelist");
        List<String> movieGenres = new ArrayList<>();

        for (String genre : genreList) {
            if (request.getParameter(genre) != null) {
                movieGenres.add(genre);
            }
        }
        return movieGenres;
    }
}