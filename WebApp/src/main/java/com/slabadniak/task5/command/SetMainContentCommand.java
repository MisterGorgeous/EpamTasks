package com.slabadniak.task5.command;

import com.slabadniak.task5.entity.Movie;
import com.slabadniak.task5.exeption.CommandExeption;
import com.slabadniak.task5.exeption.ServiceExeption;
import com.slabadniak.task5.service.MainContentService;
import com.slabadniak.task5.content.MovieContent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SetMainContentCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request) throws CommandExeption {
        MovieContent content ;

        MainContentService service = new MainContentService();

        try {
            content = service.movies();
        } catch (ServiceExeption e) {
            throw new CommandExeption("Service:", e);
        }

        setAtributes(content,request);

        CommandFactory.create("allgenres").execute(request);

        setForwardPage(request);
    }

    private void setAtributes(MovieContent content, HttpServletRequest request){
      // request.setAttribute("films",content.get());
        HttpSession session = request.getSession();
        List<Movie> movies = content.get();
        session.setAttribute("movies",movies);
        //number of pages
        int numPages = (int) Math.ceil(movies.size()/6);
        session.setAttribute("numPages",numPages);
        session.setAttribute("movieSize",movies.size());
        //not const.Will be iterated
        session.setAttribute("currentMoviePage",0);
    }
}
