package com.teamsix.employees.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseEntry extends Thread{
    private String name;
    private List<Employee> list;
    private PreparedStatement preparedStatement;

    public DatabaseEntry(String name, PreparedStatement preparedStatement, List<Employee> list){
        this.name = name;
        this.preparedStatement = preparedStatement;
        this.list = list;
    }

    public void databaseInformationEntry() throws SQLException {
        for (Employee e : list){
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

            preparedStatement.executeUpdate();
        }
        System.out.println(name);
    }
}
