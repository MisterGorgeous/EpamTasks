package com.slabadniak.web.service;

import com.slabadniak.web.dao.AdminDAO;
import com.slabadniak.web.entity.Actor;
import com.slabadniak.web.exeption.DAOException;
import com.slabadniak.web.exeption.PoolException;
import com.slabadniak.web.exeption.ServiceExeption;
import com.slabadniak.web.pool.ConnectionPool;
import com.slabadniak.web.pool.Wrapper;

import java.util.List;

public class AddActorService {


    public static  boolean add(List<Actor> actors, String movie, String year) throws ServiceExeption {
        ConnectionPool pool = ConnectionPool.getInstance();
        AdminDAO adminDAO = null;
        int movieId;
        boolean done;

        try {
            Wrapper connection = pool.getConnection();
            adminDAO = new AdminDAO(connection);
            movieId = adminDAO.movieId(movie, year);

            if (movieId > 0) {
                adminDAO.addActors(actors, movieId);
                done = true;
            } else {
                done = false;
            }
            pool.releaseConnection(connection);
        } catch (PoolException e) {
            throw new ServiceExeption("Pool exception", e);
        } catch (DAOException e) {
            throw new ServiceExeption("UserDAO exception ", e);
        }
        return done;
    }


}

