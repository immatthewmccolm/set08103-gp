/*
 * App.java
 * Main Class File used to run the program.
 */

package com.napier.gp;
import static com.napier.gp.Menu.*;
import static com.napier.gp.UserInput.*;
import static com.napier.gp.world.City.*;
import static com.napier.gp.world.Country.*;
import static com.napier.gp.world.CountryLanguage.*;
import static com.napier.gp.Db.*;
import java.sql.*;
import java.util.List;
import com.napier.gp.world.*;
import static com.napier.gp.U3LanguagesReport.*;
import static com.napier.gp.U2PopulationReports.*;
import static com.napier.gp.U1PopulationDataReports.*;


/**
 * Main class used to run the program.
 */
public class App {
    public static void main(String[] args) {

        // Create new Application
        Db a = new Db();

        // Connect to database
        a.connect();

        U1PopulationDataReports.printAll(a.getConnection());

        System.out.println("\nLanguages Report:");
        U3LanguagesReport.print(a.getConnection());

        U2PopulationReports.printAll(a.getConnection());

        // Disconnect from database
        a.disconnect();




















        // Prints Page Title
//        pageTitle("Country Data Analysis Software");

        // Prints Menu Items
//        menuItem(1, "All Countries (Ordered by Population)");

//        printU3LanguagesReport();

//        // Gets user input and loads the appropriate method, otherwise loops until a valid input is received
//        do {
//            String menuInput = getUserInput("Enter your menu choice");
//
//            if (menuInput.equals("1")) {
//                printU3LanguagesReport();
//                break; // exit the loop after a valid option
//            } else {
//                System.out.println("Please enter a valid menu option.\n");
//            }
//
//        } while (true);
    }
}