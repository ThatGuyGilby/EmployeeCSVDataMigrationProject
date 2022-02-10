package com.teamsix.employees.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseEntry implements Runnable
{
    private static final Logger logger = LogManager.getLogger(ConnectionFactory.class.getName());
    private Connection connection;
    private final String name;
    private final ArrayList<Employee> list;
    private PreparedStatement preparedStatement;

    public DatabaseEntry(String name, ArrayList<Employee> list){
        this.name = name;
        this.connection = ConnectionFactory.getConnectionFromPool();
        try
        {
            StringBuilder stringBuilder = new StringBuilder("INSERT INTO employees(empID, namePrefix, firstName, middleInitial, lastName, gender, email, dateOfBirth, dateOfJoining, salary)");
            stringBuilder.append("VALUES (?,?,?,?,?,?,?,?,?,?)");
            this.preparedStatement = connection.prepareStatement(stringBuilder.toString());
        }
        catch (SQLException e)
        {
            logger.error(() -> e.toString());
        }

        this.list = list;
    }

    @Override
    public void run(){
        double timeBefore = System.nanoTime();
        try
        {
            for (Employee e : list)
            {
                preparedStatement.setInt(1, e.getEmpID());
                preparedStatement.setString(2, e.getNamePrefix());
                preparedStatement.setString(3, e.getFirstName());
                preparedStatement.setString(4, String.valueOf(e.getMiddleInitial()));
                preparedStatement.setString(5, e.getLastName());
                preparedStatement.setString(6, String.valueOf(e.getGender()));
                preparedStatement.setString(7, e.getEmail());
                preparedStatement.setDate(8, e.getDateOfBirth());
                preparedStatement.setDate(9, e.getDateOfJoining());
                preparedStatement.setFloat(10, e.getSalary());

                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();

            connection.commit();
            ConnectionFactory.returnConnectionToPool(connection);
            connection = null;
        }
        catch (SQLException e)
        {
            logger.error(() -> e.toString());
        }

        System.out.println(name + " took: " + ((System.nanoTime() - timeBefore)/1000000000) + " seconds");
    }
}
