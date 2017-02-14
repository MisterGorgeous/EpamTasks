package com.slabadniak.web.dao;

import com.slabadniak.web.pool.Wrapper;

/**
 * To connect with data base.
 * Class is a superclass for all DAO classes.
 */
public abstract class AbstractDAO {
    protected Wrapper wrapper;

    public AbstractDAO(Wrapper wrapper) {
        this.wrapper = wrapper;
    }

}
