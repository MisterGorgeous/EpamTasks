package com.slabadniak.task5.command;


import com.slabadniak.task5.entity.Movie;
import com.slabadniak.task5.entity.UsersAssessment;
import com.slabadniak.task5.exeption.CommandExeption;
import com.slabadniak.task5.exeption.ServiceExeption;
import com.slabadniak.task5.service.ShowCommentService;
import com.slabadniak.task5.content.AssessmentContent;
import com.slabadniak.task5.content.DataContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowCommentsCommand implements ICommand {

    @Override
    public void execute(HttpServletRequest request) throws CommandExeption {

        HttpSession session = request.getSession();
        Movie movie = (Movie) session.getAttribute("chosenMovie");

        AssessmentContent content;

        ShowCommentService service = new ShowCommentService();

        try {
            content =  service.show(movie);
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