package com.teamsix.employees;

import java.io.*;

public class EmployeeReader
{
    public static void main(String[] args)
    {
        ReadCSVFromPath("E://AdmiralJava-main/EmployeeCSV/project-docs/EmployeeRecords.csv");
    }

    public static void ReadCSVFromPath(String pathToReadCSVFrom)
    {
        String  line = "";

        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToReadCSVFrom));
            bufferedReader.readLine(); // Get rid of the header line

            while((line = bufferedReader.readLine()) != null)
            {
                String[] values = line.replaceAll("\\s", "").split(",");
                Employee employee = new Employee(values);
                System.out.println(employee.toString());
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
