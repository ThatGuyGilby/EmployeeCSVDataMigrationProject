package com.teamsix.employees.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceLock;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
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
    private static final int EXPECTED_ROW_COUNT = 9943;
    private static final String RESOURCE_LOCK = "src/main/resources/employees.csv";
    private static final String RESOURCE_LOCK_EXAMPLE_FILE = "unitTestCsv/DatabaseIOTest.csv";

    public void closeConnection() throws SQLException {
        resultSet.close();
        statement.close();
        connection.close();
    }

    @DisplayName("Row count")
    @Test
    @ResourceLock(value = RESOURCE_LOCK)
    public void rowCount() throws SQLException {
        DatabaseIO.readEmployeesFromFile("src/main/resources/employees.csv");
        DatabaseIO.writeEmployeeEntries(4);
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM employees");
        int rowCount = 0;
        while(resultSet.next()){
            rowCount++;
        }

        Assertions.assertEquals(EXPECTED_ROW_COUNT, rowCount);
        closeConnection();
    }

    @DisplayName("Throws arithmetic exception if user enters 0 to number of threads")
    @Test
    @ResourceLock(RESOURCE_LOCK)
    public void throwsArithmeticExceptionIfUserEnters0ToNumberOfThreads(){
        ArrayList<Employee> employees = DatabaseIO.readEmployeesFromFile("src/main/resources/employees.csv");
        Assertions.assertThrows(ArithmeticException.class, () ->
        {
            DatabaseIO.generateDatabaseEntries(0,employees);
        });
    }

    @DisplayName("Any null values of ids")
    @Test
    @ResourceLock(value= RESOURCE_LOCK)
    public void anyNullValuesOfIds() throws SQLException {
        DatabaseIO.readEmployeesFromFile("src/main/resources/employees.csv");
        DatabaseIO.writeEmployeeEntries(4);
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM employees");
        int rowCount = 0;
        while(resultSet.next()){
            if(resultSet.getString("empID") == null) {
                Assertions.fail();
            }
            rowCount++;
        }
        Assertions.assertEquals(EXPECTED_ROW_COUNT,rowCount);
        closeConnection();
    }

    @DisplayName("Any null values of name prefix")
    @Test
    @ResourceLock(value= RESOURCE_LOCK)
    public void anyNullValuesOfNamePrefix() throws SQLException {
        DatabaseIO.readEmployeesFromFile("src/main/resources/employees.csv");
        DatabaseIO.writeEmployeeEntries(4);
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM employees");
        int rowCount = 0;
        while(resultSet.next()) {
            if (resultSet.getString("namePrefix") == null) {
                Assertions.fail();
            }
            rowCount++;
        }
        Assertions.assertEquals(EXPECTED_ROW_COUNT,rowCount);
        closeConnection();
    }

    @DisplayName("Any null values of first name")
    @Test
    @ResourceLock(value= RESOURCE_LOCK)
    public void anyNullValuesOfFirstName() throws SQLException {
        DatabaseIO.readEmployeesFromFile("src/main/resources/employees.csv");
        DatabaseIO.writeEmployeeEntries(4);
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM employees");
        int rowCount = 0;
        while(resultSet.next()) {
            if (resultSet.getString("firstName") == null) {
                Assertions.fail();
            }
            rowCount++;
        }
        Assertions.assertEquals(EXPECTED_ROW_COUNT,rowCount);
        closeConnection();
    }

    @DisplayName("Any null values of last name")
    @Test
    @ResourceLock(value= RESOURCE_LOCK)
    public void anyNullValuesOfLastName() throws SQLException {
        DatabaseIO.readEmployeesFromFile("src/main/resources/employees.csv");
        DatabaseIO.writeEmployeeEntries(4);
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM employees");
        int rowCount = 0;
        while(resultSet.next()) {
            if (resultSet.getString("lastName") == null) {
                Assertions.fail();
            }
            rowCount++;
        }
        Assertions.assertEquals(EXPECTED_ROW_COUNT,rowCount);
        closeConnection();
    }

    @DisplayName("Any null values of gender")
    @Test
    @ResourceLock(value= RESOURCE_LOCK)
    public void anyNullValuesOfGender() throws SQLException {
        DatabaseIO.readEmployeesFromFile("src/main/resources/employees.csv");
        DatabaseIO.writeEmployeeEntries(4);
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM employees");
        int rowCount = 0;
        while(resultSet.next()) {
            if (resultSet.getString("gender") == null) {
                Assertions.fail();
            }
            rowCount++;
        }
        Assertions.assertEquals(EXPECTED_ROW_COUNT,rowCount);
        closeConnection();
    }

    @DisplayName("Any null values of email")
    @Test
    @ResourceLock(value= RESOURCE_LOCK)
    public void anyNullValuesOfEmail() throws SQLException {
        DatabaseIO.readEmployeesFromFile("src/main/resources/employees.csv");
        DatabaseIO.writeEmployeeEntries(4);
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM employees");
        int rowCount = 0;
        while(resultSet.next()) {
            if (resultSet.getString("email") == null) {
                Assertions.fail();
            }
            rowCount++;
        }
        Assertions.assertEquals(EXPECTED_ROW_COUNT,rowCount);
        closeConnection();
    }

    @DisplayName("Any null values of date of birth")
    @Test
    @ResourceLock(value= RESOURCE_LOCK)
    public void anyNullValuesOfDateOfBirth() throws SQLException {
        DatabaseIO.readEmployeesFromFile("src/main/resources/employees.csv");
        DatabaseIO.writeEmployeeEntries(4);
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM employees");
        int rowCount = 0;
        while(resultSet.next()) {
            if (resultSet.getString("dateOfBirth") == null) {
                Assertions.fail();
            }
            rowCount++;
        }
        Assertions.assertEquals(EXPECTED_ROW_COUNT,rowCount);
        closeConnection();
    }

    @DisplayName("Any null values of dateOfJoining")
    @Test
    @ResourceLock(value= RESOURCE_LOCK)
    public void anyNullValuesOfDateOfJoining() throws SQLException {
        DatabaseIO.readEmployeesFromFile("src/main/resources/employees.csv");
        DatabaseIO.writeEmployeeEntries(4);
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM employees");
        int rowCount = 0;
        while(resultSet.next()) {
            if (resultSet.getString("dateOfJoining") == null) {
                Assertions.fail();
            }
            rowCount++;
        }
        Assertions.assertEquals(EXPECTED_ROW_COUNT,rowCount);
        closeConnection();
    }

    @DisplayName("Any null values of salary")
    @Test
    @ResourceLock(value= RESOURCE_LOCK)
    public void anyNullValuesOfSalary() throws SQLException {
        DatabaseIO.readEmployeesFromFile("src/main/resources/employees.csv");
        DatabaseIO.writeEmployeeEntries(4);
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM employees");
        int rowCount = 0;
        while(resultSet.next()) {
            if (resultSet.getString("salary") == null) {
                Assertions.fail();
            }
            rowCount++;
        }
        Assertions.assertEquals(EXPECTED_ROW_COUNT,rowCount);
        closeConnection();
    }

    @DisplayName("Takes correct string if user gets an employee")
    @Test
    @ResourceLock(RESOURCE_LOCK)
    public void takesCorrectStringIfUserGetsAnEmployee(){
        DatabaseIO.readEmployeesFromFile("src/main/resources/employees.csv");
        DatabaseIO.writeEmployeeEntries(4);
        String employee = DatabaseIO.getEmployee(647173);
        Assertions.assertEquals("647173 | Mr. | Milan | F | Krawczyk | M | " +
                        "milan.krawczyk@hotmail.com | 1980-04-04 | 2012-01-19 | $123681.0"
        ,employee);
    }

    @DisplayName("Throws SQLSyntax error exception if user tries to select the table which is not available")
    @Test
    @ResourceLock(RESOURCE_LOCK)
    public void throwsSQLSyntaxErrorExceptionIfUserTriesToSelectTheTableWhichIsNotAvailable() throws SQLException {
        DatabaseIO.dropTable();
        statement = connection.createStatement();
        Assertions.assertThrows(SQLSyntaxErrorException.class, () ->
        {
            resultSet = statement.executeQuery("SELECT * FROM employees");
        });
    }

    @DisplayName("Throws SQLSyntax error exception if user tries to create same table again")
    @Test
    @ResourceLock(RESOURCE_LOCK)
    public void throwsSQLSyntaxErrorExceptionIfUserTriesToCreateSameTableAgain(){
        DatabaseIO.readEmployeesFromFile("src/main/resources/employees.csv");
        DatabaseIO.createTable();
        Assertions.assertThrows(SQLSyntaxErrorException.class, () ->
        {
            connection.prepareStatement("CREATE TABLE `employees` (`ID` VARCHAR (5) NOT NULL);").executeUpdate();
        });
    }

}
