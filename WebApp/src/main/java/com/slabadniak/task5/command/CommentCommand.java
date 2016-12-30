package com.slabadniak.task5.command;

import com.slabadniak.task5.dao.DefaultDAO;
import com.slabadniak.task5.dao.UserDAO;
import com.slabadniak.task5.entity.Movie;
import com.slabadniak.task5.entity.User;
import com.slabadniak.task5.entity.UsersAssessment;
import com.slabadniak.task5.pool.ConnectionPool;
import com.slabadniak.task5.pool.Wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CommentCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        //exeption
        String comment = request.getParameter("commentText");
        float rating = Float.parseFloat(request.getParameter("rating"));
        String login = (String) session.getAttribute("userName");
        Movie movie = (Movie) session.getAttribute("chosenMovie");


        ConnectionPool pool = ConnectionPool.getInstance();
        UserDAO userDAO = null;
        UsersAssessment assessment = null;

        try {
            Wrapper connection = pool.getConnection();
            userDAO = new UserDAO(connection);
            if(comment == null || comment.isEmpty()){
                assessment = new UsersAssessment(rating,movie.getTitle(),login);
            }else {
                assessment = new UsersAssessment(comment, rating, movie.getTitle(), login);
            }

            userDAO.assess(assessment);
            pool.closeConnection(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // HttpSession session = request.getSession(true);

        CommandFactory.create("cross").execute(request);
    }
}
