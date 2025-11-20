package com.napier.gp.world.reports;

import java.util.*;

import com.napier.gp.world.City;
import com.napier.gp.world.Country;
import com.napier.gp.world.World;

// Contains all code related to Use-Case 1: Produce propulation data reports, so they can be used by the organisation etc.
public class U1PopulationDataReports {

    // Will call all Use Case 1 related reports in one function for ease of use
    static void printAll() {
        //Country reports
        printCountryPopulationLargestToSmallestInWorld();
        printTopNPopulatedCountriesInWorld(5);
        printCountryPopulationLargestToSmallestInContinent();
        printTopNPopulatedCountriesInContinent(5);
        printCountryPopulationLargestToSmallestInRegion();
        printTopNPopulatedCountriesInRegion(5);
        //City Reports
        printCityPopulationLargestToSmallestInWorld();
        printTopNPopulatedCitiesInWorld(5);
        printCityPopulationLargestToSmallestInContinent();
        printTopNPopulatedCitiesInContinent(5);
        printCityPopulationLargestToSmallestInRegion();
        printTopNPopulatedCitiesInRegion(5);
        printCityPopulationLargestToSmallestInCountry();
        printTopNPopulatedCitiesInCountry(5);
        printCitiesLargestToSmallestInDistrict();
        printTopNLargestCitiesInDistrict(5);
        //Capital City reports
        printCapitalCitiesLargestToSmallestInWorld();
        printTopNLargestCapitalCitiesInWorld(5);
        printCapitalCitiesLargestToSmallestInContinent();
        printTopNLargestCapitalCitiesInContinent(5);
        printCapitalCitiesLargestToSmallestInRegion();
        printTopNLargestCapitalCitiesInRegion(5);
        // Population of people in and not in cities
        printPeopleInAndNotInCitiesInContinent();
        printPeopleInAndNotInCitiesInRegion();
        printPeopleInAndNotInCitiesInCountry();
    }

    // Prints a report on all the countries in the world organised by largest population to smallest.
    public static List<Country> getCountryPopulationLargestToSmallestInWorld() {
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

        return countries;
    }

    public static void printCountryPopulationLargestToSmallestInWorld() {
        List<Country> countries = getCountryPopulationLargestToSmallestInWorld();

        for(Country country : countries) {
            System.out.println(country.getName() + " " + country.getPopulation());
        }
    }

    public static void printTopNPopulatedCountriesInWorld(int n) {
        List<Country> countries = getCountryPopulationLargestToSmallestInWorld();

        for (int i = 0; i < n; i++) {
            System.out.println(countries.get(i));
        }
    }

    // Prints a report on all the countries in a continent organised by largest population to smallest.
    public static HashMap<String, List<Country>> getCountryPopulationLargestToSmallestInContinent() {
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

    return continentPopulations;
}

    public static void printCountryPopulationLargestToSmallestInContinent() {
        List<Country> countries = World.getInstance().getCountries();
        HashMap<String, List<Country>> continentPopulations = getCountryPopulationLargestToSmallestInContinent();
        List<Country> tempList;

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

    public static void printTopNPopulatedCountriesInContinent(int n) {
        List<Country> countries = World.getInstance().getCountries();
        HashMap<String, List<Country>> continentPopulations = getCountryPopulationLargestToSmallestInContinent();
        List<Country> tempList;

        // remove the excess entries first. Trim
        int runningTotal = 0;
        HashMap<String, List<Country>> trimmedContinentPopulations = new HashMap<>();

        // Use a running total to ensure only n amount of values get added.
        for (Map.Entry<String, List<Country>> entry : continentPopulations.entrySet()) {
            if (runningTotal >= n) {
                break;
            }
            trimmedContinentPopulations.put(entry.getKey(), entry.getValue());
            runningTotal++;
        }

        Country temp;

        for(Map.Entry<String, List<Country>> entry : trimmedContinentPopulations.entrySet()) {
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

    public static HashMap<String, List<Country>> getCountryPopulationLargestToSmallestInRegion() {
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

        return regionPopulations;

    }
    // Prints a report on all the countries in a region organised by largest population to smallest.
    public static void printCountryPopulationLargestToSmallestInRegion() {

        Country temp;
        HashMap<String, List<Country>> regionPopulations = getCountryPopulationLargestToSmallestInRegion();

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

    public static void printTopNPopulatedCountriesInRegion(int n) {
        List<Country> countries = World.getInstance().getCountries();
        HashMap<String, List<Country>> regionPopulations = getCountryPopulationLargestToSmallestInRegion();

        // remove the excess entries first. Trim
        int runningTotal = 0;
        HashMap<String, List<Country>> trimmedRegionPopulations = new HashMap<>();

        // Use a running total to ensure only n amount of values get added.
        for (Map.Entry<String, List<Country>> entry : regionPopulations.entrySet()) {
            if (runningTotal >= n) {
                break;
            }
            trimmedRegionPopulations.put(entry.getKey(), entry.getValue());
            runningTotal++;
        }

        Country temp;

        for(Map.Entry<String, List<Country>> entry : trimmedRegionPopulations.entrySet()) {
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

    // Prints a report on all the cities in the world organised by largest population to smallest.
    public static List<City> getCitiesPopulationLargestToSmallestInWorld() {
        List<City> cities = World.getInstance().getCities();
        City temp;

        for(int i = 0; i < cities.size() - 1; i++) {
            for(int j = 0; j < cities.size() - i - 1; j++){
                if(cities.get(j).getPopulation() < cities.get(j + 1).getPopulation()) {
                    temp = cities.get(j);
                    cities.set(j, cities.get(j + 1));
                    cities.set(j + 1, temp);
                }
            }
        }

        return cities;
    }

    public static void printCityPopulationLargestToSmallestInWorld() {
        List<City> cities = getCitiesPopulationLargestToSmallestInWorld();

        for(City city : cities) {
            System.out.println(city.getName() + " " + city.getPopulation());
        }
    }

    public static void printTopNPopulatedCitiesInWorld(int n) {
        List<City> cities = getCitiesPopulationLargestToSmallestInWorld();

        for (int i = 0; i < n; i++) {
            System.out.println(cities.get(i));
        }
    }

    // Prints a report on all the cities in a continent organised by largest population to smallest.
    public static HashMap<String, List<City>> getCityPopulationLargestToSmallestInContinent() {
        List<City> cities = World.getInstance().getCities();
        List<Country> countries = World.getInstance().getCountries();
        List<City> tempList;

        HashMap<String, List<City>> continentPopulations = new HashMap<>();

        for(Country country : countries) {
            if(!continentPopulations.containsKey(country.getContinent())) {
                for(City city : cities) {
                    if(city.getCountryCode() == country.getCode()) {
                        continentPopulations.put(country.getContinent(),
                                new ArrayList<>(Arrays.asList(city)));
                    }
                }
            }
            else {
                for(City city : cities) {
                    if(city.getID() == country.getCapital()) {
                        tempList = continentPopulations.get(country.getContinent());
                        tempList.add(city);
                        continentPopulations.put(country.getContinent(), tempList);
                    }
                }
            }
        }

        return continentPopulations;
    }

    public static void printCityPopulationLargestToSmallestInContinent() {
        List<City> cities = World.getInstance().getCities();
        HashMap<String, List<City>> continentPopulations = getCityPopulationLargestToSmallestInContinent();
        List<City> tempList;

        City temp;

        for(Map.Entry<String, List<City>> entry : continentPopulations.entrySet()) {
            List<City> continentCountries = entry.getValue();
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

            for(City city : continentCountries) {
                System.out.println(city.getName() + " " + city.getPopulation());
            }

            System.out.println("\n");
        }
    }

    public static void printTopNPopulatedCitiesInContinent(int n) {
        List<City> cities = World.getInstance().getCities();
        HashMap<String, List<City>> continentPopulations = getCityPopulationLargestToSmallestInContinent();
        List<City> tempList;

        // remove the excess entries first. Trim
        int runningTotal = 0;
        HashMap<String, List<City>> trimmedContinentPopulations = new HashMap<>();

        // Use a running total to ensure only n amount of values get added.
        for (Map.Entry<String, List<City>> entry : continentPopulations.entrySet()) {
            if (runningTotal >= n) {
                break;
            }
            trimmedContinentPopulations.put(entry.getKey(), entry.getValue());
            runningTotal++;
        }

        City temp;

        for(Map.Entry<String, List<City>> entry : trimmedContinentPopulations.entrySet()) {
            List<City> continentCountries = entry.getValue();
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

            for(City city : continentCountries) {
                System.out.println(city.getName() + " " + city.getPopulation());
            }

            System.out.println("\n");
        }

    }

    public static HashMap<String, List<City>> getCityPopulationLargestToSmallestInRegion() {
        List<Country> countries = World.getInstance().getCountries();
        List<City> cities = World.getInstance().getCities();

        List<City> tempList;

        HashMap<String, List<City>> regionPopulations = new HashMap<>();

        // All the cities in a country organised by largest population to smallest.
        for(Country country : countries) {
            if(!regionPopulations.containsKey(country.getRegion())) {
                for(City city : cities) {
                    if(city.getCountryCode() == country.getCode()) {
                        regionPopulations.put(country.getRegion(),
                                new ArrayList<>(Arrays.asList(city)));
                    }
                }
            }
            else {
                for(City city : cities) {
                    tempList = regionPopulations.get(country.getRegion());
                    tempList.add(city);
                    regionPopulations.put(country.getRegion(), tempList);
                }
            }
        }

        City temp;

        for(Map.Entry<String, List<City>> entry : regionPopulations.entrySet()) {
            for(int i = 0; i < entry.getValue().size() - 1; i++) {
                for(int j = 0; j < entry.getValue().size() - i - 1; j++) {
                    if(entry.getValue().get(j).getPopulation() <
                            entry.getValue().get(j + 1).getPopulation()) {
                        temp = entry.getValue().get(j);
                        entry.getValue().set(j, entry.getValue().get(j + 1));
                        entry.getValue().set(j + 1, temp);
                    }
                }
            }
        }

        return regionPopulations;
    }

    public static void printCityPopulationLargestToSmallestInRegion() {
        HashMap<String, List<City>> regionPopulations = getCityPopulationLargestToSmallestInRegion();

        for(Map.Entry<String, List<City>> entry : regionPopulations.entrySet()) {
            List<City> regionCities = entry.getValue();
            System.out.println(entry.getKey() + ":");

            for(City city : regionCities) {
                System.out.println(city.getName() + " " + city.getPopulation());
            }

            System.out.println("\n");
        }
    }

    public static void printTopNPopulatedCitiesInRegion(int n) {
        HashMap<String, List<City>> regionPopulations = getCityPopulationLargestToSmallestInRegion();

        // remove the excess entries first. Trim
        int runningTotal = 0;
        HashMap<String, List<City>> trimmedContinentPopulations = new HashMap<>();

        // Use a running total to ensure only n amount of values get added.
        for (Map.Entry<String, List<City>> entry : regionPopulations.entrySet()) {
            if (runningTotal >= n) {
                break;
            }
            trimmedContinentPopulations.put(entry.getKey(), entry.getValue());
            runningTotal++;
        }

        for(Map.Entry<String, List<City>> entry : trimmedContinentPopulations.entrySet()) {
            List<City> regionCities = entry.getValue();
            System.out.println(entry.getKey() + ":");

            for(City city : regionCities) {
                System.out.println(city.getName() + " " + city.getPopulation());
            }

            System.out.println("\n");
        }
    }

    public static HashMap<String, List<City>> getCityPopulationLargestToSmallestInCountry() {
        List<Country> countries = World.getInstance().getCountries();
        List<City> cities = World.getInstance().getCities();

        List<City> tempList;

        HashMap<String, List<City>> countryPopulations = new HashMap<>();

        // All the cities in a country organised by largest population to smallest.
        for(Country country : countries) {
            if(!countryPopulations.containsKey(country.getName())) {
                for(City city : cities) {
                    if(city.getCountryCode() == country.getCode()) {
                        countryPopulations.put(country.getName(),
                                new ArrayList<>(Arrays.asList(city)));
                    }
                }
            }
            else {
                for(City city : cities) {
                    if(city.getID() == country.getCapital()) {
                        tempList = countryPopulations.get(country.getName());
                        tempList.add(city);
                        countryPopulations.put(country.getName(), tempList);
                    }
                }
            }
        }

        City temp;

        for(Map.Entry<String, List<City>> entry : countryPopulations.entrySet()) {
            for(int i = 0; i < entry.getValue().size() - 1; i++) {
                for(int j = 0; j < entry.getValue().size() - i - 1; j++) {
                    if(entry.getValue().get(j).getPopulation() <
                            entry.getValue().get(j + 1).getPopulation()) {
                        temp = entry.getValue().get(j);
                        entry.getValue().set(j, entry.getValue().get(j + 1));
                        entry.getValue().set(j + 1, temp);
                    }
                }
            }
        }

        return countryPopulations;
    }

    public static void printCityPopulationLargestToSmallestInCountry() {
        HashMap<String, List<City>> countryPopulations = getCityPopulationLargestToSmallestInCountry();

        for(Map.Entry<String, List<City>> entry : countryPopulations.entrySet()) {
            List<City> countryCities = entry.getValue();
            System.out.println(entry.getKey() + ":");

            for(City city : countryCities) {
                System.out.println(city.getName() + " " + city.getPopulation());
            }

            System.out.println("\n");
        }
    }

    public static void printTopNPopulatedCitiesInCountry(int n) {
        HashMap<String, List<City>> countryPopulations = getCapitalCitiesLargestToSmallestInContinent();

        // remove the excess entries first. Trim
        int runningTotal = 0;
        HashMap<String, List<City>> trimmedContinentPopulations = new HashMap<>();

        // Use a running total to ensure only n amount of values get added.
        for (Map.Entry<String, List<City>> entry : countryPopulations.entrySet()) {
            if (runningTotal >= n) {
                break;
            }
            trimmedContinentPopulations.put(entry.getKey(), entry.getValue());
            runningTotal++;
        }

        for(Map.Entry<String, List<City>> entry : trimmedContinentPopulations.entrySet()) {
            List<City> countryCities = entry.getValue();
            System.out.println(entry.getKey() + ":");

            for(City city : countryCities) {
                System.out.println(city.getName() + " " + city.getPopulation());
            }

            System.out.println("\n");
        }
    }

    public static HashMap<String, List<City>> getCityPopulationLargestToSmallestInDistrict() {
        List<City> cities = World.getInstance().getCities();
        HashMap<String, List<City>> districtPopulations = new HashMap<>();

        List<City> tempList;
        City temp;

        for (City city : cities) {
            if(!districtPopulations.containsKey(city.getDistrict())) {
                districtPopulations.put(city.getDistrict(),
                        new ArrayList<>(Arrays.asList(city)));
            }
            else {
                tempList = districtPopulations.get(city.getDistrict());
                tempList.add(city);
                districtPopulations.put(city.getDistrict(), tempList);
            }
        }

        for(Map.Entry<String, List<City>> entry : districtPopulations.entrySet()) {
            for(int i = 0; i < entry.getValue().size() - 1; i++) {
                for(int j = 0; j < entry.getValue().size() - i - 1; j++) {
                    if(entry.getValue().get(j).getPopulation() <
                            entry.getValue().get(j + 1).getPopulation()) {
                        temp = entry.getValue().get(j);
                        entry.getValue().set(j, entry.getValue().get(j + 1));
                        entry.getValue().set(j + 1, temp);
                    }
                }
            }
        }

        return districtPopulations;
    }

    public static void printCitiesLargestToSmallestInDistrict() {
        HashMap<String, List<City>> districtPopulations = getCityPopulationLargestToSmallestInDistrict();

        for(Map.Entry<String, List<City>> entry : districtPopulations.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public static void printTopNLargestCitiesInDistrict(int n) {
        HashMap<String, List<City>> districtPopulations = getCapitalCitiesLargestToSmallestInContinent();

        // remove the excess entries first. Trim
        int runningTotal = 0;
        HashMap<String, List<City>> trimmedDistrictPopulations = new HashMap<>();

        // Use a running total to ensure only n amount of values get added.
        for (Map.Entry<String, List<City>> entry : districtPopulations.entrySet()) {
            if (runningTotal >= n) {
                break;
            }
            trimmedDistrictPopulations.put(entry.getKey(), entry.getValue());
            runningTotal++;
        }

        for(Map.Entry<String, List<City>> entry : trimmedDistrictPopulations.entrySet()) {
            List<City> continentCountries = entry.getValue();
            System.out.println(entry.getKey() + ":");

            for(City city : continentCountries) {
                System.out.println(city.getName() + " " + city.getPopulation());
            }

            System.out.println("\n");
        }
    }

    // ll the capital cities in the world organised by largest population to smallest.
    public static List<City> getCapitalCitiesLargestToSmallestInWorld() {
    List<City> cities = World.getInstance().getCities();
    List<Country> countries = World.getInstance().getCountries();

    HashMap<String, List<City>> continentPopulations = new HashMap<>();
    List<City> capitalCities = new ArrayList<>();

    for(Country country : countries) {
        for (City city : cities) {
            if (city.getID() == country.getCapital()) {
                capitalCities.add(city);
            }
        }
    }

    City temp;

    for(int i = 0; i < capitalCities.size() - 1; i++) {
        for(int j = 0; j < capitalCities.size() - i - 1; j++){
            if(capitalCities.get(j).getPopulation() < cities.get(j + 1).getPopulation()) {
                temp = cities.get(j);
                capitalCities.set(j, cities.get(j + 1));
                capitalCities.set(j + 1, temp);
            }
        }
    }

    return capitalCities;
}

    public static void printCapitalCitiesLargestToSmallestInWorld() {
        List<City> cities = getCapitalCitiesLargestToSmallestInWorld();

        for(City city : cities) {
            System.out.println(city.getName() + " " + city.getPopulation());
        }
    }

    public static void printTopNLargestCapitalCitiesInWorld(int n) {
        List<City> cities = getCapitalCitiesLargestToSmallestInWorld();

        for (int i = 0; i < n; i++) {
            System.out.println(cities.get(i));
        }
    }

    public static HashMap<String, List<City>> getCapitalCitiesLargestToSmallestInContinent() {
        List<Country> countries = World.getInstance().getCountries();
        List<City> cities = World.getInstance().getCities();

        List<City> tempList;

        HashMap<String, List<City>> continentPopulations = new HashMap<>();

        // All the capital cities in a continent organised by largest population to smallest.

        for(Country country : countries) {
            if(!continentPopulations.containsKey(country.getContinent())) {
                for(City city : cities) {
                    if(city.getID() == country.getCapital()) {
                        continentPopulations.put(country.getContinent(),
                                new ArrayList<>(Arrays.asList(city)));
                    }
                }
            }
            else {
                for(City city : cities) {
                    if(city.getID() == country.getCapital()) {
                        tempList = continentPopulations.get(country.getContinent());
                        tempList.add(city);
                        continentPopulations.put(country.getContinent(), tempList);
                    }
                }
            }
        }

        City temp;

        for(Map.Entry<String, List<City>> entry : continentPopulations.entrySet()) {
            for(int i = 0; i < entry.getValue().size() - 1; i++) {
                for(int j = 0; j < entry.getValue().size() - i - 1; j++) {
                    if(entry.getValue().get(j).getPopulation() <
                            entry.getValue().get(j + 1).getPopulation()) {
                        temp = entry.getValue().get(j);
                        entry.getValue().set(j, entry.getValue().get(j + 1));
                        entry.getValue().set(j + 1, temp);
                    }
                }
            }
        }

        return continentPopulations;
    }

    public static void printCapitalCitiesLargestToSmallestInContinent() {
        HashMap<String, List<City>> continentPopulations = getCapitalCitiesLargestToSmallestInContinent();

        for(Map.Entry<String, List<City>> entry : continentPopulations.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public static void printTopNLargestCapitalCitiesInContinent(int n) {
        HashMap<String, List<City>> continentPopulations = getCapitalCitiesLargestToSmallestInContinent();

        // remove the excess entries first. Trim
        int runningTotal = 0;
        HashMap<String, List<City>> trimmedContinentPopulations = new HashMap<>();

        // Use a running total to ensure only n amount of values get added.
        for (Map.Entry<String, List<City>> entry : continentPopulations.entrySet()) {
            if (runningTotal >= n) {
                break;
            }
            trimmedContinentPopulations.put(entry.getKey(), entry.getValue());
            runningTotal++;
        }

        for(Map.Entry<String, List<City>> entry : trimmedContinentPopulations.entrySet()) {
            List<City> continentCountries = entry.getValue();
            System.out.println(entry.getKey() + ":");

            for(City city : continentCountries) {
                System.out.println(city.getName() + " " + city.getPopulation());
            }

            System.out.println("\n");
        }
    }

    public static HashMap<String, List<City>> getCapitalCitiesLargestToSmallestInRegion() {
        List<Country> countries = World.getInstance().getCountries();
        List<City> cities = World.getInstance().getCities();

        List<City> tempList;

        HashMap<String, List<City>> regionPopulations = new HashMap<>();

        // All the cities in a country organised by largest population to smallest.
        for(Country country : countries) {
            if(!regionPopulations.containsKey(country.getRegion())) {
                for(City city : cities) {
                    if(city.getCountryCode() == country.getCode()) {
                        regionPopulations.put(country.getRegion(),
                                new ArrayList<>(Arrays.asList(city)));
                    }
                }
            }
            else {
                for(City city : cities) {
                    if(city.getID() == country.getCapital()) {
                        tempList = regionPopulations.get(country.getRegion());
                        tempList.add(city);
                        regionPopulations.put(country.getRegion(), tempList);
                    }
                }
            }
        }

        City temp;

        for(Map.Entry<String, List<City>> entry : regionPopulations.entrySet()) {
            for(int i = 0; i < entry.getValue().size() - 1; i++) {
                for(int j = 0; j < entry.getValue().size() - i - 1; j++) {
                    if(entry.getValue().get(j).getPopulation() <
                            entry.getValue().get(j + 1).getPopulation()) {
                        temp = entry.getValue().get(j);
                        entry.getValue().set(j, entry.getValue().get(j + 1));
                        entry.getValue().set(j + 1, temp);
                    }
                }
            }
        }

        return regionPopulations;
    }

    public static void printCapitalCitiesLargestToSmallestInRegion() {
        HashMap<String, List<City>> regionPopulations = getCapitalCitiesLargestToSmallestInRegion();

        for(Map.Entry<String, List<City>> entry : regionPopulations.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public static void printTopNLargestCapitalCitiesInRegion(int n) {
        HashMap<String, List<City>> regionPopulations = getCapitalCitiesLargestToSmallestInRegion();

        // remove the excess entries first. Trim
        int runningTotal = 0;
        HashMap<String, List<City>> trimmedContinentPopulations = new HashMap<>();

        // Use a running total to ensure only n amount of values get added.
        for (Map.Entry<String, List<City>> entry : regionPopulations.entrySet()) {
            if (runningTotal >= n) {
                break;
            }
            trimmedContinentPopulations.put(entry.getKey(), entry.getValue());
            runningTotal++;
        }

        for(Map.Entry<String, List<City>> entry : trimmedContinentPopulations.entrySet()) {
            List<City> continentCountries = entry.getValue();
            System.out.println(entry.getKey() + ":");

            for(City city : continentCountries) {
                System.out.println(city.getName() + " " + city.getPopulation());
            }

            System.out.println("\n");
        }
    }

    public static void printPeopleInAndNotInCitiesInContinent() {
        HashMap<String, List<Country>> continentPopulations = getCountryPopulationLargestToSmallestInContinent();

        int totalCitiesPop;
        int notInCitiesPop;

        for (Map.Entry<String, List<Country>> entry : continentPopulations.entrySet()) {
            for (Country country : entry.getValue()) {
                totalCitiesPop = 0;
                notInCitiesPop = 0;

                for (City city : country.getCities()) {
                    totalCitiesPop += city.getPopulation();
                }
                notInCitiesPop = country.getPopulation() - totalCitiesPop;
                System.out.println(country.getName() + "\n");
                System.out.println("People in cities: " + totalCitiesPop + " People not in cities: " + notInCitiesPop + "\n");
            }
        }
    }

    public static void printPeopleInAndNotInCitiesInRegion() {

        HashMap<String, List<Country>> regionPopulations = getCountryPopulationLargestToSmallestInRegion();

        int totalCitiesPop;
        int notInCitiesPop;

        for (Map.Entry<String, List<Country>> entry : regionPopulations.entrySet()) {
            for (Country country : entry.getValue()) {
                totalCitiesPop = 0;
                notInCitiesPop = 0;

                for (City city : country.getCities()) {
                    totalCitiesPop += city.getPopulation();
                }
                notInCitiesPop = country.getPopulation() - totalCitiesPop;
                System.out.println(country.getName() + "\n");
                System.out.println("People in cities: " + totalCitiesPop + " People not in cities: " + notInCitiesPop + "\n");
            }
        }
    }

    public static void printPeopleInAndNotInCitiesInCountry() {

        HashMap<String, List<Country>> countryPopulations = getCountryPopulationLargestToSmallestInRegion();

        int totalCitiesPop;
        int notInCitiesPop;

        for (Map.Entry<String, List<Country>> entry : countryPopulations.entrySet()) {
            for (Country country : entry.getValue()) {
                totalCitiesPop = 0;
                notInCitiesPop = 0;

                for (City city : country.getCities()) {
                    totalCitiesPop += city.getPopulation();
                }
                notInCitiesPop = country.getPopulation() - totalCitiesPop;
                System.out.println(country.getName() + "\n");
                System.out.println("People in cities: " + totalCitiesPop + " People not in cities: " + notInCitiesPop + "\n");
            }
        }
    }

}