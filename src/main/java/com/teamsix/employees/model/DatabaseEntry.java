package com.teamsix.employees.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class DatabaseEntry implements Runnable
{
    private Connection connection;
    private final String name;
    private final List<Employee> list;
    private PreparedStatement preparedStatement;

    public DatabaseEntry(String name, Vector<Employee> list){
        this.name = name;
        this.connection = ConnectionFactory.getConnectionFromPool();
        try
        {
            this.preparedStatement = connection.prepareStatement(
                    "INSERT INTO employees(empID, namePrefix, firstName, middleInitial, lastName, gender, email, dateOfBirth, dateOfJoining, salary)" +
                            "VALUES (?,?,?,?,?,?,?,?,?,?)");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        this.list = new Vector<>(list);
    }

    @Override
    public void run(){
        double timeBefore = System.nanoTime();
        try
        {
            for (int i = 0, listSize = list.size(); i < listSize; i++)
            {
                Employee e = list.get(i);
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
            e.printStackTrace();
        }

        System.out.println(name + " took: " + ((System.nanoTime() - timeBefore)/1000000000) + " seconds");
    }
}
