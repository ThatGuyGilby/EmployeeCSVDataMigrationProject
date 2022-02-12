package com.teamsix.employees.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.stream.IntStream;

public class DatabaseIO
{
    private static final Logger logger = LogManager.getLogger(DatabaseIO.class.getName());

    private static ArrayList<Employee> employees;

    private static PreparedStatement createTableStatement;
    private static PreparedStatement dropTableStatement;
    private static PreparedStatement selectSpecificEmployeeStatement;

    public static ArrayList<Thread> getThreads() {
        return threads;
    }

    private static ArrayList<Thread> threads;

    private static Vector<Double> resultsCache;

    public static ArrayList<Employee> readEmployeesFromFile(String path)
    {
        EmployeeReader reader = new EmployeeReader();
        reader.setPathToReadCSVFrom(path);
        employees = reader.getValue();

        return employees;
    }

    public static double writeEmployeeEntries(int threadsToUse)
    {
        ConnectionFactory.setPooledConnections(threadsToUse);

        dropTable();
        createTable();

        return generateDatabaseEntries(threadsToUse, employees);
    }

    public static double generateDatabaseEntries(int numberOfThreads, ArrayList<Employee> employeeList)
    {
        ArrayList<ArrayList<Employee>> employeesToPersist = splitListIntoChunks(numberOfThreads, employeeList);

        resultsCache = new Vector<>();

        DatabaseEntry[] entries = new DatabaseEntry[numberOfThreads];
        IntStream.range(0, numberOfThreads).forEachOrdered(i -> {
            ArrayList<Employee> current = new ArrayList<>(employeesToPersist.get(i));
            DatabaseEntry entry = new DatabaseEntry(String.valueOf(i), current);
            entries[i] = entry;
        });

        threads = new ArrayList<>();
        for (int i = 0, entriesLength = entries.length; i < entriesLength; i++)
        {
            DatabaseEntry entry = entries[i];
            Thread thread = new Thread(entry);
            thread.start();
            threads.add(thread);
        }

        while (threads.size() > resultsCache.size())
        {
            System.out.print("");
        }

        double totalTime = IntStream.range(0, numberOfThreads).mapToDouble(i -> resultsCache.get(i)).sum();
        double averageTime = totalTime / numberOfThreads;
        double roundedAverageTime = (Math.round((averageTime)*100.0)/100.0);

        logger.info(() -> "All threads executed successfully\n\nAverage execution time: " + roundedAverageTime + " seconds\n");

        return roundedAverageTime;
    }

    public static void sendResult(double result)
    {
        resultsCache.add(result);
    }

    public static ArrayList<ArrayList<Employee>> splitListIntoChunks(int chunks, ArrayList<Employee> list)
    {
        ArrayList<ArrayList<Employee>> subSets = new ArrayList<>();
        int chunkSize = list.size() / chunks;

        IntStream.range(0, chunks).forEachOrdered(i -> {
            if (i == 0) {
                int chunkFloor = 0;

                subSets.add(new ArrayList<>(list.subList(chunkFloor, chunkSize)));
                logger.info(() -> "Chunk " + i + " from " + chunkFloor + " - " + chunkSize);
            } else if (i < chunks - 1) {
                int chunkFloor = (chunkSize * i);
                int chunkCeil = (chunkSize * (i + 1));

                subSets.add(new ArrayList<>(list.subList(chunkFloor, chunkCeil)));
                logger.info(() -> "Chunk " + i + " from " + chunkFloor + " - " + chunkCeil);
            } else {
                int chunkFloor = (chunkSize * i);
                int chunkCeil = list.size();

                subSets.add(new ArrayList<>(list.subList(chunkFloor, chunkCeil)));
                logger.info(() -> "Chunk " + i + " from " + chunkFloor + " - " + chunkCeil + "\n");
            }
        });

        return subSets;
    }

    public static String getEmployee(int empID)
    {
        Connection connection = ConnectionFactory.getConnectionFromPool();
        ResultSet rs;

        if (selectSpecificEmployeeStatement == null)
        {
            try
            {
                String sql = "SELECT * FROM employees WHERE empID = ?;";
                selectSpecificEmployeeStatement = connection.prepareStatement(sql);
                selectSpecificEmployeeStatement.setInt(1, empID);
                rs = selectSpecificEmployeeStatement.executeQuery();


                if (rs.next()) {

                    return rs.getInt("empID")
                            + " | " + rs.getString("namePrefix")
                            + " | " + rs.getString("firstName")
                            + " | " + rs.getString("middleInitial")
                            + " | " + rs.getString("lastName")
                            + " | " + rs.getString("gender")
                            + " | " + rs.getString("email")
                            + " | " + rs.getDate("dateOfBirth")
                            + " | " + rs.getDate("dateOfJoining")
                            + " | $" + rs.getFloat("salary");
                }
                else
                    return "A user was not found with that Employee ID";
            }
            catch (SQLException e)
            {
                logger.error(() -> e.toString());
            }
        }


        ConnectionFactory.returnConnectionToPool(connection);

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
                logger.error(() -> e.toString());
            }
        }

        try
        {
            createTableStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            logger.error(() -> e.toString());
        }

        ConnectionFactory.returnConnectionToPool(connection);
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
                logger.error(() -> e.toString());
            }
        }

        try
        {
            dropTableStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            logger.error(() -> e.toString());
        }

        ConnectionFactory.returnConnectionToPool(connection);
    }
}
