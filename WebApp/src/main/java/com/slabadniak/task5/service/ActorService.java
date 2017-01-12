package com.slabadniak.task5.service;

import com.slabadniak.task5.dao.DefaultDAO;
import com.slabadniak.task5.entity.Movie;
import com.slabadniak.task5.exeption.DAOException;
import com.slabadniak.task5.exeption.PoolException;
import com.slabadniak.task5.exeption.ServiceExeption;
import com.slabadniak.task5.pool.ConnectionPool;
import com.slabadniak.task5.pool.Wrapper;
import com.slabadniak.task5.content.ActorContent;


public class ActorService {

    public ActorContent actors(Movie movie) throws ServiceExeption {
        ConnectionPool pool = ConnectionPool.getInstance();
        DefaultDAO defaultDAO = null;
        ActorContent content = new ActorContent();

        try {
            Wrapper connection = pool.getConnection();
            defaultDAO = new DefaultDAO(connection);
            content.insert(defaultDAO.actors(movie.getTitle()));
            pool.closeConnection(connection);
        } catch (PoolException e) {
            throw new ServiceExeption("Pool exception", e);
        } catch (DAOException e) {
            throw new ServiceExeption("UserDAO exception ", e);
        }

        return content;

    }
}
