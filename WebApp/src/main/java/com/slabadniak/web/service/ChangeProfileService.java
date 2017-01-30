package com.slabadniak.web.service;

import com.slabadniak.web.dao.UserDAO;
import com.slabadniak.web.entity.User;
import com.slabadniak.web.exeption.DAOException;
import com.slabadniak.web.exeption.PoolException;
import com.slabadniak.web.exeption.ServiceExeption;
import com.slabadniak.web.exeption.WrapperException;
import com.slabadniak.web.pool.ConnectionPool;
import com.slabadniak.web.pool.Wrapper;

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

            pool.releaseConnection(connection);
            connection.closePreparedStatement();
        } catch (PoolException |WrapperException |DAOException  e) {
            throw new ServiceExeption("Service exception", e);
        }

    }

}

