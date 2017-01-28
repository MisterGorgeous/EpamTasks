package com.slabadniak.task5.dao;

import com.slabadniak.task5.pool.Wrapper;

public abstract class AbstractDAO {
    protected Wrapper wrapper;

    public AbstractDAO(Wrapper wrapper) {
        this.wrapper = wrapper;
    }

    /*protected Connection getConnection(){ return wrapper.getConnection();};

    protected void closeConnection() throws WrapperException {
        wrapper.closeConnection();
    }*/

}
