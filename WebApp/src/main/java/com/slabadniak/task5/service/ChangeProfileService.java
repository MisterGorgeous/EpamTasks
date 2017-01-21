package com.slabadniak.task5.service;

import com.slabadniak.task5.dao.UserDAO;
import com.slabadniak.task5.entity.User;
import com.slabadniak.task5.exeption.DAOException;
import com.slabadniak.task5.exeption.PoolException;
import com.slabadniak.task5.exeption.ServiceExeption;
import com.slabadniak.task5.pool.ConnectionPool;
import com.slabadniak.task5.pool.Wrapper;

public class ChangeProfileService {

    public static void change(User unmodified, User modified) throws ServiceExeption {
        ConnectionPool pool = ConnectionPool.getInstance();
        UserDAO userDAO;

        try {
            Wrapper connection = pool.getConnection();
            userDAO = new UserDAO(connection);

            if (!unmodified.getIcon().equals(modified.getIcon()) && !modified.getIcon().isEmpty()) {
                userDAO.changeIcon(unmodified, modified);
                unmodified.setIcon(modified.getIcon());
            }

            if (!unmodified.getLogin().equals(modified.getLogin())) {
                userDAO.changeLogin(unmodified, modified);
                unmodified.setLogin(modified.getLogin());
            }

            if (!unmodified.getEmail().equals(modified.getEmail())) {
                userDAO.changeEmail(unmodified, modified);
                unmodified.setEmail(modified.getEmail());
            }

            if (!modified.getPassword().isEmpty() && !unmodified.getPassword().equals(modified.getPassword()) ) {
                userDAO.changePassword(unmodified, modified);
                unmodified.setPassword(modified.getPassword());
            }

            if (!unmodified.getGender().equals(modified.getGender())) {
                userDAO.changeGender(unmodified, modified);
                unmodified.setGender(modified.getGender());
            }

            pool.closeConnection(connection);
        } catch (PoolException e) {
            throw new ServiceExeption("Pool exception", e);
        } catch (DAOException e) {
            throw new ServiceExeption("UserDAO exception ", e);
        }

    }

}

