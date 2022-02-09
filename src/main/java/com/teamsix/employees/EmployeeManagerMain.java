package com.teamsix.employees;

import com.teamsix.employees.model.DatabaseIO;
import com.teamsix.employees.view.EmployeeView;

public class EmployeeManagerMain
{
    public static void main(String[] args)
    {
        DatabaseIO.linkToSQLDatabase();
        DatabaseIO.persistEmployees();

        //EmployeeView.userFileInput();
    }
}
