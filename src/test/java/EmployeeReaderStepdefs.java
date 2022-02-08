import com.teamsix.employees.Employee;
import com.teamsix.employees.EmployeeReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmployeeReaderStepdefs {
    EmployeeReader reader;
    List<Employee> employees;

    @Given("I have a reader")
    public void iHaveAReader()
    {
        reader = new EmployeeReader();
    }

    @And("I give the reader a file name")
    public void iGiveTheReaderAFileName()
    {
        reader.setPathToReadCSVFrom("E://AdmiralJava-main/EmployeeCSV/project-docs/EmployeeRecords.csv");
    }

    @When("I call the getValue function")
    public void iCallTheGetValueFunction()
    {
        employees = reader.getValue();
    }

    @Then("the result should be a list of employees")
    public void theResultShouldBeAListOfEmployees()
    {
        Assertions.assertNotNull(employees);
    }

    @Then("the result should be a distinct list of employees")
    public void theResultShouldBeADistinctListOfEmployees()
    {
        boolean hasDuplicates = false;

        Set<Employee> lump = new HashSet<Employee>();
        for (Employee i : employees)
        {
            if (lump.contains(i)) hasDuplicates = true;
            lump.add(i);
        }

        Assertions.assertEquals(hasDuplicates, "false");
    }
}
