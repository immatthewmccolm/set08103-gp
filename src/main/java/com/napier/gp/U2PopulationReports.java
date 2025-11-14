package com.napier.gp;

import java.sql.Array.*;
import java.sql.ResultSet;
import java.sql.Statement;

import com.napier.gp.world.City;
import com.napier.gp.world.City.*;
import com.napier.gp.Db.*;
import com.napier.gp.world.Country;
import com.napier.gp.world.World;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Contains all code related to Use-Case 2: Produce population reports as a baseline to compare to other reports etc.
public class U2PopulationReports {
    // Will call all Use Case 2 related reports in one function for ease of use
    static void printAll(Connection con) {
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
    static void printWorldPopulation() {
        long worldPopulation = 0;

        for(Country country : World.getInstance().getCountries()) {
            worldPopulation += country.getPopulation();
        }

        System.out.println("World Population: " + worldPopulation);
    }

    // Prints a report on the total population of the world
    static long returnWorldPopulation() {
        long worldPopulation = 0;

        for(Country country : World.getInstance().getCountries()) {
            worldPopulation += country.getPopulation();
        }

        return worldPopulation;
    }

    // Prints a report on the total population of each of the 7 continents
    static void printContinentPopulations() {
        List<Country> countries = World.getInstance().getCountries();
        HashMap<String, Long> continentPopulations = new HashMap<>();

        for(Country country : countries) {
            if(!continentPopulations.containsKey(country.getContinent())) {
                continentPopulations.put(country.getContinent(), (long)country.getPopulation());
            }
            else {
                continentPopulations.put(country.getContinent(),
                        continentPopulations.get(country.getContinent()) + (long)country.getPopulation());
            }
        }

        for(Map.Entry<String, Long> entry : continentPopulations.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    static long printContinentPopulationByKey(String key) {
        List<Country> countries = World.getInstance().getCountries();
        HashMap<String, Long> continentPopulations = new HashMap<>();
        long _continentPopulation = 0;

        for(Country country : countries) {
            if(!continentPopulations.containsKey(country.getContinent())) {
                continentPopulations.put(country.getContinent(), (long)country.getPopulation());
            }
            else {
                continentPopulations.put(country.getContinent(),
                        continentPopulations.get(country.getContinent()) + (long)country.getPopulation());
            }
        }

        for(Map.Entry<String, Long> entry : continentPopulations.entrySet()) {
            if(entry.getKey().equals(key)) {
                _continentPopulation = entry.getValue();
            }
        }

        return _continentPopulation;
    }

    // Prints a report on the total population of each of the regions of the world
    static void printRegionPopulations() {
        List<Country> countries = World.getInstance().getCountries();
        HashMap<String, Long> regionPopulations = new HashMap<>();

        for(Country country : countries) {
            if(!regionPopulations.containsKey(country.getRegion())) {
                regionPopulations.put(country.getRegion(), (long)country.getPopulation());
            }
            else {
                regionPopulations.put(country.getRegion(),
                        regionPopulations.get(country.getRegion()) + (long)country.getPopulation());
            }
        }

        for(Map.Entry<String, Long> entry : regionPopulations.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    static long printRegionPopulationByKey(String key) {
        List<Country> countries = World.getInstance().getCountries();
        HashMap<String, Long> regionPopulations = new HashMap<>();
        long _regionPopulation = 0;

        for(Country country : countries) {
            if(!regionPopulations.containsKey(country.getRegion())) {
                regionPopulations.put(country.getRegion(), (long)country.getPopulation());
            }
            else {
                regionPopulations.put(country.getRegion(),
                        regionPopulations.get(country.getRegion()) + (long)country.getPopulation());
            }
        }

        for(Map.Entry<String, Long> entry : regionPopulations.entrySet()) {
            if(entry.getKey().equals(key)) {
                System.out.println(entry.getKey() + " " + entry.getValue());
                _regionPopulation = entry.getValue();
            }
        }

        return _regionPopulation;
    }

    // Prints a report on the total population of each of the countries of the world
    static void printCountryPopulations() {
        for(Country country : World.getInstance().getCountries()) {
            System.out.println(country.getName() + " " + country.getPopulation());
        }
    }

    static int printCountryPopulationByKey(String key) {
        int _countryPopulation = 0;
        for(Country country : World.getInstance().getCountries()) {
            if(country.getName().equals(key)) {
                _countryPopulation = country.getPopulation();
            }
        }

        return _countryPopulation;
    }

    // Prints a report on the total population of each of the districts of the world
    static void printDistrictPopulations() {
        List<City> cities = World.getInstance().getCities();
        HashMap<String, Long> districtPopulations = new HashMap<>();

        for(City city : cities) {
            if(!districtPopulations.containsKey(city.getDistrict())) {
                districtPopulations.put(city.getDistrict(), (long)city.getPopulation());
            }
            else {
                districtPopulations.put(city.getDistrict(),
                        districtPopulations.get(city.getDistrict()) + (long)city.getPopulation());
            }
        }

        for(Map.Entry<String, Long> entry : districtPopulations.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    static long printDistrictPopulationByKey(String key) {
        List<City> cities = World.getInstance().getCities();
        HashMap<String, Long> districtPopulations = new HashMap<>();
        long _districtPopulation = 0;

        for(City city : cities) {
            if(!districtPopulations.containsKey(city.getDistrict())) {
                districtPopulations.put(city.getDistrict(), (long)city.getPopulation());
            }
            else {
                districtPopulations.put(city.getDistrict(),
                        districtPopulations.get(city.getDistrict()) + (long)city.getPopulation());
            }
        }

        for(Map.Entry<String, Long> entry : districtPopulations.entrySet()) {
            if(entry.getKey().equals(key)) {
                _districtPopulation = entry.getValue();
            }
        }

        return _districtPopulation;
    }

    // Prints a report on the total population of each of the cities of the world
    static void printCityPopulations() {
        for(City city : World.getInstance().getCities()) {
            System.out.println(city.getName() + " " + city.getPopulation());
        }
    }

    static int printCityPopulationByKey(String key) {
        int _cityPopulation = 0;
        for(City city : World.getInstance().getCities()) {
            if(city.getName().equals(key)) {
                _cityPopulation =  city.getPopulation();
            }
        }
        return _cityPopulation;
    }
}



