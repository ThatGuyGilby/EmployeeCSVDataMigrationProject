package com.teamsix.employees.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeViewTest {
    EmployeeView ev;

    @BeforeEach
    public void beforeEach(){
        ev = new EmployeeView();
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
}