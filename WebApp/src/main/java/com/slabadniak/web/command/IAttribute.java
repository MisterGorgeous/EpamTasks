package com.slabadniak.web.command;

import com.slabadniak.web.configuration.ConfigurationManager;
import com.slabadniak.web.content.MovieContent;
import com.slabadniak.web.entity.Movie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface IAttribute {

    default void setAtributes(MovieContent content, HttpServletRequest request) {
        // request.setAttribute("films",content.get());
        HttpSession session = request.getSession();
        List<Movie> movies = content.get();
        session.setAttribute("movies", movies);
        //number of pages
        int numPages = (int) Math.ceil(movies.size() / 6);
        session.setAttribute("numPages", numPages);
        session.setAttribute("movieSize", movies.size());
        //not const.Will be iterated
        session.setAttribute("currentMoviePage", 0);


    }

}
