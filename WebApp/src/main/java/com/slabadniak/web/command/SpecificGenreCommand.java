package com.slabadniak.web.command;

import com.slabadniak.web.entity.Movie;
import com.slabadniak.web.exeption.CommandExeption;
import com.slabadniak.web.exeption.ServiceExeption;
import com.slabadniak.web.service.SpecificGenreService;
import com.slabadniak.web.content.MovieContent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SpecificGenreCommand implements ICommand,IAttribute {
    @Override
    public void execute(HttpServletRequest request) throws CommandExeption {

        String genre = request.getParameter("button");

        MovieContent content;


        try {
            content =  SpecificGenreService.movies(genre);
        } catch (ServiceExeption e) {
            throw new CommandExeption("Service:", e);
        }

        setAtributes(content, request);

        setForwardPage(request);
    }

}
