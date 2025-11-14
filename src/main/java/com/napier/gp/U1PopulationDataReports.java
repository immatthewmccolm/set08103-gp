package com.napier.gp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import com.napier.gp.world.City;
import com.napier.gp.world.Country;
import com.napier.gp.world.CountryLanguage;
import com.napier.gp.world.World;

// Contains all code related to Use-Case 1: Produce propulation data reports, so they can be used by the organisation etc.
public class U1PopulationDataReports {

        // Will call all Use Case 1 related reports in one function for ease of use
        static void printAll() {
            printCountryPopulationLargestToSmallestInWorld();
            printCountryPopulationLargestToSmallestInContinent();
            printCountryPopulationLargestToSmallestInRegion();
        }

        // Prints a report on all the countries in the world organised by largest population to smallest.
        static void printCountryPopulationLargestToSmallestInWorld() {
            List<Country> countries = World.getInstance().getCountries();
            Country temp;

            for(int i = 0; i < countries.size() - 1; i++) {
                for(int j = 0; j < countries.size() - i - 1; j++){
                    if(countries.get(j).getPopulation() < countries.get(j + 1).getPopulation()) {
                        temp = countries.get(j);
                        countries.set(j, countries.get(j + 1));
                        countries.set(j + 1, temp);
                    }
                }
            }

            for(Country country : countries) {
                System.out.println(country.getName() + " " + country.getPopulation());
            }
        }

        // Prints a report on all the countries in a continent organised by largest population to smallest.
        static void printCountryPopulationLargestToSmallestInContinent() {
            List<Country> countries = World.getInstance().getCountries();
            HashMap<String, List<Country>> continentPopulations = new HashMap<>();
            List<Country> tempList;

            for(Country country : countries) {
                if(!continentPopulations.containsKey(country.getContinent())) {
                    continentPopulations.put(country.getContinent(),
                            new ArrayList<>(Arrays.asList((country))));
                }
                else {
                    tempList = continentPopulations.get(country.getContinent());
                    tempList.add(country);
                    continentPopulations.put(country.getContinent(), tempList);
                }
            }

            Country temp;

            for(Map.Entry<String, List<Country>> entry : continentPopulations.entrySet()) {
                List<Country> continentCountries = entry.getValue();
                System.out.println(entry.getKey() + ":");

                for(int i = 0; i < continentCountries.size() - 1; i++) {
                    for(int j = 0; j < continentCountries.size() - i - 1; j++){
                        if(continentCountries.get(j).getPopulation() < continentCountries.get(j + 1).getPopulation()) {
                            temp = continentCountries.get(j);
                            continentCountries.set(j, continentCountries.get(j + 1));
                            continentCountries.set(j + 1, temp);
                        }
                    }
                }

                for(Country country : continentCountries) {
                    System.out.println(country.getName() + " " + country.getPopulation());
                }

                System.out.println("\n");
            }
        }

        // Prints a report on all the countries in a region organised by largest population to smallest.
        static void printCountryPopulationLargestToSmallestInRegion() {
            List<Country> countries = World.getInstance().getCountries();
            HashMap<String, List<Country>> regionPopulations = new HashMap<>();
            List<Country> tempList;

            for(Country country : countries) {
                if(!regionPopulations.containsKey(country.getRegion())) {
                    regionPopulations.put(country.getRegion(),
                            new ArrayList<>(Arrays.asList((country))));
                }
                else {
                    tempList = regionPopulations.get(country.getRegion());
                    tempList.add(country);
                    regionPopulations.put(country.getRegion(), tempList);
                }
            }

            Country temp;

            for(Map.Entry<String, List<Country>> entry : regionPopulations.entrySet()) {
                List<Country> continentCountries = entry.getValue();
                System.out.println(entry.getKey() + ":");

                for(int i = 0; i < continentCountries.size() - 1; i++) {
                    for(int j = 0; j < continentCountries.size() - i - 1; j++){
                        if(continentCountries.get(j).getPopulation() < continentCountries.get(j + 1).getPopulation()) {
                            temp = continentCountries.get(j);
                            continentCountries.set(j, continentCountries.get(j + 1));
                            continentCountries.set(j + 1, temp);
                        }
                    }
                }

                for(Country country : continentCountries) {
                    System.out.println(country.getName() + " " + country.getPopulation());
                }

                System.out.println("\n");
            }
        }
}

