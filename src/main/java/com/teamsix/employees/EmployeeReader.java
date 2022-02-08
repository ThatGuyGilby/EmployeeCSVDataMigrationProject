package com.teamsix.employees;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeReader
{
    public static Logger logger = LogManager.getLogger(EmployeeReader.class.getName());
    String pathToReadCSVFrom;

    List<Employee> cleanEntries;
    List<Employee> duplicateEntries;
    List<Employee> invalidEntries;

    public EmployeeReader()
    {
        pathToReadCSVFrom = "";

        cleanEntries = new ArrayList<>();
        duplicateEntries = new ArrayList<>();
        invalidEntries = new ArrayList<>();
    }

    public void setPathToReadCSVFrom(String pathToReadCSVFrom)
    {
        this.pathToReadCSVFrom = pathToReadCSVFrom;
    }

    public List<Employee> getValue()
    {
        String line = "";
        cleanEntries = new ArrayList<>();
        duplicateEntries = new ArrayList<>();
        invalidEntries = new ArrayList<>();

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
                    invalidEntries.add(employee);
                }
                else if (employeeExists(employee, cleanEntries))
                {
                    duplicateEntries.add(employee);
                }
                else
                {
                    cleanEntries.add(employee);
                }
            }

            StringBuilder readerResults = new StringBuilder("EmployeeReader read results successfully\nNumber of clean records: ");
            readerResults.append(cleanEntries.size());
            readerResults.append("\nNumber of duplicate records: ");
            readerResults.append(duplicateEntries.size());
            readerResults.append("\nNumber of records with empty fields: ");
            readerResults.append(invalidEntries.size());
            readerResults.append("\n\nDuplicate Result\n");

            for (int i = 0; i < duplicateEntries.size(); i++)
            {
                readerResults.append(duplicateEntries.get(i));
            }

            logger.info(readerResults.toString());

            return cleanEntries;
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

    public boolean entryHasEmptyFields(String[] entries)
    {
        for (String entry : entries)
        {
            if (entry == "")
            {
                return true;
            }
        }

        return false;
    }
}
