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
    private ChangeProfileService() {
    }

    public static void change(User unmodified, User modified) throws ServiceExeption {
        ConnectionPool pool = ConnectionPool.getInstance();
        UserDAO userDAO;

        try {
            Wrapper connection = pool.getConnection();
            userDAO = new UserDAO(connection);

            if (modified.getIcon() != null && !unmodified.getIcon().equals(modified.getIcon()) && !modified.getIcon().isEmpty() && !modified.getIcon().equals("")) {
                userDAO.changeIcon(unmodified, modified);
                unmodified.setIcon(modified.getIcon());
            }

            if (modified.getLogin() != null && !unmodified.getLogin().equals(modified.getLogin())) {
                userDAO.changeLogin(unmodified, modified);
                unmodified.setLogin(modified.getLogin());
            }

            if (modified.getEmail() != null &&!unmodified.getEmail().equals(modified.getEmail())) {
                userDAO.changeEmail(unmodified, modified);
                unmodified.setEmail(modified.getEmail());
            }

            if (modified.getPassword() != null && !modified.getPassword().isEmpty() && !unmodified.getPassword().equals(modified.getPassword()) && !modified.getPassword().equals("")) {
                userDAO.changePassword(unmodified, modified);
                unmodified.setPassword(modified.getPassword());
            }

            if (modified.getGender() != null &&!unmodified.getGender().equals(modified.getGender())) {
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

