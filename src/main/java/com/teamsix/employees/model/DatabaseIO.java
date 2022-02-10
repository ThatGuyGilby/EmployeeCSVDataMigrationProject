package com.teamsix.employees.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class DatabaseIO
{
    private static Logger logger = LogManager.getLogger(DatabaseIO.class.getName());

    private static List<Employee> employees;

    public static void linkToSQLDatabase()
    {
        ReadEmployeesFromFile();

        try
        {
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
        }
        catch (SQLException e)
        {
            logger.error(e.toString());
        }
    }

    private static void ReadEmployeesFromFile()
    {
        EmployeeReader reader = new EmployeeReader();
        reader.setPathToReadCSVFrom("src/main/resources/employeesbig.csv");
        employees = reader.getValue();
    }

    public static void writeEmployeeEntries()
    {
        try
        {
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();

            statement.executeUpdate(buildDropStatement());
            statement.executeUpdate(buildCreateStatement());

            generateDatabaseEntries(4, employees);
        }
        catch (SQLException e)
        {
            logger.error(e.toString());
        }
    }

    public static void generateDatabaseEntries(int numberOfThreads, List<Employee> employeeList)
    {
        ArrayList<List<Employee>> employeesToPersist = splitListIntoChunks(numberOfThreads, employeeList);

        DatabaseEntry[] entries = new DatabaseEntry[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++)
        {
            DatabaseEntry entry = new DatabaseEntry(String.valueOf(i), employeesToPersist.get(i));
            entries[i] = entry;
        }

        for (DatabaseEntry entry : entries)
        {
            entry.start();
        }
    }

    public static ArrayList<List<Employee>> splitListIntoChunks(int chunks, List<Employee> list)
    {
        ArrayList<List<Employee>> subSets = new ArrayList<>();
        int chunkSize = list.size() / chunks;

        for (int i = 0; i < chunks; i++)
        {
            if (i == 0)
            {
                int chunkFloor = 0;
                int chunkCeil = chunkSize;

                subSets.add(list.subList(chunkFloor, chunkCeil));
                System.out.println("Chunk " + i + " from " + chunkFloor +" - " + chunkCeil);
            }
            else if (i < chunks - 1)
            {
                int chunkFloor = (chunkSize * i);
                int chunkCeil = (chunkSize * (i + 1));

                subSets.add(list.subList(chunkFloor, chunkCeil));
                System.out.println("Chunk " + i + " from "+ chunkFloor + " - " + chunkCeil);
            }
            else
            {
                int chunkFloor = (chunkSize * i);
                int chunkCeil = list.size();

                subSets.add(list.subList(chunkFloor , chunkCeil));;
                System.out.println("Chunk " + i + " from "+ chunkFloor + " - " + chunkCeil);
                System.out.println("");
            }
        }

        return subSets;
    }

    public static Employee getEmployee(int empID)
    {
        try
        {
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();

            List<Employee> employeeList = new ArrayList<>();
            String builtSQlRequest = buildSelectSpecificEmployee(empID);

            System.out.println(builtSQlRequest);

            ResultSet resultSet = statement.executeQuery(builtSQlRequest);
            resultSet.next();
            Employee employeeToReturn = new Employee(resultSet);

            resultSet.close();

            return employeeToReturn;
        }
        catch (SQLException e)
        {
            logger.error(e.toString());
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

    public static void logExecutionTime(long startTime)
    {
        long endTime = System.nanoTime();
        long executionNanoTime = endTime - startTime;
        StringBuilder stringBuilder = new StringBuilder("Execution time: ");
        stringBuilder.append(executionNanoTime);
        stringBuilder.append("ns | ");
        stringBuilder.append(executionNanoTime * 0.000000001);
        stringBuilder.append("s");
        logger.info(stringBuilder.toString());
    }
}
