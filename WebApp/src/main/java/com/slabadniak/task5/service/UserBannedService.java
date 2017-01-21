package com.slabadniak.task5.service;

import com.slabadniak.task5.dao.AdminDAO;
import com.slabadniak.task5.entity.User;
import com.slabadniak.task5.exeption.DAOException;
import com.slabadniak.task5.exeption.PoolException;
import com.slabadniak.task5.exeption.ServiceExeption;
import com.slabadniak.task5.pool.ConnectionPool;
import com.slabadniak.task5.pool.Wrapper;


public class UserBannedService {

    public static  void ban(User user ) throws ServiceExeption {

        //reverse value
        user.setBanned(!user.isBanned());

        ConnectionPool pool = ConnectionPool.getInstance();
        AdminDAO adminDAO = null;

        try {
            Wrapper connection = pool.getConnection();
            adminDAO = new AdminDAO(connection);
            adminDAO.userBaning(user);
            pool.closeConnection(connection);
        } catch (PoolException e) {
            throw new ServiceExeption("Pool exception", e);
        } catch (DAOException e) {
            throw new ServiceExeption("UserDAO exception ", e);
        }

    }
}