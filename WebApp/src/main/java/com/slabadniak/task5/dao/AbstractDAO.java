package com.slabadniak.task5.dao;

import com.slabadniak.task5.pool.Wrapper;

import java.sql.Connection;
import java.sql.Statement;

public abstract class AbstractDAO {
    private Wrapper wrapper;

    public AbstractDAO(Wrapper wrapper) {
        this.wrapper = wrapper;
    }

   // public abstract GenreContent findAll();

    protected Connection getConnection(){ return wrapper.getConnection();};

    protected void closeConnection(){
        wrapper.closeConnection();
    }

}
