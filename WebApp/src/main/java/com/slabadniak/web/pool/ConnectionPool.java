package com.slabadniak.web.pool;

import com.slabadniak.web.exeption.PoolException;
import com.slabadniak.web.exeption.WrapperException;
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
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import com.mysql.jdbc.Driver;

import javax.annotation.PreDestroy;

/**
 * ConnectionPool represents an ability to connect with database.
 *
 **/
public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static ConnectionPool instance;
    private ArrayBlockingQueue<Wrapper> connections;  //once created, it can't be resized
    private static AtomicBoolean created = new AtomicBoolean(false);
    private static Properties property;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean freeConnections = new AtomicBoolean(true);
    private static int capacity;
    private static final int TIMEQUANTUM = 3;


    /**
     * private constructor is part of Singleton pattern.
     */
    private ConnectionPool() {
        property = new Properties();
        try {
            DriverManager.registerDriver(new Driver());
            LOGGER.log(Level.DEBUG, "Driver set");
            ResourceBundle resource = ResourceBundle.getBundle("config");
            String url = resource.getString("url");
            capacity = Integer.parseInt(resource.getString("pool"));
            property.put("user", resource.getString("login"));
            property.put("password", resource.getString("password"));
            property.put("autoReconnect", resource.getString("autoreconnect"));
            property.put("characterEncoding", resource.getString("encoding"));
            property.put("useUnicode", resource.getString("unicode"));
            connections = new ArrayBlockingQueue<>(capacity);
            for (int i = 0; i < capacity; i++) {
                Connection conn = DriverManager.getConnection(url, property);
                Wrapper connection = new Wrapper(conn);
                connections.offer(connection);
            }
            if (connections.size() != capacity) {
                throw new RuntimeException("Connections was not created");
            }
            LOGGER.log(Level.DEBUG, "Pool initialized");
        } catch (MissingResourceException e) {
            throw new RuntimeException("Missing resource ", e);
        } catch (SQLException e) {
            throw new RuntimeException("SQL exception ", e);
        }
    }

    /**
     * This instance method returns the unique ConnectionPool object.
     * @return
     */
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


    /**
     * Check TIMEQUANTUM and return Wrapper object.
     * Method take isn't used to prevent long blocking.
     * @return
     * @throws PoolException
     */
    public Wrapper getConnection() throws PoolException {
        if (freeConnections.get()) {
            try {
                LOGGER.log(Level.DEBUG,"Connection taken");
                return connections.poll(TIMEQUANTUM, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new PoolException("Interrupted:", e);
            }
        }
        return null;
    }

    /**
     * This method returns a spare connection to the pool.
     * @param connection
     * @throws PoolException
     */
    public void releaseConnection(Wrapper connection) throws PoolException {
        try {
            connections.put(connection);
            LOGGER.log(Level.DEBUG, "Connection returned");
        } catch (InterruptedException e) {
            throw new PoolException("Interrupted:", e);
        }
    }

    /**
     * This method returns all connections to the pool.
     * @throws PoolException
     * @throws WrapperException
     */
    private void releaseAllPoolConnections() throws PoolException, WrapperException {
        freeConnections.set(false);
        try {
            TimeUnit.SECONDS.sleep(TIMEQUANTUM);
            for (Wrapper connection : connections) {
                    connections.put(connection);
            }
            LOGGER.log(Level.DEBUG, "Pool released");
        } catch (InterruptedException e) {
            throw new PoolException("Release connection exception", e);
        }
    }

    /**
     * Close Pool.The method annotated with PreDestroy is typically
     * used to release resources that it has been holding.
     * @throws PoolException
     * @throws WrapperException
     */
    @PreDestroy
    public void closePool() throws PoolException, WrapperException {
        freeConnections.set(false);
        releaseAllPoolConnections(); //Release all connections
        for (int i = 0; i < capacity; i++) {
            try {
                Wrapper wrapper = connections.take();
                wrapper.closeConnection();
            } catch (InterruptedException e) {
                throw new PoolException("Interrupted:", e);
            } catch (WrapperException e) {
                throw new WrapperException("Wrapper:", e);
            }
        }
        LOGGER.log(Level.DEBUG, "Pool closed");
    }
}




