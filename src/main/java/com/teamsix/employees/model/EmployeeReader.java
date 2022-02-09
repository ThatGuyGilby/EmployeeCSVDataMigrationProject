package com.teamsix.employees.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EmployeeReader
{
    public static Logger logger = LogManager.getLogger(EmployeeReader.class.getName());
    String pathToReadCSVFrom;

    public EmployeeReader()
    {
        pathToReadCSVFrom = "src/main/resources/employees.csv";
    }

    public void setPathToReadCSVFrom(String pathToReadCSVFrom)
    {
        this.pathToReadCSVFrom = pathToReadCSVFrom;
    }

    public List<Employee> getValue()
    {
        String line = "";
        List<Employee> employees = new ArrayList<>();
        List<Employee> duplicates = new ArrayList<>();
        List<Employee> emptyFields = new ArrayList<>();

        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToReadCSVFrom));
            bufferedReader.readLine(); // Get rid of the header line

            while((line = bufferedReader.readLine()) != null)
            {
                String[] values = line.replaceAll("\\s", "").split(",");
                Employee employee = new Employee(values);

                if (entryHasEmptyFields(values))
                {
                    emptyFields.add(employee);
                }
                else if (employeeExists(employee, employees))
                {
                    duplicates.add(employee);
                }
                else
                {
                    employees.add(employee);
                }
            }

            StringBuilder readerResults = new StringBuilder("EmployeeReader read results successfully\nNumber of clean records: ");
            readerResults.append(employees.size());
            readerResults.append("\nNumber of duplicate records: ");
            readerResults.append(duplicates.size());
            readerResults.append("\nNumber of records with empty fields: ");
            readerResults.append(emptyFields.size());
            logger.info(readerResults.toString());

            return employees;

        }
        catch (IOException e)
        {
            logger.error(e.toString());
        }


        return null;
    }

    public boolean employeeExists(Employee employee, List<Employee> employees)
    {
        for (Employee thisEmployee : employees) {
            if (thisEmployee.empID == employee.empID) {
                return true;
            }
        }

        return false;
    }

    public boolean entryHasEmptyFields(String[] entries)
    {
        for (String entry : entries)
        {
            if (Objects.equals(entry, ""))
            {
                return true;
            }
        }

        return false;
    }
}
