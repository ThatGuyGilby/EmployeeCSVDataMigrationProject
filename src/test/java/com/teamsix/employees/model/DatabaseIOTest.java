package com.teamsix.employees.model;

import org.junit.jupiter.api.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DatabaseIOTest {

    FileReader fileReader;
    Properties properties;
    Connection connection;
    {
        try {
            fileReader = new FileReader("src/main/resources/mysql.properties");
            properties = new Properties();
            properties.load(fileReader);
            connection = DriverManager.getConnection(properties.getProperty("dbURL"),
                    properties.getProperty("dbUser"), properties.getProperty("dbPassword"));
        } catch (IOException|SQLException e) {
            e.printStackTrace();
        }
    }

    Statement statement;
    ResultSet resultSet;

    @BeforeEach
    public void setConnectionAndWriteEmployeeEntries(){
        DatabaseIO.readEmployeesFromFile("src/main/resources/employeesbig.csv");
        DatabaseIO.writeEmployeeEntries(8);
    }

    @AfterEach
    public void closeConnection() throws SQLException{
        resultSet.close();
        statement.close();
        connection.close();
    }


    @DisplayName("Row count")
    @Test
    public void rowCount() throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM employees;");
        int rowCount = 0;
        while(resultSet.next()){
            rowCount++;
        }

        Assertions.assertEquals(9943, rowCount);
    }

    @DisplayName("Any null values of ids")
    @Test
    public void anyNullValuesOfIds() throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM employees");
        int rowCount = 0;
        while(resultSet.next()){
            if(resultSet.getString("empID") == null) {

                Assertions.fail();
            }
            rowCount++;
        }
        Assertions.assertEquals(9943, rowCount);
    }

    @DisplayName("Any null values of name prefix")
    @Test
    public void anyNullValuesOfNamePrefix() throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM employees");
        int rowCount = 0;
        while(resultSet.next()) {
            if (resultSet.getString("namePrefix") == null) {
                Assertions.fail();

            }
            rowCount++;
        }
        Assertions.assertEquals(9943,rowCount);
    }

    @DisplayName("Any null values of first name")
    @Test
    public void anyNullValuesOfFirstName() throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM employees");
        int rowCount = 0;
        while(resultSet.next()){
            if(resultSet.getString("firstName") == null) {
                Assertions.fail();
            }
            rowCount++;
        }
        Assertions.assertEquals(9943, rowCount);
    }

    @DisplayName("Any null values of last name")
    @Test
    public void anyNullValuesOfLastName() throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM employees");
        int rowCount = 0;
        while(resultSet.next()){
            if(resultSet.getString("lastName") == null) {
                Assertions.fail();
            }
            rowCount++;
        }
        Assertions.assertEquals(9943, rowCount);
    }

    @DisplayName("Any null values of gender")
    @Test
    public void anyNullValuesOfGender() throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM employees");
        int rowCount = 0;
        while(resultSet.next()){
            if(resultSet.getString("gender") == null) {
                Assertions.fail();
            }
            rowCount++;
        }
        Assertions.assertEquals(9943,rowCount);
    }

    @DisplayName("Any null values of email")
    @Test
    public void anyNullValuesOfEmail() throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM employees");
        int rowCount = 0;
        while(resultSet.next()){
            if(resultSet.getString("email") == null) {
                Assertions.fail();
            }
            rowCount++;
        }
        Assertions.assertEquals(9943, rowCount);
    }

    @DisplayName("Any null values of date of birth")
    @Test
    public void anyNullValuesOfDateOfBirth() throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM employees");
        int rowCount = 0;
        while(resultSet.next()){
            if(resultSet.getString("dateOfBirth") == null) {
                Assertions.fail();
            }
            rowCount++;
        }
        Assertions.assertEquals(9943, rowCount);
    }

    @DisplayName("Any null values of dateOfJoining")
    @Test
    public void anyNullValuesOfDateOfJoining() throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM employees");
        int rowCount = 0;
        while(resultSet.next()){
            if(resultSet.getString("dateOfJoining") == null) {
                Assertions.fail();
            }
            rowCount++;
        }
        Assertions.assertEquals(9943, rowCount);
    }

    @DisplayName("Any null values of salary")
    @Test
    public void anyNullValuesOfSalary() throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM employees");
        int rowCount = 0;
        while(resultSet.next()){
            if(resultSet.getString("salary") == null) {
                Assertions.fail();
            }
            rowCount++;
        }
        Assertions.assertEquals(9943, rowCount);
    }
}
