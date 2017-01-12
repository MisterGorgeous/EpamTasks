package com.slabadniak.task5.content;

import com.slabadniak.task5.entity.Actor;
import com.slabadniak.task5.exeption.DAOException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class ActorContent implements DataContext {
    private List<Actor> actors = null;
    private static final int FIRNAME = 1;
    private static final int SECNAME = 2;
    private static final int BIRSTDAY = 3;
    private static final int BIRTHPLACE = 4;
    private static final int PERSON = 5;
    private static final int PROFESSION = 6;

    public ActorContent() {
        actors = new ArrayList<>();
    }

    @Override
    public Collection get() {
        return actors;
    }

    @Override
    public void insert(ResultSet resultSet) throws DAOException {
        Actor actor;
        try {
            while (resultSet.next()) {
                actor = new Actor(resultSet.getString(FIRNAME),
                        resultSet.getString(SECNAME),
                        resultSet.getString(BIRSTDAY),
                        resultSet.getString(BIRTHPLACE),
                        resultSet.getString(PERSON),
                        resultSet.getString(PROFESSION));
                actors.add(actor);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException("SQL exception", e);
        }
    }
}
