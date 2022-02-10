package com.teamsix.employees.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DatabaseIO
{
    private static final Logger logger = LogManager.getLogger(DatabaseIO.class.getName());

    private static List<Employee> employees;

    private static PreparedStatement createTableStatement;
    private static PreparedStatement dropTableStatement;
    private static PreparedStatement selectSpecificEmployeeStatement;

    public static void readEmployeesFromFile()
    {
        EmployeeReader reader = new EmployeeReader();
        reader.setPathToReadCSVFrom("src/main/resources/employeesbig.csv");
        employees = reader.getValue();
    }

    public static void writeEmployeeEntries(int threadsToUse)
    {
        ConnectionFactory.setPooledConnections(threadsToUse);

        dropTable();
        createTable();

        generateDatabaseEntries(threadsToUse, employees);
    }

    public static void generateDatabaseEntries(int numberOfThreads, List<Employee> employeeList)
    {
        Vector<Vector<Employee>> employeesToPersist = splitListIntoChunks(numberOfThreads, employeeList);

        DatabaseEntry[] entries = new DatabaseEntry[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++)
        {
            Vector<Employee> current = new Vector<>(employeesToPersist.get(i));

            DatabaseEntry entry = new DatabaseEntry(String.valueOf(i), current);
            entries[i] = entry;
        }

        for (int i = 0; i < entries.length; i++)
        {
            DatabaseEntry entry = entries[i];
            Thread thread = new Thread(entry);
            thread.start();
        }
    }

    public static Vector<Vector<Employee>> splitListIntoChunks(int chunks, List<Employee> list)
    {
        Vector<Vector<Employee>> subSets = new Vector<>();
        int chunkSize = list.size() / chunks;

        for (int i = 0; i < chunks; i++)
        {
            if (i == 0)
            {
                int chunkFloor = 0;

                subSets.add(new Vector<>(list.subList(chunkFloor, chunkSize)));
                System.out.println("Chunk " + i + " from " + chunkFloor +" - " + chunkSize);
            }
            else if (i < chunks - 1)
            {
                int chunkFloor = (chunkSize * i);
                int chunkCeil = (chunkSize * (i + 1));

                subSets.add(new Vector<>(list.subList(chunkFloor, chunkCeil)));
                System.out.println("Chunk " + i + " from "+ chunkFloor + " - " + chunkCeil);
            }
            else
            {
                int chunkFloor = (chunkSize * i);
                int chunkCeil = list.size();

                subSets.add(new Vector<>(list.subList(chunkFloor, chunkCeil)));
                System.out.println("Chunk " + i + " from "+ chunkFloor + " - " + chunkCeil);
                System.out.println();
            }
        }

        return new Vector<>(subSets);
    }

    public static Employee getEmployee(int empID)
    {
        Connection connection = ConnectionFactory.getConnectionFromPool();

        if (selectSpecificEmployeeStatement == null)
        {
            try
            {
                selectSpecificEmployeeStatement = connection.prepareStatement("SELECT * FROM `employees`\nWHERE empID = ?;");
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        try
        {
            return new Employee(selectSpecificEmployeeStatement.executeQuery());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        ConnectionFactory.returnConnectionToPool(connection);
        connection = null;

        return null;
    }

    public static void createTable()
    {
        Connection connection = ConnectionFactory.getConnectionFromPool();

        if (createTableStatement == null)
        {
            try
            {
                StringBuilder stringBuilder = new StringBuilder("CREATE TABLE `employees` (\n  `empID` int NOT NULL,\n");
                stringBuilder.append("  `namePrefix` varchar(5) DEFAULT NULL,\n");
                stringBuilder.append("  `firstName` varchar(45) DEFAULT NULL,\n");
                stringBuilder.append("  `middleInitial` char(1) DEFAULT NULL,\n");
                stringBuilder.append("  `lastName` varchar(45) DEFAULT NULL,\n");
                stringBuilder.append("  `gender` char(1) DEFAULT NULL,\n");
                stringBuilder.append("  `email` varchar(45) DEFAULT NULL,\n");
                stringBuilder.append("  `dateOfBirth` date DEFAULT NULL,\n");
                stringBuilder.append("  `dateOfJoining` date DEFAULT NULL,\n");
                stringBuilder.append("  `salary` decimal(10,0) DEFAULT NULL,\n");
                stringBuilder.append("  PRIMARY KEY (`empID`)\n");
                stringBuilder.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\n");

                createTableStatement = connection.prepareStatement(stringBuilder.toString());
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        try
        {
            createTableStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        ConnectionFactory.returnConnectionToPool(connection);
        connection = null;
    }

    public static void dropTable()
    {
        Connection connection = ConnectionFactory.getConnectionFromPool();

        if (dropTableStatement == null)
        {
            try
            {
                dropTableStatement = connection.prepareStatement("DROP TABLE IF EXISTS `employees`;");
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        try
        {
            dropTableStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        ConnectionFactory.returnConnectionToPool(connection);
        connection = null;
    }
}
