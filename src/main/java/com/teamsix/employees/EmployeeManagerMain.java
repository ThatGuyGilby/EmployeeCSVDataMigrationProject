package com.teamsix.employees;

import com.teamsix.employees.model.DatabaseIO;
import com.teamsix.employees.model.Employee;

public class EmployeeManagerMain
{
    public static void main(String[] args)
    {
        DatabaseIO.linkToSQLDatabase();
        DatabaseIO.persistEmployees();
        Employee employee = DatabaseIO.getEmployee(111282);
        System.out.println(employee.toString());
        //EmployeeView.userFileInput();
    }
}
