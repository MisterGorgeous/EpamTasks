package com.slabadniak.web.service;

import com.slabadniak.web.dao.AdminDAO;
import com.slabadniak.web.entity.Movie;
import com.slabadniak.web.exeption.DAOException;
import com.slabadniak.web.exeption.PoolException;
import com.slabadniak.web.exeption.ServiceExeption;
import com.slabadniak.web.exeption.WrapperException;
import com.slabadniak.web.pool.ConnectionPool;
import com.slabadniak.web.pool.Wrapper;

import java.util.List;

public class AddMovieSevice {

    private AddMovieSevice(){}

    public static  boolean add(Movie movie, List<String> movieGenres) throws ServiceExeption {
        ConnectionPool pool = ConnectionPool.getInstance();
        AdminDAO adminDAO = null;
        boolean found;

        try {
            Wrapper connection = pool.getConnection();
            adminDAO = new AdminDAO(connection);
            found = CheckMovieExistence.check(movie.getTitle(),movie.getYear());

            if(!found) {
                adminDAO.addMovie(movie, movieGenres);
            }

            pool.releaseConnection(connection);
            connection.closePreparedStatement();
        } catch (PoolException |WrapperException |DAOException  e) {
            throw new ServiceExeption("Pool exception", e);
        }
        return found;
    }

}
