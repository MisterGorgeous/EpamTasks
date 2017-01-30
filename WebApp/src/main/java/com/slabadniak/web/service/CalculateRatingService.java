package com.slabadniak.web.service;

import com.slabadniak.web.dao.UserDAO;
import com.slabadniak.web.entity.Movie;
import com.slabadniak.web.entity.User;
import com.slabadniak.web.entity.UsersAssessment;
import com.slabadniak.web.exeption.DAOException;
import com.slabadniak.web.exeption.PoolException;
import com.slabadniak.web.exeption.ServiceExeption;
import com.slabadniak.web.exeption.WrapperException;
import com.slabadniak.web.pool.ConnectionPool;
import com.slabadniak.web.pool.Wrapper;

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
            //asses movie
            userDAO.assess(assessment);

            //get value of user's assessments
            int numAssess = userDAO.numAssess(movie.getTitle());


            if(numAssess >= MIN_USERS_ASSESSMENTS) {
                //get user's rate
                float usersRate = userDAO.usersRate(movie.getTitle());

                //change user's status
                String status = calculateStatus(mark, usersRate);
                userDAO.changeStatus(user, status);
                //user's synchronization
                user.setStatus(status);

            }

            pool.releaseConnection(connection);
            connection.closePreparedStatement();
        } catch (PoolException |WrapperException |DAOException  e) {
            throw new ServiceExeption("Service exception", e);
        }

    }


    private static  String calculateStatus(float mark, float usersRate){
        float step = Math.abs(mark - usersRate);
       return  (step < FIRST_APPROXIMATION) ? "expert" : (step < SECOND_APPROXIMATION) ? "fan" : "beginer";
    }


}
