package com.slabadniak.task5.dao;

import com.slabadniak.task5.pool.Wrapper;
import com.slabadniak.task5.sessioncontent.SessionRequestContent;

import java.sql.Statement;

public abstract class AbstractDAO {
    protected Wrapper wrapper;

    public AbstractDAO(Wrapper wrapper) {
        this.wrapper = wrapper;
    }

    public abstract SessionRequestContent findAll();

    protected Statement getStatement() {
      return wrapper.getStatement();
    }

    protected void closeConnection(){
        wrapper.closeConnectionandStatement();
    }
}
