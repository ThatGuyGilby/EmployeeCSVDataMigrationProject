package com.teamsix.employees.view;

import com.teamsix.employees.model.ConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Objects;
import java.util.Scanner;

public class EmployeeView
{
    private static final Logger logger = LogManager.getLogger(ConnectionFactory.class.getName());

    public static String takeFileInput()
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
        }
        catch (IndexOutOfBoundsException e)
        {
            logger.error(() -> e.toString());
        }

        return fileName;
    }

    public static int takeThreadInput()
    {
        System.out.print("Please enter the number of threads you want to use -> ");
        Scanner scanner = new Scanner(System.in);

        String string = scanner.next();

        Integer input = Integer.valueOf((string).replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", ""));

        input = Math.max(1, input);

        return input;
    }

    public int getUserThreadCount()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please specify a number of Threads that you wish to use..");
        System.out.println("---> ");

        int i = 8;

        if (scanner.hasNextInt()){
            int number = scanner.nextInt();
            if (number > 0){
                i = number;
            }

        if (scanner.hasNext())
        {
            i = scanner.nextInt();
        }
        }
        return i;

    }

    public static boolean doYouWishToSearchTheDatabaseForAUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to search for a user in the database with their Employee ID?");
        if (scanner.next().equalsIgnoreCase("yes")||scanner.next().equalsIgnoreCase("Yeah")||
                scanner.next().equalsIgnoreCase("Y")||scanner.next().equalsIgnoreCase("Yup")||
                scanner.next().equalsIgnoreCase("Aye")){
            return true;
        }
        else
            return false;

    }
}
