package com.slabadniak.task5.dao;

import com.slabadniak.task5.pool.Wrapper;
import com.slabadniak.task5.sessioncontent.GenreContent;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ClientDAO extends AbstractDAO {
    public  static final String GENRES = "SELECT name FROM genre_kind;";

    public ClientDAO(Wrapper wrapper){
        super(wrapper);
    }

    public GenreContent findAll() {
        ResultSet genres = null;
        try {
            genres = getStatement().executeQuery(GENRES);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        GenreContent content = new GenreContent();
        content.insert(genres);
        closeConnection();
        return content;
    }


}
