package org.example;

import java.sql.Connection;
import java.sql.Statement;

public class DbConnection {
    static String connectionUrl;
    public DbConnection(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    public static String getConnectionUrl() {
        return connectionUrl;
    }

    public static Connection getConnection() throws Exception {
        return java.sql.DriverManager.getConnection(connectionUrl);
    }

    public static Statement getStatement() throws Exception {
        return getConnection().createStatement();
    }

    public static void closeConnection() throws Exception {
        getConnection().close();
    }
}
