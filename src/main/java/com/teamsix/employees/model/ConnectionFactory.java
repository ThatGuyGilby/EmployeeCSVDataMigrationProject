package com.teamsix.employees.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory
{
    public static Logger logger = LogManager.getLogger(EmployeeReader.class.getName());
    private static Connection connection = null;

    public static Connection getConnection()
    {
        if (connection == null)
        {
            try (InputStream inputStream = new FileInputStream("src/main/resources/mysql.properties"))
            {
                Properties properties = new Properties();
                properties.load(inputStream);

                connection = DriverManager.getConnection(properties.getProperty("dburl"), properties.getProperty("dbuserid"), properties.getProperty("dbpassword"));
            }
            catch (SQLException | IOException e)
            {
                logger.error(e.toString());
            }
        }

        return connection;
    }
}
