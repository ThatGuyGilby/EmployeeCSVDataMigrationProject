package com.teamsix.employees.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseIO
{
    private static Connection connection;
    private static Statement statement;
    private static EmployeeReader reader;
    private static List<Employee> employees;

    public static void linkToSQLDatabase()
    {
        reader = new EmployeeReader();
        reader.setPathToReadCSVFrom("src/main/resources/employees.csv");
        employees = reader.getValue();

        try
        {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_schema", "root", "password");

            statement = connection.createStatement();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void persistEmployees()
    {
        try
        {
            statement.executeUpdate(buildDropStatement());
            statement.executeUpdate(buildCreateStatement());

            statement.executeUpdate(buildInsertStatement(employees));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static Employee getEmployee(int empID)
    {
        try
        {
            List<Employee> employeeList = new ArrayList<>();
            String builtSQlRequest = buildSelectSpecificEmployee(empID);

            System.out.println(builtSQlRequest);

            ResultSet resultSet = statement.executeQuery(builtSQlRequest);
            resultSet.next();

            return new Employee(resultSet);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Employee> getEmployees(int[] empIDs)
    {
        try
        {
            List<Employee> employeeList = new ArrayList<>();
            String builtSQlRequest = buildSelectMultipleEmployees(empIDs);

            System.out.println(builtSQlRequest);

            ResultSet resultSet = statement.executeQuery(builtSQlRequest);

            while (resultSet.next())
            {
                employeeList.add(new Employee(resultSet));
            }

            return employeeList;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
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

    public static String buildInsertStatement(List<Employee> employeesToPersist)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO `employees` (empID, namePrefix, firstName, middleInitial, lastName, gender, email, dateOfBirth, dateOfJoining, salary)");
        stringBuilder.append("VALUES\n");

        for (int i = 0; i < employeesToPersist.size(); i++)
        {
            Employee employeeToPersist = employeesToPersist.get(i);

            if (i > 0)
            {
                stringBuilder.append((",\n"));
            }

            stringBuilder.append("(");
            stringBuilder.append(employeeToPersist.toString());
            stringBuilder.append(")");
        }

        return stringBuilder.toString();
    }

    public static String buildSelectSpecificEmployee(int empID)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM `employees`");
        stringBuilder.append("\nWHERE empID = ");
        stringBuilder.append(empID);
        stringBuilder.append(";");

        return stringBuilder.toString();
    }

    public static String buildSelectMultipleEmployees(int[] empIDs)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM `employees`");
        stringBuilder.append("\nWHERE ");

        for (int i = 0; i < empIDs.length; i++)
        {
            stringBuilder.append("empID = ");
            stringBuilder.append(empIDs[i]);

            if (i < empIDs.length - 1)
            {
                stringBuilder.append(" || ");
            }
        }

        stringBuilder.append(";");

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
