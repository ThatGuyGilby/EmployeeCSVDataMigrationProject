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

}