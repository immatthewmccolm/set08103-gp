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

        // Attempts to populate the World class with the data from the database
        a.TryPopulateWorld();

        // Disconnect from the database
        a.disconnect();

//        U1PopulationDataReports.printAll(a.getConnection());
//
//        System.out.println("\nLanguages Report:");
//        U3LanguagesReport.print(a.getConnection());
//
//        U2PopulationReports.printAll(a.getConnection());
/*
        //         Prints Page Title
        pageTitle("Country Data Analysis Software");

//         Prints Menu Items
        menuItem(0, "Exit");
        menuItem(1, "Country Population (L - S in World)");
        menuItem(2, "Country Population (L - S in Continent)");
        menuItem(3, "Country Population (L - S in Region)");
        menuItem(4, "World Population");
        menuItem(5, "Continent Populations");
        menuItem(6, "Region Populations");
        menuItem(7, "Country Populations");
        menuItem(8, "District Populations");
        menuItem(9, "City Populations");
        menuItem(10, "U3 Language Report");

        // Gets user input and loads the appropriate method, otherwise loops until a valid input is received
        while (true) {
            String menuInput = UserInput.getUserInput("Enter your menu choice (0 to exit)");

            switch (menuInput) {
                case "0":
                    System.out.println("Exiting... Goodbye!");
                    return; // exits the method cleanly

                case "1":
                    U1PopulationDataReports.printCountryPopulationLargestToSmallestInWorld();
                    break;

                case "2":
                    U1PopulationDataReports.printCountryPopulationLargestToSmallestInContinent();
                    break;

                case "3":
                    U1PopulationDataReports.printCountryPopulationLargestToSmallestInRegion();
                    break;

                case "4":
                    U2PopulationReports.printWorldPopulation();
                    break;

                case "5":
                    U2PopulationReports.printContinentPopulations();
                    break;

                case "6":
                    U2PopulationReports.printRegionPopulations();
                    break;

                case "7":
                    U2PopulationReports.printCountryPopulations();
                    break;

                case "8":
                    U2PopulationReports.printDistrictPopulations();
                    break;

                case "9":
                    U2PopulationReports.printCityPopulations();
                    break;
*//*
                case "10":
                    U3LanguagesReport.print(a.getConnection());
                    break;
*//*
                default:
                    System.out.println("Please enter a valid menu option.\n");
                    break;
            }
        }*/
    }
}