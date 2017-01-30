package com.slabadniak.web.service;

import com.slabadniak.web.dao.DefaultDAO;
import com.slabadniak.web.entity.User;
import com.slabadniak.web.exeption.DAOException;
import com.slabadniak.web.exeption.PoolException;
import com.slabadniak.web.exeption.ServiceExeption;
import com.slabadniak.web.exeption.WrapperException;
import com.slabadniak.web.pool.ConnectionPool;
import com.slabadniak.web.pool.Wrapper;

public class SignInService {

    public static  void signin(User user) throws ServiceExeption {
        ConnectionPool pool = ConnectionPool.getInstance();
        DefaultDAO defaultDAO = null;


        try {
            Wrapper connection = pool.getConnection();
            defaultDAO = new DefaultDAO(connection);

            defaultDAO.signIn(user);

            pool.releaseConnection(connection);
            connection.closePreparedStatement();
        } catch (PoolException |WrapperException |DAOException  e) {
            throw new ServiceExeption("Service exception", e);
        }
    }
}
