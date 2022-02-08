import com.teamsix.employees.Employee;
import com.teamsix.employees.EmployeeReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.testng.Assert;

import java.util.ArrayList;
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
        reader.setPathToReadCSVFrom("src/main/resources/employees.csv");
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

    @Then("the result should be a list of unique employees")
    public void theResultShouldBeAListOfUniqueEmployees()
    {
        List<Integer> usedIDs = new ArrayList<>();
        boolean duplicateFound = false;

        for (int i = 0; i < employees.size(); i++)
        {
            if (!usedIDs.contains(employees.get(i).empID))
            {
                usedIDs.add(employees.get(i).empID);
            }
            else
            {
                duplicateFound = true;
            }
        }

        Assert.assertEquals(duplicateFound, false);
    }
}
