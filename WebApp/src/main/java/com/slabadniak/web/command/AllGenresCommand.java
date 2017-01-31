package com.slabadniak.web.command;

import com.slabadniak.web.exeption.CommandExeption;
import com.slabadniak.web.exeption.ServiceExeption;
import com.slabadniak.web.service.AllGenresService;
import com.slabadniak.web.content.DataContext;
import com.slabadniak.web.content.GenreContent;

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
        HttpSession session = request.getSession();
        session.setAttribute("genrelist", (List<String>) content.get());
    }
}
