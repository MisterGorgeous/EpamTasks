package com.slabadniak.task5.service;

import com.slabadniak.task5.dao.AdminDAO;
import com.slabadniak.task5.exeption.DAOException;
import com.slabadniak.task5.exeption.PoolException;
import com.slabadniak.task5.exeption.ServiceExeption;
import com.slabadniak.task5.pool.ConnectionPool;
import com.slabadniak.task5.pool.Wrapper;
import com.slabadniak.task5.content.UserContent;

public class UsersService {

    public static  UserContent users() throws ServiceExeption {
        ConnectionPool pool = ConnectionPool.getInstance();
        AdminDAO adminDAO = null;
        UserContent content = new UserContent();

        try {
            Wrapper connection = pool.getConnection();
            adminDAO = new AdminDAO(connection);
            content.insert(adminDAO.users());
            pool.closeConnection(connection);
        } catch (PoolException e) {
            throw new ServiceExeption("Pool exception", e);
        } catch (DAOException e) {
            throw new ServiceExeption("UserDAO exception ", e);
        }

        return content;
    }
}
