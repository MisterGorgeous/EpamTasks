package com.slabadniak.web.dao;

import com.slabadniak.web.pool.Wrapper;


 /**
 * To connect with data base.
 * This class is a superclass for all DAO classes.
 * @author Slabadniak Sergei
 * @version 1.0
 */
public abstract class AbstractDAO {
    protected Wrapper wrapper;

    public AbstractDAO(Wrapper wrapper) {
        this.wrapper = wrapper;
    }

}
