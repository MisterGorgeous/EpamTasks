package com.slabadniak.web.pool;

import com.slabadniak.web.exeption.WrapperException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Wrapper {
    private static final Logger LOGGER = LogManager.getLogger(Wrapper.class);
    private Connection connection;
    private PreparedStatement ps;

    public Wrapper(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        LOGGER.log(Level.DEBUG, "Connection taken.");
        return connection;
    }

    public void closeConnection() throws WrapperException {
        if (connection != null) {
            try {
                connection.close();
                LOGGER.log(Level.DEBUG, "Connection closed.");

            } catch (SQLException e) {
                throw new WrapperException("SQL exception ", e);
            }
        }
    }

    public PreparedStatement prepareStatement(String comments) throws WrapperException {
        try { //closed check
            if (this.ps != null ) {
                if(!ps.isClosed()) {
                    ps.close();
                }
            }
            ps = connection.prepareStatement(comments);
            LOGGER.log(Level.DEBUG, "Connection closed.");
        } catch (SQLException e) {
            throw new WrapperException("SQL exception ", e);
        }
        return ps;
    }

    public void closePreparedStatement() throws WrapperException {
        try {
            ps.close();
        } catch (SQLException e) {
            throw new WrapperException("SQL exception ", e);
        }
    }

    public void commit() throws WrapperException {

        try {
            connection.commit();
            LOGGER.log(Level.DEBUG, "Connection closed.");
        } catch (SQLException e) {
            throw new WrapperException("SQL exception ", e);
        }
    }

    public void setAutoCommit(boolean autoCommit) throws WrapperException {
        try {
            connection.setAutoCommit(autoCommit);
            LOGGER.log(Level.DEBUG, "Connection closed.");
        } catch (SQLException e) {
            throw new WrapperException("SQL exception ", e);
        }
    }

    public void rollback() throws WrapperException {
        try {
            connection.rollback();
            LOGGER.log(Level.DEBUG, "Connection closed.");
        } catch (SQLException e) {
            throw new WrapperException("SQL exception ", e);
        }
    }
}