package com.slabadniak.task5.pool;

import com.slabadniak.task5.exeption.WrapperException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class Wrapper {
    private static final Logger LOGGER = LogManager.getLogger(Wrapper.class);
    private Connection connection;

    public Wrapper(Connection connection){
        this.connection = connection;

    }

    public Connection getConnection(){
        LOGGER.log(Level.DEBUG, "Connection taken.");
        return connection;
    }

    public void closeConnection() throws WrapperException  {
        if (connection != null) {
            try {
                connection.close();
                LOGGER.log(Level.DEBUG, "Connection closed.");

            } catch (SQLException e) {
                throw new WrapperException("SQL exception ", e);
            }
        }
    }


}
