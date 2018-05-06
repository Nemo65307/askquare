package com.nemo.askquare.repository;

import java.sql.Connection;

public class ConnectionUtils {

    private static final ThreadLocal<Connection> connections = new ThreadLocal<>();

    public static Connection getCurrentConnection() {
        Connection connection = connections.get();
        if (connection == null) {
            throw new IllegalStateException("Connection not found for current thread");
        }
        return connection;
    }

    public static void setCurrentConnection(Connection connection) {
        connections.set(connection);
    }

    public static void removeCurrentConnection() {
        connections.remove();
    }

}
