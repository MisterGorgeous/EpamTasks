package com.slabadniak.web.content;

import com.slabadniak.web.entity.Movie;
import com.slabadniak.web.exeption.DAOException;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Contain information about movies.
 * @author Slabadniak Sergei
 * @version 1.0
 */
public class MovieContent implements DataContext {
    private List<Movie> films = null;
    private static final int TITLE = 1;
    private static final int RATING = 2;
    private static final int ICON = 3;
    private static final int YEAR = 4;
    private static final int COUNTRY = 5;
    private static final int DESCRIPTION = 6;

    public MovieContent() {
        films =  new ArrayList<>();
    }

    @Override
    public void insert(ResultSet resultSet) throws DAOException {
        Movie movie;
        try {
            while (resultSet.next()) {
                movie = new Movie(resultSet.getString(TITLE),
                        Float.valueOf(resultSet.getString(RATING)),
                        resultSet.getString(ICON),
                        resultSet.getString(YEAR),
                        resultSet.getString(COUNTRY),
                        resultSet.getString(DESCRIPTION));
                films.add(movie);
            }

            resultSet.close();

        } catch (SQLException e) {
            throw new DAOException("SQL exception", e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public List<Movie> get() {
        return films;
    }
}
