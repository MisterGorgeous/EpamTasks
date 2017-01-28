package com.slabadniak.task5.command;

import com.slabadniak.task5.entity.Movie;
import com.slabadniak.task5.entity.User;
import com.slabadniak.task5.exeption.CommandExeption;
import com.slabadniak.task5.exeption.ServiceExeption;
import com.slabadniak.task5.service.CalculateRatingService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class CommentCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request) throws CommandExeption {

        HttpSession session = request.getSession();
        //exeption
        String comment = request.getParameter("commentText");
        float rating = Float.parseFloat(request.getParameter("rating"));
        User user = (User) session.getAttribute("user");
        Movie movie = (Movie) session.getAttribute("chosenMovie");

        try {
            CalculateRatingService.calculate(comment,rating,user,movie);
        } catch (ServiceExeption e) {
            throw new CommandExeption("Service:", e);
        }

        // HttpSession session = request.getSession(true);

        //CommandFactory.create("cross").execute(request);


        CommandFactory.create("showcomments").execute(request);
        setForwardPage(request);
    }
}
