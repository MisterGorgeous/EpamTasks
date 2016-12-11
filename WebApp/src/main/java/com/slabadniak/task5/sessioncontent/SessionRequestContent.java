package com.slabadniak.task5.sessioncontent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SessionRequestContent {
    private Set<String> genre = null;
    private static final int COLUMN = 1;

    public SessionRequestContent() {
        genre =  new HashSet<>();
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

    public Set get() {
        return genre;
    }
}
