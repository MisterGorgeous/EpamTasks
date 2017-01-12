package com.slabadniak.task5.service;

import com.slabadniak.task5.dao.UserDAO;
import com.slabadniak.task5.entity.User;
import com.slabadniak.task5.exeption.DAOException;
import com.slabadniak.task5.exeption.PoolException;
import com.slabadniak.task5.exeption.ServiceExeption;
import com.slabadniak.task5.pool.ConnectionPool;
import com.slabadniak.task5.pool.Wrapper;

/**
 * Created by Siarhei on 10.01.2017.
 */
public class ChangeProfileService {

    public void change(User unmodified, User modified) throws ServiceExeption {

        if (!iconEqual(unmodified, modified)) {
            changeIcon(unmodified, modified);
        }


        ConnectionPool pool = ConnectionPool.getInstance();
        UserDAO userDAO = null;

        try {
            Wrapper connection = pool.getConnection();
            userDAO = new UserDAO(connection);

            if (!unmodified.equals(modified)) {
                userDAO.changeProfile(unmodified, modified);
            }

            pool.closeConnection(connection);
        } catch (PoolException e) {
            throw new ServiceExeption("Pool exception", e);
        } catch (DAOException e) {
            throw new ServiceExeption("UserDAO exception ", e);
        }

    }

    private void changeIcon(User unmodified, User modified) {
        //TO DO
    }

    private boolean iconEqual(User unmodified, User modified) {
        if (unmodified.getIcon().equals(modified.getIcon())) {
            return true;
        }
        return false;
    }


}

