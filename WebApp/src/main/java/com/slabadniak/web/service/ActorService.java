package com.slabadniak.web.service;

import com.slabadniak.web.dao.DefaultDAO;
import com.slabadniak.web.entity.Movie;
import com.slabadniak.web.exeption.DAOException;
import com.slabadniak.web.exeption.PoolException;
import com.slabadniak.web.exeption.ServiceExeption;
import com.slabadniak.web.exeption.WrapperException;
import com.slabadniak.web.pool.ConnectionPool;
import com.slabadniak.web.pool.Wrapper;
import com.slabadniak.web.content.ActorContent;


public class ActorService {

    public static ActorContent actors(Movie movie) throws ServiceExeption {
        ConnectionPool pool = ConnectionPool.getInstance();
        DefaultDAO defaultDAO = null;
        ActorContent content = new ActorContent();

        try {
            Wrapper connection = pool.getConnection();
            defaultDAO = new DefaultDAO(connection);
            content.insert(defaultDAO.actors(movie.getTitle()));
            pool.releaseConnection(connection);
            connection.closePreparedStatement();
        } catch (PoolException |WrapperException |DAOException  e) {
            throw new ServiceExeption("Pool exception", e);
        }

        return content;

    }
}
