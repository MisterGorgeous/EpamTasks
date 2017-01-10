package com.slabadniak.task5.command;

import com.slabadniak.task5.entity.Actor;
import com.slabadniak.task5.entity.Movie;
import com.slabadniak.task5.exeption.CommandExeption;
import com.slabadniak.task5.exeption.ServiceExeption;
import com.slabadniak.task5.service.ActorService;
import com.slabadniak.task5.content.ActorContent;
import com.slabadniak.task5.content.DataContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ActorCommand implements ICommand {

    @Override
    public void execute(HttpServletRequest request) throws CommandExeption {

        HttpSession session = request.getSession();
        Movie movie = (Movie) session.getAttribute("chosenMovie");
        ActorService service = new ActorService();
        ActorContent content;

        try {
            content = service.actors(movie);
        } catch (ServiceExeption e) {
          throw new CommandExeption("Service:",e);
        }

        setAtributes(content, request);
    }


    private void setAtributes(DataContext content, HttpServletRequest request) {
        request.setAttribute("actors", (List<Actor>) content.get());
    }
}
