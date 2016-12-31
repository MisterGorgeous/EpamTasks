package com.slabadniak.task5.dao;

import com.slabadniak.task5.entity.User;
import com.slabadniak.task5.pool.Wrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AdminDAO extends AbstractDAO {
    public  static final String USERS = "select login,email,status_id,banned from user WHERE admin = FALSE;";
    public  static final String BANING = "UPDATE user set banned = ? WHERE login = ?;";

    public AdminDAO(Wrapper wrapper) {
        super(wrapper);
    }


    public void userBaning(User user){
        PreparedStatement ps = null;
        try {
            ps = getConnection().prepareStatement(BANING);
            ps.setBoolean(1,user.isBanned());
            ps.setString(2,user.getLogin());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
           /* if(ps != null) {
                ps.close();
            }*/
            closeStatement();
            closeConnection();
        }

    }

    public ResultSet users(){
        PreparedStatement ps = null;
        ResultSet res = null;

        try {
            ps = getConnection().prepareStatement(USERS);
            res = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
           /* if(ps != null) {
                ps.close();
            }*/
            closeStatement();
            closeConnection();
        }
        return res;
    }
}
