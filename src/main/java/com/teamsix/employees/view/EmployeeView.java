package com.teamsix.employees.view;

import com.teamsix.employees.model.EmployeeReader;

import java.io.File;
import java.util.Objects;
import java.util.Scanner;

public class EmployeeView
{
    public static void userFileInput()
    {
        System.out.print("Please enter a valid filepath -> ");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.next();
        File f = new File(fileName);
        try
        {
            String[] fileCheck = fileName.split("\\.");

            if (!Objects.equals(fileCheck[1], "csv"))
            {
                System.out.println("The submitted file does not pass the checks");
                System.exit(0);
            }
            else if (!f.exists() && !f.isDirectory())
            {
                System.out.println("Sorry that file doesn't exist");
            }

            else
            {
                EmployeeReader er = new EmployeeReader();
                er.setPathToReadCSVFrom(fileName);
                er.getValue();
            }
        }
        catch (IndexOutOfBoundsException e)
        {
            e.printStackTrace();
            System.out.println("");
        }
    }

    public int getUserThreadCount(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please specify a number of Threads that you wish to use..");
        System.out.println("---> ");

        int i = 0;

        if (scanner.hasNext()){
            i = scanner.nextInt();
        }

        return i;
    }
}
