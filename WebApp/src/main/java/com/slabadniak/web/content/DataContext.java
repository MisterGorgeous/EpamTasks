package com.slabadniak.web.content;

import com.slabadniak.web.exeption.DAOException;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Collection;

public interface DataContext extends Serializable {
     Collection get();
     void insert(ResultSet resultSet) throws DAOException;
}
