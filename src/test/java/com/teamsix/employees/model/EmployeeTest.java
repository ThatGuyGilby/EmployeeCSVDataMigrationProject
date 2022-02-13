package com.teamsix.employees.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

public class EmployeeTest
{
    EmployeeReader reader = new EmployeeReader();
    List<Employee> employees;

    @BeforeEach
    public void getValuesOfCSVForm(){
        reader.setPathToReadCSVFrom("src/main/resources/employees.csv");
        employees = reader.getValue();
    }

    @Test
    @DisplayName("Each first letter of first name is upper case")
    public void eachFirstLetterOfFirstNameIsUpperCase(){
        Assertions.assertTrue(employees.stream()
                .allMatch(employee -> Character.isUpperCase(employee.getFirstName().charAt(0))));
    }

    @Test
    @DisplayName("Each first letter of first name is upper case")
    public void eachFirstLetterOfLastNameIsUpperCase(){
        Assertions.assertTrue(employees.stream()
                .allMatch(employee -> Character.isUpperCase(employee.getLastName().charAt(0))));
    }

    @Test
    @DisplayName("Valid email pattern")
    public void validEmailPattern()
    {
        Assertions.assertTrue(employees.stream()
                .allMatch(employee -> employee.getEmail().
                        matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")));
    }

    @Test
    @DisplayName("Valid prefix pattern")
    public void validPrefixPattern() {
        Assertions.assertTrue(employees.stream()
                .allMatch(employee -> employee.getNamePrefix().
                        matches("^([a-zA-Z]+(.)?[\\s]*)$")));
    }

    @Test
    @DisplayName("Valid middle initial")
    public void validMiddleInitial() {
        Assertions.assertTrue(employees.stream()
                        .allMatch(employee -> Character.isUpperCase(employee.getMiddleInitial())));
    }

    @Test
    @DisplayName("Valid gender if it is F or M")
    public void validGenderIfItIsFOrM(){
        Assertions.assertTrue(employees.stream().allMatch(employee ->
                employee.getGender() == 'F' || employee.getGender() == 'M'));
    }

    @Test
    @DisplayName("Missing values of Ids")
    public void missingValuesOfIds(){
        Assertions.assertTrue(employees.stream().allMatch(employee -> employee.getEmpID() != 0));
    }

    @Test
    @DisplayName("Missing values of salary")
    public void missingValuesOfSalary(){
        Assertions.assertTrue(employees.stream().allMatch(employee -> employee.getSalary() > 0));
    }

    @Test
    @DisplayName("Missing values of first name")
    public void missingValuesOfFirstName(){
        Assertions.assertTrue(employees.stream().allMatch(employee -> employee.getFirstName() != null));
    }

    @Test
    @DisplayName("Missing values of last name")
    public void missingValuesOfLastName(){
        Assertions.assertTrue(employees.stream().allMatch(employee ->
                employee.getFirstName() != null));
    }

    @Test
    @DisplayName("Missing values of email")
    public void missingValuesOfEmail(){
        Assertions.assertTrue(employees.stream().allMatch(employee ->
                employee.getEmail() != null));
    }

    @Test
    @DisplayName("Missing values of date of birth")
    public void missingValuesOfDateOfBirth(){
        Assertions.assertTrue(employees.stream().allMatch(employee ->
                employee.getDateOfBirth() != null));
    }

    @Test
    @DisplayName("Missing values of date of joining")
    public void missingValuesOfDateOfJoining(){
        Assertions.assertTrue(employees.stream().allMatch(employee ->
                employee.getDateOfJoining() != null));
    }
}
