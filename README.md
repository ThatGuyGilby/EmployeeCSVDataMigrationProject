## Table of Contents
- [How to Use the Project](#how-to-use-the-project)
- [Phase 1](#phase-1)
- [Phase 2](#phase-2)
- [Phase 3](#phase-3)
- [Phase 4](#phase-4)
- [JUnit Testing](#junit-testing)

## How to Use the Project

[⮬ Table of Contents](#table-of-contents)

# Phase 1
### Initial Reading and Cleaning
- Create a new project and write code to read data from an Employee CSV file.
- As it is read in, add each record read to a new object of a suitable class and then add those objects to a collection.
- Any corrupt or duplicated data should be added to a separate collection for further analysis.
- Write tests to ensure data is being managed correctly.
- Consider which date class would be best to use for the date fields – there is one in java.util and another in java.sql.
- Provide a simple user interface to display the results of reading the file – how many unique, clean records there are, how many duplicates, how many records with missing fields, possibly display the questionable records.
- User the provided EmployeeRecords.csv for your testing and optionally create your own test files to help with your JUnit tests.

Relevant Classes
- [Employee.java](../dev/src/main/java/com/teamsix/employees/model/Employee.java)
- [EmployeeReader.java](../dev/src/main/java/com/teamsix/employees/model/EmployeeReader.java)

[⮬ Table of Contents](#table-of-contents)

# Phase 2
### Persist to Database
- Write the SQL statements to create a table and to persist data to that table. If the table exists, it will need to be dropped first.
- Install the drivers for the database to be used (MySQL) and create a connection.
- Create a data access object (DAO pattern) to persist the data to the database.
- Persist employee records and write code to retrieve individual records from the database.

Relevant Classes
- [ConnectionFactory.java](../dev/src/main/java/com/teamsix/employees/model/ConnectionFactory.java)
- [DatabaseIO.java](../dev/src/main/java/com/teamsix/employees/model/DatabaseIO.java)
- [DatabaseIOExample.java](../dev/src/main/java/com/teamsix/employees/DatabaseIOExample.java)

[⮬ Table of Contents](#table-of-contents)

# Phase 3
### Add Multithreading
- Use the second file, EmployeeRecordsLarge.csv, which can be assumed to have already been cleaned.
- Record time taken to persist before implementing multiple threads.
- Add multithreading to your application to write the data to the database, comparing the execution time with the single-threaded version.
- Try different numbers of threads and compare the results – what is the optimum number of threads? Record this information in your readme.md

[⮬ Table of Contents](#table-of-contents)

# Phase 4
### Add Streams and Lambdas
- Modify code to make use of functional programming concepts – lambdas and streams.
- Keep the original code and then run tests to see if efficiency has improved by adding functional code.

[⮬ Table of Contents](#table-of-contents)

# JUnit Testing
Most of the testing in this project followed the Test Driven Development (TDD) framework of software development and testing.

### **The testing of EmployeeView**

In the vein of TDD, EmployeeView was tested and then developed to fit with the tests in order for them to pass. From testing the return of the filePath String to the return of the Thread count. Some exit points were included and testing was tricky on detecting them. It was therefore decided that they would stay and manual testing would be performed on them. Wherever they existed, testing of the exit conditions was performed. Passing in files that didn't match the specified file type, if it didn't contain "csv" following a full stop. 

As well as this, the user is also given the choice of searching the database for a single user, testing was performed to ensure that only a "yes" would trigger the search. Using a "Yes" and a "yes" followed by a "No", "no" and an Integer. Each failed initially and was subsequently made to pass, besides the Integer test case that passed with no change needed upon the passing of the "No" and "no" test cases.

![image-20220213134034698](https://i.imgur.com/pQcPh4F.png)


Relevant Classes
- [EmployeeViewTest.java](../dev/src/test/java/com/teamsix/employees/view/EmployeeViewTest.java)

[⮬ Table of Contents](#table-of-contents)
