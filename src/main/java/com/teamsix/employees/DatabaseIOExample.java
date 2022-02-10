package com.teamsix.employees;

import com.teamsix.employees.model.ConnectionFactory;
import com.teamsix.employees.model.DatabaseIO;
import com.teamsix.employees.model.Employee;

import java.util.List;

public class DatabaseIOExample
{
    public static void main(String[] args)
    {
        double startTime = System.nanoTime();
        DatabaseIO.linkToSQLDatabase();
        DatabaseIO.writeEmployeeEntries();

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

        double endTime = System.nanoTime();
        System.out.println("This operation took " + ((endTime-startTime)/1000000000) + " seconds");
        ConnectionFactory.closeConnection();
    }
}
