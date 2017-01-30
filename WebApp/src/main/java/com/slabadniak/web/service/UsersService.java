package com.slabadniak.web.service;

import com.slabadniak.web.dao.AdminDAO;
import com.slabadniak.web.exeption.DAOException;
import com.slabadniak.web.exeption.PoolException;
import com.slabadniak.web.exeption.ServiceExeption;
import com.slabadniak.web.exeption.WrapperException;
import com.slabadniak.web.pool.ConnectionPool;
import com.slabadniak.web.pool.Wrapper;
import com.slabadniak.web.content.UserContent;

public class UsersService {

    public static  UserContent users() throws ServiceExeption {
        ConnectionPool pool = ConnectionPool.getInstance();
        AdminDAO adminDAO = null;
        UserContent content = new UserContent();

        try {
            Wrapper connection = pool.getConnection();
            adminDAO = new AdminDAO(connection);
            content.insert(adminDAO.users());
            pool.releaseConnection(connection);
            connection.closePreparedStatement();
        } catch (PoolException |WrapperException |DAOException  e) {
            throw new ServiceExeption("Service exception", e);
        }

        return content;
    }
}
