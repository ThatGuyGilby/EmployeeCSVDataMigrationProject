Feature: EmployeeReader
  Takes a CSV and parses it int a list of employees

  @ReadListOfEmployees
  Scenario: ReadListOfEmployees
    Given I have a reader
    And I give the reader a file name
    When I call the getValue function
    Then the result should be a list of employees

  @ReadListOfUniqueEmployees
  Scenario: ReadListOfUniqueEmployees
    Given I have a reader
    And I give the reader a file name
    When I call the getValue function
    Then the result should be a list of unique employees