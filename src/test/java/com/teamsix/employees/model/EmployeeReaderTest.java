package com.teamsix.employees.model;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EmployeeReaderTest {

    @Test
    @DisplayName("Check correct output: Test 5 employee records with no same ID")
    public void testFiveEmployeeRecordsWithNoSameID(){
        EmployeeReader employeeReader = new EmployeeReader();
        employeeReader.setPathToReadCSVFrom("unitTestCsv/test0duplicate.csv");
        employeeReader.getValue();

        int expectedEmployeeOutput = 5;
        int actualDuplicates = employeeReader.getDuplicates().size();
        int expectedDuplicates = 0;

        int expectedEmptyField = 0;
        int actualEmptyField = employeeReader.getEmptyFields().size();

        Assertions.assertEquals(expectedEmployeeOutput,employeeReader.getValue().size());
        Assertions.assertEquals(expectedDuplicates,actualDuplicates);
        Assertions.assertEquals(expectedEmptyField,actualEmptyField);
    }

    @Test
    @DisplayName("Check correct output: Test 10 employee records with 2 same ID")
    public void testTenEmployeeRecordsWithTwoSameID(){

        EmployeeReader employeeReader = new EmployeeReader();
        employeeReader.setPathToReadCSVFrom("unitTestCsv/test1duplicate.csv");
        employeeReader.getValue();

        int expectedEmployeeOutput = 9;
        int actualDuplicates = employeeReader.getDuplicates().size();
        int expectedDuplicates = 1;

        int expectedEmptyField = 0;
        int actualEmptyField = employeeReader.getEmptyFields().size();

        Assertions.assertEquals(expectedEmployeeOutput,employeeReader.getValue().size());
        Assertions.assertEquals(expectedDuplicates,actualDuplicates);
        Assertions.assertEquals(expectedEmptyField,actualEmptyField);
    }

    @Test
    @DisplayName("Check correct output: Test 10 employee records with 10 same ID")
    public void testTenEmployeeRecordsWithTenSameID(){

        EmployeeReader employeeReader = new EmployeeReader();
        employeeReader.setPathToReadCSVFrom("unitTestCsv/test10duplicate.csv");
        employeeReader.getValue();

        int expectedEmployeeOutput = 1;
        int actualDuplicates = employeeReader.getDuplicates().size();
        int expectedDuplicates = 9;

        int expectedEmptyField = 0;
        int actualEmptyField = employeeReader.getEmptyFields().size();

        Assertions.assertEquals(expectedEmployeeOutput,employeeReader.getValue().size());
        Assertions.assertEquals(expectedDuplicates,actualDuplicates);
        Assertions.assertEquals(expectedEmptyField,actualEmptyField);
    }

    @Test
    @DisplayName("Check correct output: Test 5 unique employee records with 1 empty string field")
    public void testFiveUniqueEmployeeRecordsWithOneEmptyStringField(){

        EmployeeReader employeeReader = new EmployeeReader();
        employeeReader.setPathToReadCSVFrom("unitTestCsv/test1FieldEmpty.csv");
        employeeReader.getValue();

        int expectedEmployeeOutput = 4;
        int actualDuplicates = employeeReader.getDuplicates().size();
        int expectedDuplicates = 0;

        int expectedEmptyField = 1;
        int actualEmptyField = employeeReader.getEmptyFields().size();


        Assertions.assertEquals(expectedEmployeeOutput,employeeReader.getValue().size());
        Assertions.assertEquals(expectedDuplicates,actualDuplicates);
        Assertions.assertEquals(expectedEmptyField,actualEmptyField);
    }

    @Test
    @DisplayName("Check correct output: Test 5 unique employee records with empty string field in each row included space bar only")
    public void testFiveUniqueEmployeeRecordsWithEmptyStringFieldEachRowIncludedSpaceBarOnly(){

        EmployeeReader employeeReader = new EmployeeReader();
        employeeReader.setPathToReadCSVFrom("unitTestCsv/test5FieldEmpty.csv");
        employeeReader.getValue();

        int expectedEmployeeOutput = 0;
        int actualDuplicates = employeeReader.getDuplicates().size();
        int expectedDuplicates = 0;

        int expectedEmptyField = 5;
        int actualEmptyField = employeeReader.getEmptyFields().size();


        Assertions.assertEquals(expectedEmployeeOutput,employeeReader.getValue().size());
        Assertions.assertEquals(expectedDuplicates,actualDuplicates);
        Assertions.assertEquals(expectedEmptyField,actualEmptyField);
    }

}
