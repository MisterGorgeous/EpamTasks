package com.slabadniak.task5.sessioncontent;

import com.slabadniak.task5.entity.Actor;
import com.slabadniak.task5.entity.Movie;

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

    public ActorContent(){
        actors = new ArrayList<Actor>();
    }

    @Override
    public Collection get() {
       return actors;
    }

    @Override
    public void insert(ResultSet resultSet) {
        Actor actor;
        try {
            while (resultSet.next()) {
                actor = new Actor(resultSet.getString(FIRNAME),
                        resultSet.getString(SECNAME),
                        resultSet.getString(BIRSTDAY));
                actors.add(actor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
