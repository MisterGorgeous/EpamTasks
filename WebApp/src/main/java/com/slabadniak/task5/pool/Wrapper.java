package com.slabadniak.task5.pool;

import com.slabadniak.task5.Builder;
import com.slabadniak.task5.pool.ConnectionPool;
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
    private Statement statement ;
    private static PreparedStatement psFilms;

    public Wrapper(Connection connection){
        this.connection = connection;

    }

    public Statement getStatement() {
        if(psFilms == null){
            try {
                psFilms = connection.prepareStatement("SELECT title,icon,rating FROM movie WHERE year = 2009;");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
       /* if(sqlQuery.isEmpty()){
            return psFilms;
        }*/
        if(statement == null) {
            try {
                statement = connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return statement;
    }

    public void closeConnectionandStatement()  {
        if (connection != null && statement != null) {
            try {
                statement.close();
                connection.close();
                LOGGER.log(Level.DEBUG, "Closed connection");

            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, e);
            }
        }
    }

}
