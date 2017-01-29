package com.slabadniak.web.command;

import com.slabadniak.web.entity.Movie;
import com.slabadniak.web.exeption.CommandExeption;
import com.slabadniak.web.exeption.ServiceExeption;
import com.slabadniak.web.service.ActorService;
import com.slabadniak.web.content.ActorContent;
import com.slabadniak.web.content.DataContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ActorCommand implements ICommand {

    @Override
    public void execute(HttpServletRequest request) throws CommandExeption {

        HttpSession session = request.getSession();
        Movie movie = (Movie) session.getAttribute("chosenMovie");
        ActorContent content;

        try {
            content =  ActorService.actors(movie);
        } catch (ServiceExeption e) {
          throw new CommandExeption("Service:",e);
        }

        setAtributes(content, request);
    }


    private void setAtributes(DataContext content, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("actors", content.get());
    }
}
