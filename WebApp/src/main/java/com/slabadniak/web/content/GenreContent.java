package com.slabadniak.web.content;

import com.slabadniak.web.exeption.DAOException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreContent implements DataContext {
    private List<String> genre = null;
    private static final int COLUMN = 1;

    public GenreContent() {
        genre =  new ArrayList<>();
    }

    public void insert(ResultSet resultSet) throws DAOException {
        try {
            while (resultSet.next()) {
                genre.add(resultSet.getString(COLUMN));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DAOException("SQL exception", e);
        }
    }

    public List<String> get() {
        return genre;
    }
}
