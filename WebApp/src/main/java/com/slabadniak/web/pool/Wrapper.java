package com.slabadniak.web.pool;

import com.slabadniak.web.exeption.WrapperException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



/**
 * Wrapper is the part of Wrapper Pattern. This object is used to substitute
 * the real one and provide a new functionality.
 * @author Slabadniak Sergei
 * @version 1.0
 */
public class Wrapper {
    private static final Logger LOGGER = LogManager.getLogger(Wrapper.class);
    private Connection connection;
    private PreparedStatement ps;

    public Wrapper(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    /**
     * @see Connection#close()
     * @throws WrapperException
     */
    public void closeConnection() throws WrapperException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new WrapperException("SQL exception ", e);
            }
        }
    }

    /**
     *
     * @param request to DB
     * @see Connection#prepareStatement(String)
     * @return
     * @throws WrapperException
     */
    public PreparedStatement prepareStatement(String request) throws WrapperException {
        try { //closed check
            if (this.ps != null ) {
                if(!ps.isClosed()) {
                    ps.close();
                }
            }
            ps = connection.prepareStatement(request);
        } catch (SQLException e) {
            throw new WrapperException("SQL exception ", e);
        }
        return ps;
    }

    /**
     * @throws WrapperException
     */
    public void closePreparedStatement() throws WrapperException {
        try {
            if (this.ps != null ) {
                ps.close();
            }
        } catch (SQLException e) {
            throw new WrapperException("SQL exception ", e);
        }
    }

    /**
     * @see Connection#commit()
     * @throws WrapperException
     */
    public void commit() throws WrapperException {

        try {
            connection.commit();
        } catch (SQLException e) {
            throw new WrapperException("SQL exception ", e);
        }
    }

    /**
     * @see Connection#setAutoCommit(boolean)
     * @throws WrapperException
     */
    public void setAutoCommit(boolean autoCommit) throws WrapperException {
        try {
            connection.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            throw new WrapperException("SQL exception ", e);
        }
    }

    /**
     * @see Connection#rollback()
     * @throws WrapperException
     */
    public void rollback() throws WrapperException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new WrapperException("SQL exception ", e);
        }
    }
}