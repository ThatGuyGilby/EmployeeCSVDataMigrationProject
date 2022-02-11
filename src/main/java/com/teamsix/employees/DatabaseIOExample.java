package com.teamsix.employees;

import com.teamsix.employees.model.DatabaseEntry;
import com.teamsix.employees.model.DatabaseIO;
import com.teamsix.employees.model.EmployeeReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class DatabaseIOExample
{
    private static Logger logger = LogManager.getLogger(EmployeeReader.class.getName());

    public static void main(String[] args)
    {
        logger.info(() -> "Reading CSV file...\n");
        double startTime = System.nanoTime();
        DatabaseIO.readEmployeesFromFile();
        double endTime = System.nanoTime();
        logger.info(() -> "Reading the CSV file provided took " + ((endTime-startTime)/1000000000) + " seconds\n");

        ArrayList<Double> executionTimes = new ArrayList<>();

        int loops = 1;

        for (int i = 0; i < loops; i++)
        {
            executionTimes.add(DatabaseIO.writeEmployeeEntries(8));
        }

        double totalTime = 0.0;
        for (int i = 0, executionTimesSize = executionTimes.size(); i < executionTimesSize; i++)
        {
            double v = executionTimes.get(i);
            totalTime += v;
        }

        double averageTime = totalTime / loops;
        double roundedAverageTime = (Math.round((averageTime)*100.0)/100.0);

        logger.info(() -> "All loops executed successfully\n\nAverage execution time of " + loops + " loops: " + roundedAverageTime + " seconds");
    }
}
