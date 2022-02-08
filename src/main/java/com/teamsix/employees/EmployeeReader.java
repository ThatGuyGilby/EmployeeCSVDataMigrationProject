package com.teamsix.employees;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeReader
{
    String pathToReadCSVFrom;

    public EmployeeReader()
    {
        pathToReadCSVFrom = "";
    }

    public void setPathToReadCSVFrom(String pathToReadCSVFrom)
    {
        this.pathToReadCSVFrom = pathToReadCSVFrom;
    }

    public List<Employee> getValue()
    {
        String line = "";
        List<Employee> employees = new ArrayList<Employee>();
        List<Employee> rejects = new ArrayList<Employee>();

        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToReadCSVFrom));
            bufferedReader.readLine(); // Get rid of the header line

            while((line = bufferedReader.readLine()) != null)
            {
                String[] values = line.replaceAll("\\s", "").split(",");
                Employee employee = new Employee(values);
                if (employeeExists(employee, employees))
                {
                    rejects.add(employee);
                }
                else
                {
                    employees.add(employee);
                }
            }

            System.out.println(employees.size());
            System.out.println(rejects.size());
            return employees;
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public boolean employeeExists(Employee employee, List<Employee> employees)
    {
        for (int i = 0; i < employees.size(); i++)
        {
            Employee thisEmployee = employees.get(i);
            if (thisEmployee.empID == employee.empID)
            {
                return true;
            }
        }

        return false;
    }
}
