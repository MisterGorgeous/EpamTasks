package com.slabadniak.web.command;

import com.slabadniak.web.entity.Movie;
import com.slabadniak.web.exeption.CommandExeption;
import com.slabadniak.web.exeption.ServiceExeption;
import com.slabadniak.web.service.GenreService;
import com.slabadniak.web.content.DataContext;
import com.slabadniak.web.content.GenreContent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GenreCommand implements ICommand {

    @Override
    public void execute(HttpServletRequest request) throws CommandExeption {
        //exeption

        HttpSession session = request.getSession();
        Movie movie = (Movie) session.getAttribute("chosenMovie");
        GenreContent content;

        try {
            content =   GenreService.change(movie);
        } catch (ServiceExeption e) {
            throw new CommandExeption("Service:", e);
        }


        setAtributes(content,request);
    }

    private void setAtributes(DataContext content, HttpServletRequest request){
        HttpSession session = request.getSession();
         session.setAttribute("genres", (List<String>) content.get());
    }
}
