package com.slabadniak.web.service;

import com.slabadniak.web.dao.DefaultDAO;
import com.slabadniak.web.exeption.DAOException;
import com.slabadniak.web.exeption.PoolException;
import com.slabadniak.web.exeption.ServiceExeption;
import com.slabadniak.web.exeption.WrapperException;
import com.slabadniak.web.pool.ConnectionPool;
import com.slabadniak.web.pool.Wrapper;
import com.slabadniak.web.content.GenreContent;


public class AllGenresService {

    private AllGenresService(){}

    public static  GenreContent genres() throws ServiceExeption {
        ConnectionPool pool = ConnectionPool.getInstance();
        DefaultDAO defaultDAO = null;
        GenreContent content = new GenreContent();


        try {
            Wrapper connection = pool.getConnection();
            defaultDAO = new DefaultDAO(connection);
            content.insert(defaultDAO.allGenres());
            //add genres to request atr


            pool.releaseConnection(connection);
            connection.closePreparedStatement();
        } catch (PoolException |WrapperException |DAOException  e) {
            throw new ServiceExeption("Pool exception", e);
        }

        return content;
    }
}
