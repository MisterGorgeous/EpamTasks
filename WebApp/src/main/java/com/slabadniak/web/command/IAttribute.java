package com.slabadniak.web.command;

import com.slabadniak.web.content.MovieContent;
import com.slabadniak.web.entity.Movie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Simply to provide DRY principle
 */
public interface IAttribute {
     int NUMMOVIES = 6;
    /**
     * Set movie page attributes
     * @param content
     * @param request
     */
    default void setAtributes(MovieContent content, HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<Movie> movies = content.get();
        session.setAttribute("movies", movies);
        int numPages = (int) Math.ceil(movies.size() / NUMMOVIES);//number movies on the page
        session.setAttribute("numPages", numPages);
        session.setAttribute("movieSize", movies.size());
        session.setAttribute("currentMoviePage", 0);  //not const.Will be iterated
    }

}
