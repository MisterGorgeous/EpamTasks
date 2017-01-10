package com.slabadniak.task5.command;

import com.slabadniak.task5.entity.Movie;
import com.slabadniak.task5.exeption.CommandExeption;
import com.slabadniak.task5.exeption.ServiceExeption;
import com.slabadniak.task5.service.GenreService;
import com.slabadniak.task5.content.DataContext;
import com.slabadniak.task5.content.GenreContent;

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

        GenreService service = new GenreService();

        try {
            content = service.change(movie);
        } catch (ServiceExeption e) {
            throw new CommandExeption("Service:", e);
        }


        setAtributes(content,request);
    }

    private void setAtributes(DataContext content, HttpServletRequest request){
         request.setAttribute("genres", (List<String>) content.get());
    }
}
