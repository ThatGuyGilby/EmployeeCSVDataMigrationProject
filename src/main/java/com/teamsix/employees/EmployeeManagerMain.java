package com.teamsix.employees;

import com.teamsix.employees.model.Employee;
import com.teamsix.employees.model.EmployeeReader;

import java.sql.*;
import java.util.List;

public class EmployeeManagerMain
{
    public static void main(String[] args)
    {
        linkToSQLDatabase();
        //EmployeeView.userFileInput();
    }

    public static void linkToSQLDatabase()
    {
        EmployeeReader reader = new EmployeeReader();
        reader.setPathToReadCSVFrom("src/main/resources/employees.csv");
        List<Employee> employees = reader.getValue();

        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_schema", "root", "password");

            Statement statement = connection.createStatement();

            statement.executeUpdate(buildDropStatement());
            statement.executeUpdate(buildCreateStatement());

            for (Employee employee : employees)
            {
                statement.executeUpdate(buildInsertStatement(employee));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static String buildInsertStatement(Employee employee)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO `employees` (empID, namePrefix, firstName, middleInitial, lastName, gender, email, dateOfBirth, dateOfJoining, salary)");
        stringBuilder.append("VALUES\n");

        stringBuilder.append("(");
        stringBuilder.append(employee.toString());
        stringBuilder.append(")");

        return stringBuilder.toString();
    }

    public static String buildCreateStatement()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(
                "CREATE TABLE `employees` (\n" +
                "  `empID` int NOT NULL,\n" +
                "  `namePrefix` varchar(5) DEFAULT NULL,\n" +
                "  `firstName` varchar(45) DEFAULT NULL,\n" +
                "  `middleInitial` char(1) DEFAULT NULL,\n" +
                "  `lastName` varchar(45) DEFAULT NULL,\n" +
                "  `gender` char(1) DEFAULT NULL,\n" +
                "  `email` varchar(45) DEFAULT NULL,\n" +
                "  `dateOfBirth` date DEFAULT NULL,\n" +
                "  `dateOfJoining` date DEFAULT NULL,\n" +
                "  `salary` decimal(10,0) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`empID`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\n"
        );

        return  stringBuilder.toString();
    }

    public static String buildDropStatement()
{
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("DROP TABLE IF EXISTS `employees`;");

    return stringBuilder.toString();
}
}
