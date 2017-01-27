package com.slabadniak.task5.service;

import com.slabadniak.task5.dao.AdminDAO;
import com.slabadniak.task5.entity.Actor;
import com.slabadniak.task5.exeption.DAOException;
import com.slabadniak.task5.exeption.PoolException;
import com.slabadniak.task5.exeption.ServiceExeption;
import com.slabadniak.task5.pool.ConnectionPool;
import com.slabadniak.task5.pool.Wrapper;

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

