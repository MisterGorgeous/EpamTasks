package com.slabadniak.task5.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Wrapper {
    private static final Logger LOGGER = LogManager.getLogger(Wrapper.class);
    private Connection connection;
   // private static PreparedStatement psFilms;

    public Wrapper(Connection connection){
        this.connection = connection;

    }

    public Connection getConnection(){
        return connection;
    }

    public void closeConnection()  {
        if (connection != null) {
            try {
                connection.close();
                LOGGER.log(Level.DEBUG, "Closed connection");

            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, e);
            }
        }
    }

}
