package com.slabadniak.task5.service;

import com.slabadniak.task5.dao.AdminDAO;
import com.slabadniak.task5.entity.User;
import com.slabadniak.task5.exeption.DAOException;
import com.slabadniak.task5.exeption.PoolException;
import com.slabadniak.task5.exeption.ServiceExeption;
import com.slabadniak.task5.pool.ConnectionPool;
import com.slabadniak.task5.pool.Wrapper;

public class ChangeStatusService {

    public void change(User user, int statusId) throws ServiceExeption {

        //serStatus value
        setStatus(user, statusId);

        ConnectionPool pool = ConnectionPool.getInstance();
        AdminDAO adminDAO = null;


        try {
            Wrapper connection = pool.getConnection();
            adminDAO = new AdminDAO(connection);
            adminDAO.changeStatus(user);
            pool.closeConnection(connection);
        } catch (PoolException e) {
            throw new ServiceExeption("Pool exception", e);
        } catch (DAOException e) {
            throw new ServiceExeption("UserDAO exception ", e);
        }

    }

    void setStatus(User user, int status) {
        switch (status) {
            case 1:
                user.setStatus("beginer");
                break;
            case 2:
                user.setStatus("fan");
                break;
            default:
                user.setStatus("expert");
        }
    }
}

