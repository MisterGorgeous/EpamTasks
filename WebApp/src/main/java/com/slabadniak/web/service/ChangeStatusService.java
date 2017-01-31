package com.slabadniak.web.service;

import com.slabadniak.web.dao.AdminDAO;
import com.slabadniak.web.entity.User;
import com.slabadniak.web.exeption.DAOException;
import com.slabadniak.web.exeption.PoolException;
import com.slabadniak.web.exeption.ServiceExeption;
import com.slabadniak.web.exeption.WrapperException;
import com.slabadniak.web.pool.ConnectionPool;
import com.slabadniak.web.pool.Wrapper;

public class ChangeStatusService {
    private ChangeStatusService() {
    }

    public static  void change(User user, int statusId) throws ServiceExeption {

        //serStatus value
        setStatus(user, statusId);

        ConnectionPool pool = ConnectionPool.getInstance();
        AdminDAO adminDAO = null;


        try {
            Wrapper connection = pool.getConnection();
            adminDAO = new AdminDAO(connection);
            adminDAO.changeStatus(user);
            pool.releaseConnection(connection);
            connection.closePreparedStatement();
        } catch (PoolException |WrapperException |DAOException  e) {
            throw new ServiceExeption("Service exception", e);
        }

    }

   private static void setStatus(User user, int status) {
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

