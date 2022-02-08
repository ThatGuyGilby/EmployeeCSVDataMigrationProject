import com.teamsix.employees.model.Employee;
import com.teamsix.employees.model.EmployeeReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.List;

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
}
