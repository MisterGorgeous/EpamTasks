package com.slabadniak.web.service;

import com.slabadniak.web.dao.AdminDAO;
import com.slabadniak.web.entity.User;
import com.slabadniak.web.exeption.DAOException;
import com.slabadniak.web.exeption.PoolException;
import com.slabadniak.web.exeption.ServiceExeption;
import com.slabadniak.web.exeption.WrapperException;
import com.slabadniak.web.pool.ConnectionPool;
import com.slabadniak.web.pool.Wrapper;


public class UserBannedService {
    private UserBannedService() {
    }

    public static  void ban(User user ) throws ServiceExeption {

        //reverse value
        user.setBanned(!user.isBanned());

        ConnectionPool pool = ConnectionPool.getInstance();
        AdminDAO adminDAO = null;

        try {
            Wrapper connection = pool.getConnection();
            adminDAO = new AdminDAO(connection);
            adminDAO.userBaning(user);

            pool.releaseConnection(connection);
            connection.closePreparedStatement();
        } catch (PoolException |WrapperException |DAOException  e) {
            throw new ServiceExeption("Service exception", e);
        }

    }
}