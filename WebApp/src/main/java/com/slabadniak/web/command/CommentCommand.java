package com.slabadniak.web.command;

import com.slabadniak.web.entity.Movie;
import com.slabadniak.web.entity.User;
import com.slabadniak.web.exeption.CommandExeption;
import com.slabadniak.web.exeption.ServiceExeption;
import com.slabadniak.web.factory.CommandFactory;
import com.slabadniak.web.service.CalculateRatingService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class CommentCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request) throws CommandExeption {

        HttpSession session = request.getSession();
        String comment = request.getParameter("commentText");
        float rating = Float.parseFloat(request.getParameter("rating"));
        User user = (User) session.getAttribute("user");
        Movie movie = (Movie) session.getAttribute("chosenMovie");

        try {
            CalculateRatingService.calculate(comment,rating,user,movie);
        } catch (ServiceExeption e) {
            throw new CommandExeption("Service:", e);
        }

        setForwardPage(request);
    }
}
