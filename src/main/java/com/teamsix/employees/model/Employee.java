package com.teamsix.employees.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Employee
{
    // these variables need to be made private but they are used in tests,
    // just add a getter and switch the usage in tese code
    public int empID;
    public String namePrefix;
    public String firstName;
    public char middleInitial;
    public String lastName;
    public char gender;
    public String email;
    public java.sql.Date dateOfBirth;
    public java.sql.Date dateOfJoining;
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
            String strDate = data[7];

            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            java.util.Date utilDate = sdf.parse(strDate);
            this.dateOfBirth = new java.sql.Date(utilDate.getTime());
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        try
        {
            String strDate = data[8];

            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            java.util.Date utilDate = sdf.parse(strDate);
            this.dateOfJoining = new java.sql.Date(utilDate.getTime());
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
        StringBuilder stringBuilder = new StringBuilder("\"");
        stringBuilder.append(String.valueOf(empID));
        stringBuilder.append("\" , \"");
        stringBuilder.append(namePrefix);
        stringBuilder.append("\" , \"");
        stringBuilder.append(firstName);
        stringBuilder.append("\" , \"");
        stringBuilder.append(middleInitial);
        stringBuilder.append("\" , \"");
        stringBuilder.append(lastName);
        stringBuilder.append("\" , \"");
        stringBuilder.append(gender);
        stringBuilder.append("\" , \"");
        stringBuilder.append(email);
        stringBuilder.append("\" , \"");
        stringBuilder.append(dateOfBirth);
        stringBuilder.append("\" , \"");
        stringBuilder.append(dateOfJoining);
        stringBuilder.append("\" , \"");
        stringBuilder.append(salary);
        stringBuilder.append("\"");

        return stringBuilder.toString();
    }
}
