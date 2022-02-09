package com.teamsix.employees.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Employee
{
    // these variables need to be made private but they are used in tests,
    // just add a getter and switch the usage in tese code
    private static Logger logger = LogManager.getLogger(Employee.class.getName());

    private int empID;
    private String namePrefix;
    private String firstName;
    private char middleInitial;
    private String lastName;
    private char gender;
    private String email;
    private java.sql.Date dateOfBirth;
    private java.sql.Date dateOfJoining;
    private float salary;


    public int getEmpID() {
        return empID;
    }

    public String getNamePrefix() {
        return namePrefix;
    }

    public char getMiddleInitial() {
        return middleInitial;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public Employee(String[] data)
    {
        this.empID = Integer.parseInt(data[0]);
        this.namePrefix = data[1];
        this.firstName = data[2];
        this.middleInitial = data[3].charAt(0);
        this.lastName = data[4];
        this.gender = data[5].charAt(0);
        this.email = data[6];

        try
        {
            String strDate = data[7];

            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            java.util.Date utilDate = sdf.parse(strDate);
            this.dateOfBirth = new java.sql.Date(utilDate.getTime());
        }
        catch (ParseException e)
        {
            logger.error(e.toString());
        }

        try
        {
            String strDate = data[8];

            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            java.util.Date utilDate = sdf.parse(strDate);
            this.dateOfJoining = new java.sql.Date(utilDate.getTime());
        }
        catch (ParseException e)
        {
            logger.error(e.toString());
        }

        this.salary = Integer.parseInt(data[9]);
    }

    public Employee(ResultSet resultSet)
    {
        try
        {
            this.empID = resultSet.getInt("empID");
            this.namePrefix = resultSet.getString("namePrefix");
            this.firstName = resultSet.getString("firstName");
            this.middleInitial = resultSet.getString("middleInitial").charAt(0);
            this.lastName = resultSet.getString("lastName");
            this.gender = resultSet.getString("gender").charAt(0);
            this.email = resultSet.getString("email");
            this.dateOfBirth = resultSet.getDate("dateOfBirth");
            this.dateOfJoining = resultSet.getDate("dateOfJoining");
            this.salary = resultSet.getFloat("salary");
        }
        catch (SQLException e)
        {
            logger.error(e.toString());
        }
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder("\"");
        stringBuilder.append(String.valueOf(empID));
        stringBuilder.append("\" , \"");
        stringBuilder.append(namePrefix);
        stringBuilder.append("\" , \"");
        stringBuilder.append(firstName);
        stringBuilder.append("\" , \"");
        stringBuilder.append(middleInitial);
        stringBuilder.append("\" , \"");
        stringBuilder.append(lastName);
        stringBuilder.append("\" , \"");
        stringBuilder.append(gender);
        stringBuilder.append("\" , \"");
        stringBuilder.append(email);
        stringBuilder.append("\" , \"");
        stringBuilder.append(dateOfBirth);
        stringBuilder.append("\" , \"");
        stringBuilder.append(dateOfJoining);
        stringBuilder.append("\" , \"");
        stringBuilder.append(salary);
        stringBuilder.append("\"");

        return stringBuilder.toString();
    }
}
