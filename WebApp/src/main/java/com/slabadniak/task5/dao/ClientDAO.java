package com.slabadniak.task5.dao;

import com.slabadniak.task5.pool.Wrapper;
import com.slabadniak.task5.sessioncontent.SessionRequestContent;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ClientDAO extends AbstractDAO {
    public  static final String GENRES = "SELECT name FROM genre_kind;";

    public ClientDAO(Wrapper wrapper){
        super(wrapper);
    }

    @Override
    public SessionRequestContent findAll() {
        ResultSet genres = null;
        try {
            genres = getStatement().executeQuery(GENRES);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        SessionRequestContent content = new SessionRequestContent();
        content.insert(genres);
        closeConnection();
        return content;
    }


}
