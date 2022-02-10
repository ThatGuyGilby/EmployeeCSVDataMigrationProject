package com.teamsix.employees.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;
import java.util.Vector;

public class ConnectionFactory
{
    private static final Logger logger = LogManager.getLogger(ConnectionFactory.class.getName());
    private static Queue<Connection> connections = new LinkedList<>();

    private static Connection getConnection()
    {
        try (InputStream inputStream = new FileInputStream("src/main/resources/mysql.properties"))
        {
            Properties properties = new Properties();
            properties.load(inputStream);

            return DriverManager.getConnection(properties.getProperty("dbURL"), properties.getProperty("dbUser"), properties.getProperty("dbPassword"));
        }
        catch (SQLException | IOException e)
        {
            logger.error(e.toString());
        }

        return null;
    }

    public static void setPooledConnections(int numberOfConnections)
    {
        connections = new LinkedList<>();

        for (int i = 0; i < numberOfConnections; i++)
        {
            connections.add(getConnection());
        }
    }

    public static Connection getConnectionFromPool()
    {
        if (connections.size() == 0)
        {
            int fallbackPoolSize = 25;
            setPooledConnections(fallbackPoolSize);
        }

        Connection connection = connections.remove();
        try
        {
            connection.setAutoCommit(false);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return connection;
    }

    public static void closeConnections()
    {
        try
        {
            for (int i = 0; i < connections.size(); i++)
            {
                connections.remove().close();
            }
        }
        catch (SQLException e)
        {
            logger.error(e.toString());
        }
    }

    public static void returnConnectionToPool(Connection connection)
    {
        connections.add(connection);
    }

    private ConnectionFactory(){}
}
