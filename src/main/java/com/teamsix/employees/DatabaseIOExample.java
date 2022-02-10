package com.teamsix.employees;

import com.teamsix.employees.model.ConnectionFactory;
import com.teamsix.employees.model.DatabaseIO;
import com.teamsix.employees.model.Employee;

import java.util.List;
import java.util.Vector;

public class DatabaseIOExample
{
    public static void main(String[] args)
    {
        double startTime = System.nanoTime();
        DatabaseIO.readEmployeesFromFile();
        double endTime = System.nanoTime();
        System.out.println("Reading the CSV file provided took " + ((endTime-startTime)/1000000000) + " seconds\n");

        DatabaseIO.writeEmployeeEntries(8);
    }
}
