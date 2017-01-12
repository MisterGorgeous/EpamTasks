package com.slabadniak.task5.content;

import com.slabadniak.task5.exeption.DAOException;

import java.sql.ResultSet;
import java.util.Collection;

public interface DataContext {
     Collection get();
     void insert(ResultSet resultSet) throws DAOException;
}
