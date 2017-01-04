package com.slabadniak.task5.sessioncontent;

import com.slabadniak.task5.entity.Actor;
import com.slabadniak.task5.entity.User;

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

    public UserContent(){
        users = new ArrayList<>();
    }


    @Override
    public Collection get() {
        return users;
    }

    @Override
    public void insert(ResultSet resultSet) {
        User user;
        try {
            while (resultSet.next()) {
                user = new User(resultSet.getString(LOGIN),
                        resultSet.getString(EMAIL),
                        resultSet.getString(STATUS),
                        resultSet.getBoolean(BANNED),
                        resultSet.getString(GENDER),
                        resultSet.getString(ICON));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
