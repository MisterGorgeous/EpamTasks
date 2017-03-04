package com.slabadniak.web.content;

import com.slabadniak.web.exeption.DAOException;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Collection;


/**
 * Represent all data.
 * @author Slabadniak Sergei
 * @version 1.0
 */
public interface DataContext extends Serializable {
     Collection get();
     void insert(ResultSet resultSet) throws DAOException;
}
