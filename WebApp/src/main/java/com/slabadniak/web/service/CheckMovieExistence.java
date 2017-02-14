package com.slabadniak.web.service;

import com.slabadniak.web.dao.AdminDAO;
import com.slabadniak.web.entity.Actor;
import com.slabadniak.web.exeption.DAOException;
import com.slabadniak.web.exeption.PoolException;
import com.slabadniak.web.exeption.ServiceExeption;
import com.slabadniak.web.exeption.WrapperException;
import com.slabadniak.web.pool.ConnectionPool;
import com.slabadniak.web.pool.Wrapper;

import java.util.List;

/**
 * Created by Siarhei on 14.02.2017.
 */
public class CheckMovieExistence {

    private CheckMovieExistence(){}

    public static  boolean check(String movie, String year) throws ServiceExeption {
        ConnectionPool pool = ConnectionPool.getInstance();
        AdminDAO adminDAO = null;
        int movieId;
        boolean found;

        try {
            Wrapper connection = pool.getConnection();
            adminDAO = new AdminDAO(connection);
            movieId = adminDAO.movieId(movie, year);
            found = movieId > 0;
            pool.releaseConnection(connection);
            connection.closePreparedStatement();
        } catch (PoolException |WrapperException |DAOException e) {
            throw new ServiceExeption("Pool exception", e);
        }
        return found;
    }

}
