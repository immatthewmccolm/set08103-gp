/*
 * App.java
 * Main Class File used to run the program.
 */

package com.napier.gp;
import static com.napier.gp.Menu.*;

import com.napier.gp.world.reports.U1PopulationDataReports;
import com.napier.gp.world.reports.U2PopulationReports;
import com.napier.gp.world.reports.U3LanguagesReport;


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

        //         Prints Page Title
        pageTitle("Country Data Analysis Software");

//         Prints Menu Items
        menuItem(0, "Exit");
        menuItem(1, "Country Population (L - S in World)");
        menuItem(2, "Country Population (L - S in Continent)");
        menuItem(3, "Country Population (L - S in Region)");
        menuItem(4, "Top N Populated Countries (in World)");
        menuItem(5, "Top N Populated Countries (in Continent)");
        menuItem(6, "Top N Populated Countries (in Region)");
        menuItem(7, "City Population (L - S in World)");
        menuItem(8, "City Population (L - S in Continent)");
        menuItem(9, "City Population (L - S in Region)");
        menuItem(10, "City Population (L - S in Country)");
        menuItem(11, "City Population (L - S in District)");
        menuItem(12, "Top N Populated Cities (in World)");
        menuItem(13, "Top N Populated Cities (in Continent)");
        menuItem(14, "Top N Populated Cities (in Region)");
        menuItem(15, "Top N Populated Cities (in Country)");
        menuItem(16, "Top N Populated Cities (in District)");
        menuItem(17, "Capital City Population (L - S in World)");
        menuItem(18, "Capital City Population (L - S in Continent)");
        menuItem(19, "Capital City Population (L - S in Region)");
        menuItem(20, "Top N Populated Capital Cities (in World)");
        menuItem(21, "Top N Populated Capital Cities (in Continent)");
        menuItem(22, "Top N Populated Capital Cities (in Region)");
        menuItem(23, "Population of People Living and Not Living in Cities");
        menuItem(24, "Population of People Living and Not Living in Cities");
        menuItem(25, "Population of People Living and Not Living in Cities");
        menuItem(26, "World Population");
        menuItem(27, "Continent Populations");
        menuItem(28, "Region Populations");
        menuItem(29, "Country Populations");
        menuItem(30, "District Populations");
        menuItem(31, "City Populations");
        menuItem(32, "Percentage of Selected Languages Spoken (L - S in World)");

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
                    U1PopulationDataReports.printTopNPopulatedCountriesInWorld(5);
                    break;

                case "5":
                    U1PopulationDataReports.printTopNPopulatedCountriesInContinent(5);
                    break;

                case "6":
                    U1PopulationDataReports.printTopNPopulatedCountriesInRegion(5);
                    break;

                case "7":
                    U1PopulationDataReports.printCityPopulationLargestToSmallestInWorld();
                    break;

                case "8":
                    U1PopulationDataReports.printCityPopulationLargestToSmallestInContinent();
                    break;

                case "9":
                    U1PopulationDataReports.printCityPopulationLargestToSmallestInRegion();
                    break;

                case "10":
                    U1PopulationDataReports.printCityPopulationLargestToSmallestInCountry();
                    break;

                case "11":
                    U1PopulationDataReports.printCitiesLargestToSmallestInDistrict();
                    break;

                case "12":
                    U1PopulationDataReports.printTopNPopulatedCitiesInWorld(5);
                    break;

                case "13":
                    U1PopulationDataReports.printTopNPopulatedCitiesInContinent(5);
                    break;

                case "14":
                    U1PopulationDataReports.printTopNPopulatedCitiesInRegion(5);
                    break;

                case "15":
                    U1PopulationDataReports.printTopNPopulatedCitiesInCountry(5);

                case "16":
                    U1PopulationDataReports.printTopNLargestCitiesInDistrict(5);

                case "17":
                    U1PopulationDataReports.printCapitalCitiesLargestToSmallestInWorld();
                    break;

                case "18":
                    U1PopulationDataReports.printCapitalCitiesLargestToSmallestInContinent();
                    break;

                case "19":
                    U1PopulationDataReports.printCapitalCitiesLargestToSmallestInRegion();
                    break;

                case "20":
                    U1PopulationDataReports.printTopNLargestCapitalCitiesInWorld(5);
                    break;

                case "21":
                    U1PopulationDataReports.printTopNLargestCapitalCitiesInContinent(5);
                    break;

                case "22":
                    U1PopulationDataReports.printTopNLargestCapitalCitiesInRegion(5);
                    break;

                case "23":
                    U1PopulationDataReports.printPeopleInAndNotInCitiesInContinent();
                    break;

                case "24":
                    U1PopulationDataReports.printPeopleInAndNotInCitiesInRegion();
                    break;

                case "25":
                    U1PopulationDataReports.printPeopleInAndNotInCitiesInCountry();
                    break;

                case "26":
                    U2PopulationReports.printWorldPopulation();
                    break;

                case "27":
                    U2PopulationReports.printContinentPopulations();
                    break;

                case "28":
                    U2PopulationReports.printRegionPopulations();
                    break;

                case "29":
                    U2PopulationReports.printCountryPopulations();
                    break;

                case "30":
                    U2PopulationReports.printDistrictPopulations();
                    break;

                case "31":
                    U2PopulationReports.printCityPopulations();
                    break;

                case "32":
                    U3LanguagesReport.print();
                    break;

                default:
                    System.out.println("Please enter a valid menu option.\n");
                    break;
            }
        }
    }
}