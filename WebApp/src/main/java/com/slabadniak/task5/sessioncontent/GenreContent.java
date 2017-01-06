package com.slabadniak.task5.sessioncontent;

import com.slabadniak.task5.Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GenreContent implements DataContext {
    private List<String> genre = null;
    private static final int COLUMN = 1;

    public GenreContent() {
        genre =  new ArrayList<>();
    }

    public void insert(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                genre.add(resultSet.getString(COLUMN));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public List<String> get() {
        return genre;
    }
}
