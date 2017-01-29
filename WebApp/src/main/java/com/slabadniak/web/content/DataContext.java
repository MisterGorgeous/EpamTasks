package com.slabadniak.web.content;

import com.slabadniak.web.exeption.DAOException;

import java.sql.ResultSet;
import java.util.Collection;

public interface DataContext {
     Collection get();
     void insert(ResultSet resultSet) throws DAOException;
}
