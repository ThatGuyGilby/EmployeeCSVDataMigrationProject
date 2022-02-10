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
        ConnectionFactory.setPooledConnections(5);

        DatabaseIO.linkToSQLDatabase();
        DatabaseIO.writeEmployeeEntries(100);
//        Employee employee = DatabaseIO.getEmployee(877054);
//        System.out.println(employee);

//        System.out.println();
//        List<Employee> employees = DatabaseIO.getEmployees(new int[] {15, 13, 25});
//
//        for (int i = 0; i < employees.size(); i++)
//        {
//            Employee employeeToOutput = employees.get(i);
//            System.out.println(employeeToOutput.toString());
//        }

        //int i = Runtime.getRuntime().availableProcessors();

        //System.out.println(i);

        double endTime = System.nanoTime();
        System.out.println("Reading the CSV file provided took " + ((endTime-startTime)/1000000000) + " seconds\n");
        //ConnectionFactory.closeConnection();
    }
}
