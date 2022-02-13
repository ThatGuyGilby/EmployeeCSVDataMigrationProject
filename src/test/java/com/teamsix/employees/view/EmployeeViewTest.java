package com.teamsix.employees.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeViewTest {
    EmployeeView ev;

    @BeforeEach
    public void beforeEach(){
        ev = new EmployeeView();
    }

    @Test
    @DisplayName("Given the path to a valid and available csv file, return the pathName")
    public void givenThePathToAValidAndAvailableCsvFileReturnThePathname(){
        String input = "src/main/resources/employees.csv";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertEquals("src/main/resources/employees.csv", EmployeeView.takeFileInput());
    }

    @Test
    @DisplayName("When a number is passed or entered, that number should be returned")
    public void whenANumberIsPassedOrEnteredThatNumberShouldBeReturned(){
        String input = "5";
        InputStream in = new ByteArrayInputStream(input.getBytes());

        System.setIn(in);

        assertEquals(5, ev.getUserThreadCount());
    }

    @Test
    @DisplayName("When a number below Zero is passed, return the default 8 threads")
    public void whenANumberBelowZeroIsPassedReturnTheDefault8Threads(){
        String input = "-1";
        InputStream in = new ByteArrayInputStream(input.getBytes());

        System.setIn(in);

        assertEquals(8, ev.getUserThreadCount());
    }

    @Test
    @DisplayName("When passed a non-Int number, return the value of 8")
    public void whenPassedANonIntNumberReturnTheValueOf8(){
        String input = "5.6";
        InputStream in = new ByteArrayInputStream(input.getBytes());

        System.setIn(in);

        assertEquals(8, ev.getUserThreadCount());
    }

    @Test
    @DisplayName("If the user passes a String into the method, return the default value of 8")
    public void ifTheUserPassesAStringIntoTheMethodReturnTheDefaultValueOf8(){
        String input = "This is a proper String, not like those other String number conflicting types";
        InputStream in = new ByteArrayInputStream(input.getBytes());

        System.setIn(in);

        assertEquals(8, ev.getUserThreadCount());
    }

    @Test
    @DisplayName("When supplied with a Yes, return true")
    public void whenSuppliedWithAUpperCaseYesReturnTrue(){
        String input = "Yes";
        InputStream in = new ByteArrayInputStream(input.getBytes());

        System.setIn(in);

        assertTrue(EmployeeView.doYouWishToSearchTheDatabaseForAUser());
    }
    @Test
    @DisplayName("When supplied with a yes, return true")
    public void whenSuppliedWithALowerCaseYesReturnTrue(){
        String input = "yes";
        InputStream in = new ByteArrayInputStream(input.getBytes());

        System.setIn(in);

        assertTrue(EmployeeView.doYouWishToSearchTheDatabaseForAUser());
    }

    @Test
    @DisplayName("When supplied with a No, return false")
    public void whenSuppliedWithAnUpperCaseNoReturnFalse(){
        String input = "No";
        InputStream in = new ByteArrayInputStream(input.getBytes());

        System.setIn(in);

        assertFalse(EmployeeView.doYouWishToSearchTheDatabaseForAUser());
    }
    @Test
    @DisplayName("When supplied with a no, return false")
    public void whenSuppliedWithALowerCaseNoReturnFalse(){
        String input = "No";
        InputStream in = new ByteArrayInputStream(input.getBytes());

        System.setIn(in);

        assertFalse(EmployeeView.doYouWishToSearchTheDatabaseForAUser());
    }
    @Test
    @DisplayName("When supplied with an Integer, return false")
    public void whenSuppliedWithAnIntegerReturnFalse(){
        String input = "0";
        InputStream in = new ByteArrayInputStream(input.getBytes());

        System.setIn(in);

        assertFalse(EmployeeView.doYouWishToSearchTheDatabaseForAUser());
    }
}