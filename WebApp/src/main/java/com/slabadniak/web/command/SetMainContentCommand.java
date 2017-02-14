package com.slabadniak.web.command;

import com.slabadniak.web.entity.Movie;
import com.slabadniak.web.exeption.CommandExeption;
import com.slabadniak.web.exeption.ServiceExeption;
import com.slabadniak.web.factory.CommandFactory;
import com.slabadniak.web.service.MainContentService;
import com.slabadniak.web.content.MovieContent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SetMainContentCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request) throws CommandExeption {
        MovieContent content ;

        try {
            content = MainContentService.movies();
        } catch (ServiceExeption e) {
            throw new CommandExeption("Service:", e);
        }

        setAtributes(content,request);
        setForwardPage(request);
    }

    private void setAtributes(MovieContent content, HttpServletRequest request){
        HttpSession session = request.getSession();
        List<Movie> movies = content.get();
        session.setAttribute("movies",movies);
        //number of pages
        int numPages = (int) Math.ceil(movies.size()/6);
        session.setAttribute("moviesOnPage",6);
        session.setAttribute("numPages",numPages);
        session.setAttribute("movieSize",movies.size());
        //not const.Will be iterated
        session.setAttribute("currentMoviePage",0);
    }
}
