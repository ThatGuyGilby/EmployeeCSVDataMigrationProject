## Table of Contents
- [How to Use the Project](#how-to-use-the-project)
- [Phase 1](#phase-1)
- [Phase 2](#phase-2)
- [Phase 3](#phase-3)
- [Phase 4](#phase-4)
- [JUnit Testing](#junit-testing)
- [Parallel Junit Testing](#parallel-junit-testing)

## How to Use the Project
This project uses a variety of different externally obtained packages, listed below:
* JUnit
* Cucumber
* Cucumber for Java
* MySQL Connector for Java
* Log4J

While they're not all required, it is recommended to have these installed. As well as this, MySQL requires a properties file. A basic formulated file is included with the project. Change the dbPassword field to match your installed setup. dbURL and dbUser may also require changing based on install.

Once installed, the user can run the application. On first run it is recommended to run through the file the user can specify as searching the database will be useless and will return an error.

### First runthrough
On run the first input the user is shown is for the user to decide whether to run the full program or not. The option "Yes" will search the database and quit after returning the specified user with the UserID. Errors as per above if no database exists. Inputting "No" will run the full program.

First up in the full program runthrough is the user being asked for a file to pull data from. If the file doesn't fit the specific file type, it will alert the user. Same for a file that does not exist and a malformed filepath.

Following this the user is asked which amount of threads to use. Anything in the range of 1-144 is acceptable. Below 1 is caught. 145 and above is out of the range that MySQL will accept for connections.

The program will scan the file and input the data into the database. Following this the user will be asked if they wish to search the database for a user based on a UserID. Now that the database is filled, this will work. Provide a valid UserID and the program will query the database and return the full entry based on the ID.


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

### **The testing of DatabaseIOTest**

DatabaseIO class was tested to see whether the class is reading the file and writing SQL queries in correct way. The class was tested to make sure that every employee was added to database and no value was missing in these queries.

It was tested again in some exceptions to ensure that it is working in right way. For example, scenario of no thread to use and trying to insert the table whose name is the same as the one inserted before. 

ResourceLock annotations were used to be able to make synchronized tests in parallel testing.

![image-DatabaseIOTestResults](https://i.imgur.com/NFOUMyL.jpg)


Relevant Class
- [DatabaseIOTest.java](../dev/src/test/java/com/teamsix/employees/model/DatabaseIOTest.java)

[⮬ Table of Contents](#table-of-contents)

### **The testing of EmployeeTest**

Employee class was tested to see that each employee's value is formatted in correct way and no missing value as well. Mostly lambda expressions were used to be able to implement one line code testing. Also, regex was used to check some values of class like email, address etc. 

![image-EmployeeTest](https://i.imgur.com/8opTEvV.png)


Relevant Class
- [EmployeeTest.java](../dev/src/test/java/com/teamsix/employees/model/EmployeeTest.java)

[⮬ Table of Contents](#table-of-contents)

## Parallel Junit Testing

Parallel testing was implemented using configuration file named junit-platform.properties. It was done not only across the classes also in the test methods in same class.

![image-ParallelTesting](https://i.imgur.com/mAcIGf4.png)

It can easily be run if you click on package or any class. It runs as below

![image-ParallelTestingExample](https://i.imgur.com/YzeWTqR.png)


Relevant File
- [junit-platform.properties](../dev/src/test/resources/junit-platform.properties)

[⮬ Table of Contents](#table-of-contents)
