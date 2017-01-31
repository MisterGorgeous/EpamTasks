package com.slabadniak.web.service;

import com.slabadniak.web.dao.DefaultDAO;
import com.slabadniak.web.entity.User;
import com.slabadniak.web.exeption.DAOException;
import com.slabadniak.web.exeption.PoolException;
import com.slabadniak.web.exeption.ServiceExeption;
import com.slabadniak.web.exeption.WrapperException;
import com.slabadniak.web.pool.ConnectionPool;
import com.slabadniak.web.pool.Wrapper;
import com.slabadniak.web.content.UserContent;


public class AuthorizationService {

    private AuthorizationService(){}

    public static UserContent authorization(User user) throws ServiceExeption {
        ConnectionPool pool = ConnectionPool.getInstance();
        DefaultDAO defaultDAO;
        //boolean logIn = false;
        UserContent content = new UserContent();

        try {
            Wrapper connection = pool.getConnection();
            defaultDAO = new DefaultDAO(connection);
            content.insert(defaultDAO.LogIn(user));
            pool.releaseConnection(connection);
            connection.closePreparedStatement();
        } catch (PoolException |WrapperException |DAOException  e) {
            throw new ServiceExeption("Service exception", e);
        }
        return content;
    }
}
