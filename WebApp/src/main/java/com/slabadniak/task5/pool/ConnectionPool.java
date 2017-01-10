package com.slabadniak.task5.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import com.mysql.jdbc.Driver;


public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static ConnectionPool instance;
    private ArrayBlockingQueue<Wrapper> connections;
    private static AtomicBoolean created = new AtomicBoolean(false);
    private static Properties property;
    private static ReentrantLock lock = new ReentrantLock();
    private static int maxPool;
    private static int poolSize;


    private ConnectionPool() {
        property = new Properties();
        try {
            DriverManager.registerDriver(new Driver());
            LOGGER.debug("Driver set");
            ResourceBundle resource = ResourceBundle.getBundle("config");
            String url = resource.getString("url");
            maxPool = Integer.parseInt(resource.getString("pool"));
            property.put("user", resource.getString("login"));
            property.put("password", resource.getString("password"));
            property.put("autoReconnect", resource.getString("autoreconnect"));
            property.put("characterEncoding", resource.getString("encoding"));
            property.put("useUnicode", resource.getString("unicode"));
            connections = new ArrayBlockingQueue<>(maxPool);
            poolSize = 0;
            for (int i = 0; i < maxPool; i++) {
                Connection conn = DriverManager.getConnection(url, property);
                Wrapper connection = new Wrapper(conn);
                connections.offer(connection);
                poolSize++;
            }
            if (connections.size() != maxPool) {
                throw new RuntimeException("Connections was not created");
            }
            LOGGER.log(Level.DEBUG, "Pool initialized");
        } catch (MissingResourceException e) {
            throw new RuntimeException("Missing resource ", e);
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception ", e);
        }
    }

    public static ConnectionPool getInstance() {
        if (!created.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    created.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }


    public Wrapper getConnection() throws InterruptedException {
        LOGGER.log(Level.DEBUG, "Connection taken");
        return connections.take();
    }


    public void closeConnection(Wrapper connection) throws InterruptedException {
        connections.put(connection);
        LOGGER.log(Level.DEBUG, "Connection returned");
    }

    public void closePool() {
        for (int i = 0; i < maxPool; i++) {
            try {
                Wrapper wrapper = connections.take();

                wrapper.closeConnection();
            } catch (InterruptedException e) {
                LOGGER.log(Level.DEBUG, "Interrupted exc");
            }
        }
    }

}