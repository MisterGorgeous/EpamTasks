package com.slabadniak.task5.service;

import com.slabadniak.task5.dao.UserDAO;
import com.slabadniak.task5.entity.Feedback;
import com.slabadniak.task5.entity.Movie;
import com.slabadniak.task5.entity.User;
import com.slabadniak.task5.entity.UsersAssessment;
import com.slabadniak.task5.exeption.DAOException;
import com.slabadniak.task5.exeption.PoolException;
import com.slabadniak.task5.exeption.ServiceExeption;
import com.slabadniak.task5.pool.ConnectionPool;
import com.slabadniak.task5.pool.Wrapper;
import com.sun.org.apache.regexp.internal.RE;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CalculateRatingService {
    private static final int MIN_USERS_ASSESSMENTS = 5;
    private static final int FIRST_APPROXIMATION  = 1;
    private static final int SECOND_APPROXIMATION  = 2;

    public static  void calculate(String comment, float mark, User user, Movie movie ) throws ServiceExeption {
        ConnectionPool pool = ConnectionPool.getInstance();
        UserDAO userDAO = null;
        UsersAssessment assessment = null;


        try {
            Wrapper connection = pool.getConnection();
            userDAO = new UserDAO(connection);

            // make assessment
            if(comment == null || comment.isEmpty()){
                assessment = new UsersAssessment(mark, movie.getTitle(),user.getLogin());
            }else  {
                assessment = new UsersAssessment(comment, mark,movie.getTitle(),user.getLogin());
            }
            userDAO.assess(assessment);

            //get value of user's assessments
            int numAssess = userDAO.numAssess(movie.getTitle());


            if(numAssess > MIN_USERS_ASSESSMENTS) {
                //get user's rate
                float usersRate = userDAO.usersRate(movie.getTitle());

                //change user's status
                String status = calculateStatus(mark, usersRate);
                userDAO.changeStatus(user, status);
            }

            pool.closeConnection(connection);
        } catch (PoolException e) {
            throw new ServiceExeption("Pool exception", e);
        } catch (DAOException e) {
            throw new ServiceExeption("UserDAO exception ", e);
        }

    }


    private static  String calculateStatus(float mark, float usersRate){
        float step = Math.abs(mark - usersRate);
       return  (step < FIRST_APPROXIMATION) ? "expert" : (step < SECOND_APPROXIMATION) ? "fan" : "beginer";
    }


}
