package com.slabadniak.task5.command;

import com.slabadniak.task5.exeption.CommandExeption;
import com.slabadniak.task5.exeption.ServiceExeption;
import com.slabadniak.task5.service.AllGenresService;
import com.slabadniak.task5.content.DataContext;
import com.slabadniak.task5.content.GenreContent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AllGenresCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request) throws CommandExeption {

        GenreContent content;


        try {
            content =  AllGenresService.genres();
        } catch (ServiceExeption e) {
            throw new CommandExeption("Service:", e);
        }

        setAtributes(content, request);

        setForwardPage(request);

    }

    private void setAtributes(DataContext content, HttpServletRequest request) {
        //request.setAttribute("allgenres", (Set<String>) content.get());
        HttpSession session = request.getSession();
        session.setAttribute("genrelist", (List<String>) content.get());
    }
}
