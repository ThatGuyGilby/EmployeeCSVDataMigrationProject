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

        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToReadCSVFrom));
            bufferedReader.readLine(); // Get rid of the header line

            while((line = bufferedReader.readLine()) != null)
            {
                String[] values = line.replaceAll("\\s", "").split(",");
                Employee employee = new Employee(values);
                employees.add(employee);
            }
            return employees;
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
