package com.slabadniak.task5.content;

import com.slabadniak.task5.entity.User;
import com.slabadniak.task5.exeption.DAOException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserContent implements DataContext {

    private List<User> users = null;
    private static final int LOGIN = 1;
    private static final int EMAIL = 2;
    private static final int STATUS = 3;
    private static final int BANNED = 4;
    private static final int GENDER = 5;
    private static final int ICON = 6;
    private static final int ADMIN = 7;

    public UserContent(){
        users = new ArrayList<>();
    }


    @Override
    public Collection get() {
        return users;
    }

    @Override
    public void insert(ResultSet resultSet) throws DAOException {
        User user;
        try {
            while (resultSet.next()) {
                user = new User(resultSet.getString(LOGIN),
                        resultSet.getString(EMAIL),
                        resultSet.getString(STATUS),
                        resultSet.getBoolean(BANNED),
                        resultSet.getString(GENDER),
                        resultSet.getString(ICON),
                        resultSet.getBoolean(ADMIN));
                users.add(user);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException("SQL exception", e);
        }
    }
}