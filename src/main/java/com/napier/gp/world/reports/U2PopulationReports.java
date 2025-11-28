package com.napier.gp.world.reports;

import com.napier.gp.HelperFunctions;
import com.napier.gp.world.City;
import com.napier.gp.world.Country;
import com.napier.gp.world.World;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Contains all code related to Use-Case 2: Produce population reports as a baseline to compare to other reports etc.
public class U2PopulationReports {
    // Will call all Use Case 2 related reports in one function for ease of use
    public static void printAll() {
        System.out.println("\nWorld Population:");
        printWorldPopulation();

        System.out.println("\nContinental Populations:");
        printContinentPopulations();

        System.out.println("\nRegion Populations:");
        printRegionPopulations();

        System.out.println("\nCountry Populations:");
        printCountryPopulations();

        System.out.println("\nDistrict Populations:");
        printDistrictPopulations();

        System.out.println("\nCity Populations:");
        printCityPopulations();
    }

    // Prints a report on the total population of the world
    public static void printWorldPopulation() {
        long worldPopulation = 0;

        for(Country country : World.getInstance().getCountries()) {
            worldPopulation += country.getPopulation();
        }

        System.out.println("World Population: " + worldPopulation);
    }

    // Prints a report on the total population of each of the 7 continents
    public static void printContinentPopulations() {
        HashMap<String, Long> continentPopulations = HelperFunctions.getGenericU2Populations(Country::getContinent);

        for(Map.Entry<String, Long> entry : continentPopulations.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    //
    public static void printContinentPopulationByKey(String key) {
        HashMap<String, Long> continentPopulations = HelperFunctions.getGenericU2Populations(Country::getContinent);

        for(Map.Entry<String, Long> entry : continentPopulations.entrySet()) {
            if(entry.getKey().equals(key)) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }
    }

    // Prints a report on the total population of each of the regions of the world
    public static void printRegionPopulations() {
        HashMap<String, Long> regionPopulations = HelperFunctions.getGenericU2Populations(Country::getRegion);
        for(Map.Entry<String, Long> entry : regionPopulations.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    //
    public static void printRegionPopulationByKey(String key) {
        HashMap<String, Long> regionPopulations = HelperFunctions.getGenericU2Populations(Country::getRegion);

        for(Map.Entry<String, Long> entry : regionPopulations.entrySet()) {
            if(entry.getKey().equals(key)) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }
    }

    // Prints a report on the total population of each of the countries of the world
    public static void printCountryPopulations() {
        for(Country country : World.getInstance().getCountries()) {
            System.out.println(country.getName() + " " + country.getPopulation());
        }
    }

    //
    public static void printCountryPopulationByKey(String key) {
        for(Country country : World.getInstance().getCountries()) {
            if(country.getName().equals(key)) {
                System.out.println(country.getName() + " " + country.getPopulation());
            }
        }
    }

    // Prints a report on the total population of each of the districts of the world
    public static void printDistrictPopulations() {
        HashMap<String, Long> districtPopulations = HelperFunctions.getCityDistrictPopulation();

        for(Map.Entry<String, Long> entry : districtPopulations.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    //
    public static void printDistrictPopulationByKey(String key) {
        HashMap<String, Long> districtPopulations = HelperFunctions.getCityDistrictPopulation();

        for(Map.Entry<String, Long> entry : districtPopulations.entrySet()) {
            if(entry.getKey().equals(key)) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }
    }

    // Prints a report on the total population of each of the cities of the world
    public static void printCityPopulations() {
        for(City city : World.getInstance().getCities()) {
            System.out.println(city.getName() + " " + city.getPopulation());
        }
    }

    //
    public static void printCityPopulationByKey(String key) {
        for(City city : World.getInstance().getCities()) {
            if(city.getName().equals(key)) {
                System.out.println(city.getName() + " " + city.getPopulation());
            }
        }
    }
}



