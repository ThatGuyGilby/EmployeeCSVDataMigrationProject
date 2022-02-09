package com.teamsix.employees;

import com.teamsix.employees.model.DatabaseIO;
import com.teamsix.employees.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeManagerMain
{
    public static void main(String[] args)
    {
        DatabaseIO.linkToSQLDatabase();
        DatabaseIO.persistEmployees();

        Employee employee = DatabaseIO.getEmployee(111282);

        List<Employee> employees = DatabaseIO.getEmployees(new int[] {111282, 111498, 114311});

        for (int i = 0; i < employees.size(); i++)
        {
            Employee employeeToOutput = employees.get(i);
            System.out.println(employeeToOutput.toString());
        }

        //EmployeeView.userFileInput();
    }
}
