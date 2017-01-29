package com.slabadniak.web.service;

import com.slabadniak.web.dao.DefaultDAO;
import com.slabadniak.web.entity.User;
import com.slabadniak.web.exeption.DAOException;
import com.slabadniak.web.exeption.PoolException;
import com.slabadniak.web.exeption.ServiceExeption;
import com.slabadniak.web.pool.ConnectionPool;
import com.slabadniak.web.pool.Wrapper;


public class CheckUserService {

    public static  boolean isLoginExist(User user) throws ServiceExeption {
        ConnectionPool pool = ConnectionPool.getInstance();
        DefaultDAO defaultDAO = null;
        boolean loginExist;

        try {
            Wrapper connection = pool.getConnection();
            defaultDAO = new DefaultDAO(connection);

            loginExist = defaultDAO.checkUsersLogin(user);

        } catch (PoolException e) {
            throw new ServiceExeption("Pool exception", e);
        } catch (DAOException e) {
            throw new ServiceExeption("UserDAO exception ", e);
        }
        return loginExist;
    }

    public static  boolean isEmailExist(User user) throws ServiceExeption {
        ConnectionPool pool = ConnectionPool.getInstance();
        DefaultDAO defaultDAO = null;
        boolean emailExist;

        try {
            Wrapper connection = pool.getConnection();
            defaultDAO = new DefaultDAO(connection);

            emailExist = defaultDAO.checkUsersEmail(user);

        } catch (PoolException e) {
            throw new ServiceExeption("Pool exception", e);
        } catch (DAOException e) {
            throw new ServiceExeption("UserDAO exception ", e);
        }
        return emailExist;

    }

    public static  boolean checkPassword(User user) throws ServiceExeption {
        ConnectionPool pool = ConnectionPool.getInstance();
        DefaultDAO defaultDAO = null;
        boolean passwordCorrect;

        try {
            Wrapper connection = pool.getConnection();
            defaultDAO = new DefaultDAO(connection);

            passwordCorrect = defaultDAO.checkUserPassword(user);

        } catch (PoolException e) {
            throw new ServiceExeption("Pool exception", e);
        } catch (DAOException e) {
            throw new ServiceExeption("UserDAO exception ", e);
        }
        return passwordCorrect;

    }
}
