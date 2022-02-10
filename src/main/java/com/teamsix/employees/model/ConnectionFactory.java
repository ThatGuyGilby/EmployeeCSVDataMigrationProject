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

public class ConnectionFactory
{
    private static Logger logger = LogManager.getLogger(ConnectionFactory.class.getName());
    private static int defaultPooledConnections = 25;
    private static Queue<Connection> connections = new LinkedList<>();

    private static Connection getConnection()
    {
        try (InputStream inputStream = new FileInputStream("src/main/resources/mysql.properties"))
        {
            Properties properties = new Properties();
            properties.load(inputStream);

            Connection connection = DriverManager.getConnection(properties.getProperty("dbURL"), properties.getProperty("dbUser"), properties.getProperty("dbPassword"));
            return connection;
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
        if (connections.size() <= 0)
        {
            setPooledConnections(defaultPooledConnections);
        }

        return connections.remove();
    }

    public static void returnConnectionToPool(Connection connection)
    {
        connections.add(connection);
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

    private ConnectionFactory(){}
}
