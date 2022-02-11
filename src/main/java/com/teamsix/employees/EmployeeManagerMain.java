package com.teamsix.employees;

import com.teamsix.employees.model.DatabaseIO;
import com.teamsix.employees.model.Employee;
import com.teamsix.employees.model.EmployeeReader;
import com.teamsix.employees.view.EmployeeView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class EmployeeManagerMain
{
    private static Logger logger = LogManager.getLogger(EmployeeReader.class.getName());

    public static void main(String[] args)
    {
        String path = EmployeeView.takeFileInput();
        int threadsToRun = EmployeeView.takeThreadInput();

        logger.info(() -> new StringBuilder().append("Beginning read/write cycle...\n\nFile: ").append(path).append("\nThreads: ").append(threadsToRun).append("\n").toString());

        logger.info(() -> "Reading CSV file...\n");
        double startTime = System.nanoTime();
        ArrayList<Employee> employees = DatabaseIO.readEmployeesFromFile(path);
        double timeTakenToReadCSV = (Math.round(((System.nanoTime() - startTime)/1000000000)*100.0)/100.0);
        logger.info(() -> new StringBuilder().append("Reading took ").append(timeTakenToReadCSV).append(" seconds\n").toString());

        ArrayList<Double> executionTimes = new ArrayList<>();

        int loops = 1;

        for (int i = 0; i < loops; i++)
        {
            executionTimes.add(DatabaseIO.writeEmployeeEntries(threadsToRun));
        }

        double totalTime = 0.0;
        for (int i = 0, executionTimesSize = executionTimes.size(); i < executionTimesSize; i++)
        {
            double v = executionTimes.get(i);
            totalTime += v;
        }

        double averageTime = totalTime / loops;
        double roundedAverageTime = (Math.round((averageTime)*100.0)/100.0);

        logger.info(() -> new StringBuilder()
                .append("All loops executed successfully\n\nAverage execution time of ")
                .append(loops)
                .append(" loops: ")
                .append(roundedAverageTime)
                .append(" seconds")
                .toString());

        try
        {
            nameLogFile(path, threadsToRun);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        doSearch();
    }

    private static void doSearch() {
        boolean doSearch = EmployeeView.doYouWishToSearchTheDatabaseForAUser();

        if (doSearch){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter an employee number");
            if (scanner.hasNextInt()){
                int employeeID = scanner.nextInt();
                if (employeeID > 0){
                    System.out.println(DatabaseIO.getEmployee(employeeID));
                }
            }

        }
    }

    public static void nameLogFile(String name, int threads) throws IOException
    {
        String logFolder = "src/logs/";

        String[] filteredNames = name.replaceAll("\\.", "/").split("/");

        String createdName = filteredNames[filteredNames.length-2] + "_t" + threads;

        createdName = new StringBuilder().append(logFolder).append(createdName).toString();

        File file = new File("logfile.log");
        String finalFileName;

        int i = 0;
        while (true)
        {
            finalFileName = new StringBuilder().append(createdName).append("(").append(i).append(")").append(".log").toString();
            if (!new File(finalFileName).exists()) break;
            System.out.println(createdName);
            i++;
        }

        Path path = Path.of(logFolder);

        if (!Files.isDirectory(path))
        {
            Files.createDirectory(path);
        }

        Files.copy(file.toPath(), Path.of(finalFileName));
    }
}
