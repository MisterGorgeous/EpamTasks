package com.slabadniak.task5.command;

import com.slabadniak.task5.dao.DefaultDAO;
import com.slabadniak.task5.dao.UserDAO;
import com.slabadniak.task5.entity.Movie;
import com.slabadniak.task5.entity.User;
import com.slabadniak.task5.entity.UsersAssessment;
import com.slabadniak.task5.exeption.CommandExeption;
import com.slabadniak.task5.exeption.ServiceExeption;
import com.slabadniak.task5.pool.ConnectionPool;
import com.slabadniak.task5.pool.Wrapper;
import com.slabadniak.task5.service.CalculateRatingService;
import com.slabadniak.task5.service.ShowCommentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CommentCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request) throws CommandExeption {

        HttpSession session = request.getSession();
        //exeption
        String comment = request.getParameter("commentText");
        float rating = Float.parseFloat(request.getParameter("rating"));
        User user = (User) session.getAttribute("user");
        Movie movie = (Movie) session.getAttribute("chosenMovie");


        CalculateRatingService service = new CalculateRatingService();


        try {
            service.calculate(comment,rating,user,movie);
        } catch (ServiceExeption e) {
            throw new CommandExeption("Service:", e);
        }

        // HttpSession session = request.getSession(true);

        //CommandFactory.create("cross").execute(request);


        CommandFactory.create("showcomments").execute(request);
        setForwardPage(request);
    }
}
