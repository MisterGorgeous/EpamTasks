package com.slabadniak.web.dao;

import com.slabadniak.web.pool.Wrapper;

public abstract class AbstractDAO {
    protected Wrapper wrapper;

    public AbstractDAO(Wrapper wrapper) {
        this.wrapper = wrapper;
    }

}
