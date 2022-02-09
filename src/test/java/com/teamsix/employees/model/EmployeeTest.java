package com.teamsix.employees.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class EmployeeTest
{
    EmployeeReader reader = new EmployeeReader();
    List<Employee> employees;

    @Test
    public void validEmailPattern()
    {
        reader.setPathToReadCSVFrom("src/main/resources/employees.csv");
        employees = reader.getValue();
        for(Employee employee : employees)
        {
            if(!employee.email.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$"))
            {
                Assertions.fail();
            }
        }
    }

    @Test
    public void validPrefixPattern()
    {
        reader.setPathToReadCSVFrom("src/main/resources/employees.csv");
        employees = reader.getValue();
        for (Employee employee : employees)
        {
            if(!employee.namePrefix.matches("^([a-zA-Z]+(.)?[\\s]*)$"))
            {
                Assertions.fail();
            }
        }
    }

    @Test
    public void validMiddleInitial()
    {
        reader.setPathToReadCSVFrom("src/main/resources/employees.csv");
        employees = reader.getValue();
        for(Employee employee : employees)
        {
            if(Character.isLowerCase(employee.middleInitial))
            {
                Assertions.fail();
            }
        }
    }
}
