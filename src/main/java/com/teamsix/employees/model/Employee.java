package com.teamsix.employees.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee
{
    public int empID;
    public String namePrefix;
    public String firstName;
    public char middleInitial;
    public String lastName;
    public char gender;
    public String email;
    public Date dateOfBirth;
    public Date dateOfJoining;
    public float salary;

    public Employee(String[] data)
    {
        this.empID = Integer.parseInt(data[0]);
        this.namePrefix = data[1];
        this.firstName = data[2];
        this.middleInitial = data[3].charAt(0);
        this.lastName = data[4];
        this.gender = data[5].charAt(0);
        this.email = data[6];

        try
        {
            this.dateOfBirth = new SimpleDateFormat("dd/MM/yyyy").parse(data[7]);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        try
        {
            this.dateOfJoining = new SimpleDateFormat("dd/MM/yyyy").parse(data[8]);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        this.salary = Integer.parseInt(data[9]);
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder("ID: ");
        stringBuilder.append(String.valueOf(empID));
        stringBuilder.append(" | ");
        stringBuilder.append(namePrefix);
        stringBuilder.append(" ");
        stringBuilder.append(firstName);
        stringBuilder.append(" ");
        stringBuilder.append(middleInitial);
        stringBuilder.append(" ");
        stringBuilder.append(lastName);
        stringBuilder.append(" | ");
        stringBuilder.append(email);
        stringBuilder.append(" | ");
        stringBuilder.append(dateOfBirth);
        stringBuilder.append(" | ");
        stringBuilder.append(dateOfJoining);
        stringBuilder.append(" | $");
        stringBuilder.append(salary);

        return stringBuilder.toString();
    }
}
