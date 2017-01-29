package com.slabadniak.web.command;


import com.slabadniak.web.entity.Movie;
import com.slabadniak.web.entity.UsersAssessment;
import com.slabadniak.web.exeption.CommandExeption;
import com.slabadniak.web.exeption.ServiceExeption;
import com.slabadniak.web.service.ShowCommentService;
import com.slabadniak.web.content.AssessmentContent;
import com.slabadniak.web.content.DataContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowCommentsCommand implements ICommand {

    @Override
    public void execute(HttpServletRequest request) throws CommandExeption {

        HttpSession session = request.getSession();
        Movie movie = (Movie) session.getAttribute("chosenMovie");

        AssessmentContent content;

        try {
            content =   ShowCommentService.show(movie);
        } catch (ServiceExeption e) {
            throw new CommandExeption("Service:", e);
        }

        setAtributes(content, request);
    }

    private void setAtributes(DataContext content, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("assessments", (List<UsersAssessment>) content.get());
    }
}