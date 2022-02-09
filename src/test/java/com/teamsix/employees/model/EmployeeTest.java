package com.teamsix.employees.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class EmployeeTest
{
    EmployeeReader reader = new EmployeeReader();
    List<Employee> employees;

    @Test
    @DisplayName("Valid email pattern")
    public void validEmailPattern()
    {
        reader.setPathToReadCSVFrom("src/main/resources/employees.csv");
        employees = reader.getValue();
        for(Employee employee : employees)
        {
            if(!employee.getEmail().matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$"))
            {
                Assertions.fail();
            }
        }
    }

    @Test
    @DisplayName("Valid prefix pattern")
    public void validPrefixPattern()
    {
        reader.setPathToReadCSVFrom("src/main/resources/employees.csv");
        employees = reader.getValue();
        for (Employee employee : employees)
        {
            if(!employee.getNamePrefix().matches("^([a-zA-Z]+(.)?[\\s]*)$"))
            {
                Assertions.fail();
            }
        }
    }

    @Test
    @DisplayName("Valid middle initial")
    public void validMiddleInitial()
    {
        reader.setPathToReadCSVFrom("src/main/resources/employees.csv");
        employees = reader.getValue();
        for(Employee employee : employees)
        {
            if(Character.isLowerCase(employee.getMiddleInitial()))
            {
                Assertions.fail();
            }
        }
    }

    @Test
    @DisplayName("Valid gender if it is F or M")
    public void validGenderIfItIsFOrM(){
        reader.setPathToReadCSVFrom("src/main/resources/employees.csv");
        employees = reader.getValue();
        for(Employee employee : employees)
        {
            if(!(employee.getGender() == 'F' || employee.getGender() == 'M'))
            {
                Assertions.fail();
            }
        }
    }

    @Test
    @DisplayName("Missing values of Ids")
    public void missingValuesOfIds(){
        reader.setPathToReadCSVFrom("src/main/resources/employees.csv");
        employees = reader.getValue();
        for (Employee employee : employees){
            if(employee.getEmpID()== 0){
                Assertions.fail();
            }
        }
    }

    @Test
    @DisplayName("Missing values of salary")
    public void missingValuesOfSalary(){
        reader.setPathToReadCSVFrom("src/main/resources/employees.csv");
        employees = reader.getValue();
        for (Employee employee : employees){
            if(employee.getSalary() == 0){
                Assertions.fail();
            }
        }
    }

    @Test
    @DisplayName("Missing values of first name")
    public void missingValuesOfFirstName(){
        reader.setPathToReadCSVFrom("src/main/resources/employees.csv");
        employees = reader.getValue();
        for(Employee employee : employees){
            if (employee.getFirstName() == null){
                Assertions.fail();
            }
        }
    }

    @Test
    @DisplayName("Missing values of last name")
    public void missingValuesOfLastName(){
        reader.setPathToReadCSVFrom("src/main/resources/employees.csv");
        employees = reader.getValue();
        for(Employee employee : employees){
            if(employee.getLastName() == null){
                Assertions.fail();
            }
        }
    }

    @Test
    @DisplayName("Missing values of email")
    public void missingValuesOfEmail(){
        reader.setPathToReadCSVFrom("src/main/resources/employees.csv");
        employees = reader.getValue();
        for(Employee employee : employees){
            if(employee.getLastName() == null){
                Assertions.fail();
            }
        }
    }

    @Test
    @DisplayName("Missing values of date of birth")
    public void missingValuesOfDateOfBirth(){
        reader.setPathToReadCSVFrom("src/main/resources/employees.csv");
        employees = reader.getValue();
        for(Employee employee : employees){
            if(employee.getDateOfBirth() == null){
                Assertions.fail();
            }
        }
    }

    @Test
    @DisplayName("Missing values of date of joining")
    public void missingValuesOfDateOfJoining(){
        reader.setPathToReadCSVFrom("src/main/resources/employees.csv");
        employees = reader.getValue();
        for(Employee employee : employees){
            if(employee.getDateOfJoining() == null){
                Assertions.fail();
            }
        }
    }
}
