package com.teamsix.employees.model;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



import java.util.ArrayList;
import java.util.List;
public class EmployeeReaderTest {

    @Test
    @DisplayName("Test 5 employee records with no same ID")
    public void testFiveEmployeeRecordsWithNoSameID(){
        EmployeeReader employeeReader = new EmployeeReader();
        employeeReader.setPathToReadCSVFrom("unitTestCsv/test0duplicate.csv");
        employeeReader.getValue();
        int expectedEmployeeOutput = 5;

        Assertions.assertEquals(expectedEmployeeOutput,employeeReader.getValue().size());
    }

    @Test
    @DisplayName("Test 10 employee records with 2 same ID")
    public void testTenEmployeeRecordsWithTwoSameID(){

        EmployeeReader employeeReader = new EmployeeReader();
        employeeReader.setPathToReadCSVFrom("unitTestCsv/test1duplicate.csv");
        employeeReader.getValue();
        int expectedEmployeeOutput = 9;
        int expectedRejectsEmployees = 1;

        Assertions.assertEquals(expectedEmployeeOutput,employeeReader.getValue().size());

    }

    @Test
    @DisplayName("Test 10 employee records with 10 same ID")
    public void testTenEmployeeRecordsWithTenSameID(){

        EmployeeReader employeeReader = new EmployeeReader();
        employeeReader.setPathToReadCSVFrom("unitTestCsv/test10duplicate.csv");
        employeeReader.getValue();
        int expectedEmployeeOutput = 1;
        int expectedRejectsEmployees = 9;

        Assertions.assertEquals(expectedEmployeeOutput,employeeReader.getValue().size());
    }

}
