package com.napier.gp.world.reports;

import java.util.*;

import com.napier.gp.HelperFunctions;
import com.napier.gp.world.City;
import com.napier.gp.world.Country;
import com.napier.gp.world.World;

// Contains all code related to Use-Case 1: Produce population data reports, so they can be used by the organisation etc.
public class U1PopulationDataReports {

    // Will call all Use Case 1 related reports in one function for ease of use
//    static void printAll() {
//        //Country reports
//        printCountryPopulationLargestToSmallestInWorld();
//        printTopNPopulatedCountriesInWorld(5);
//        printCountryPopulationLargestToSmallestInContinent();
//        printTopNPopulatedCountriesInContinent(5);
//        printCountryPopulationLargestToSmallestInRegion();
//        printTopNPopulatedCountriesInRegion(5);
//        //City Reports
//        printCityPopulationLargestToSmallestInWorld();
//        printTopNPopulatedCitiesInWorld(5);
//        printCityPopulationLargestToSmallestInContinent();
//        printTopNPopulatedCitiesInContinent(5);
//        printCityPopulationLargestToSmallestInRegion();
//        printTopNPopulatedCitiesInRegion(5);
//        printCityPopulationLargestToSmallestInCountry();
//        printTopNPopulatedCitiesInCountry(5);
//        printCitiesLargestToSmallestInDistrict();
//        printTopNLargestCitiesInDistrict(5);
//        //Capital City reports
//        printCapitalCitiesLargestToSmallestInWorld();
//        printTopNLargestCapitalCitiesInWorld(5);
//        printCapitalCitiesLargestToSmallestInContinent();
//        printTopNLargestCapitalCitiesInContinent(5);
//        printCapitalCitiesLargestToSmallestInRegion();
//        printTopNLargestCapitalCitiesInRegion(5);
//        // Population of people in and not in cities
//        printPeopleInAndNotInCitiesInContinent();
//        printPeopleInAndNotInCitiesInRegion();
//        printPeopleInAndNotInCitiesInCountry();
//    }

    // Prints a report on all the countries in the world organised by largest population to smallest.
    public static List<Country> getCountryPopulationLargestToSmallestInWorld() {
        List<Country> countries = World.getInstance().getCountries();

        return HelperFunctions.BubbleSortCountryByPop(countries);
    }

    public static void printCountryPopulationLargestToSmallestInWorld() {
        List<Country> countries = getCountryPopulationLargestToSmallestInWorld();

        for(Country country : countries) {
            System.out.println(country.getName() + " " + country.getPopulation());
        }
    }

    public static void printTopNPopulatedCountriesInWorld(int n) {
        List<Country> countries = getCountryPopulationLargestToSmallestInWorld();

        System.out.println("The top " + n + " most populated countries in the world: \n");

        for (int i = 0; i < n; i++) {
            Country currentCountry = countries.get(i);
            System.out.println(i+1 +". " + currentCountry.getName() + " " + currentCountry.getPopulation());
        }
    }

    // Prints a report on all the countries in a continent organised by largest population to smallest.
    public static HashMap<String, List<Country>> getCountryPopulationLargestToSmallestInContinent() {
        List<Country> countries = World.getInstance().getCountries();
        HashMap<String, List<Country>> continentPopulations = HelperFunctions.SeperateCountriesByProvidedStringFunction(countries, Country::getContinent);

        continentPopulations.replaceAll((key, vCountries) -> HelperFunctions.BubbleSortCountryByPop(vCountries));

        return continentPopulations;
    }

    public static void printCountryPopulationLargestToSmallestInContinent() {
        HashMap<String, List<Country>> continentPopulations = getCountryPopulationLargestToSmallestInContinent();

        for(Map.Entry<String, List<Country>> entry : continentPopulations.entrySet()) {
            List<Country> continentCountries = entry.getValue();
            System.out.println(entry.getKey() + ":");

            for(Country country : continentCountries) {
                System.out.println(country.getName() + " " + country.getPopulation());
            }

            System.out.println("\n");
        }
    }

    public static void printTopNPopulatedCountriesInContinent(int n) {
        HashMap<String, List<Country>> continentPopulations = getCountryPopulationLargestToSmallestInContinent();
        int count;

        //
        for (Map.Entry<String, List<Country>> entry : continentPopulations.entrySet()) {
            count = 0;
            System.out.println("The top " + n + " most populated countries in the continent of " + entry.getKey() + ":\n");
            List<Country> currentCountryList = entry.getValue();
            List<Country> trimmedList;

            if (currentCountryList.size() < n) {
                trimmedList = currentCountryList;
            } else {
                trimmedList = new ArrayList<>(currentCountryList.subList(0, n));
            }

            for (Country country : trimmedList) {
                System.out.println(count + 1 + ". " + country.getName() + " " + country.getPopulation());
                count++;
            }

            System.out.println("\n");
        }
    }

    public static HashMap<String, List<Country>> getCountryPopulationLargestToSmallestInRegion() {
        List<Country> countries = World.getInstance().getCountries();
        HashMap<String, List<Country>> regionPopulations = HelperFunctions.SeperateCountriesByProvidedStringFunction(countries, Country::getRegion);

        regionPopulations.replaceAll((key, vCountries) -> HelperFunctions.BubbleSortCountryByPop(vCountries));

        return regionPopulations;
    }

    // Prints a report on all the countries in a region organised by largest population to smallest.
    public static void printCountryPopulationLargestToSmallestInRegion() {
        HashMap<String, List<Country>> regionPopulations = getCountryPopulationLargestToSmallestInRegion();

        for(Map.Entry<String, List<Country>> entry : regionPopulations.entrySet()) {
            List<Country> regionCountries = entry.getValue();
            System.out.println(entry.getKey() + ":");

            for(Country country : regionCountries) {
                System.out.println(country.getName() + " " + country.getPopulation());
            }

            System.out.println("\n");
        }
    }

    public static void printTopNPopulatedCountriesInRegion(int n) {
        HashMap<String, List<Country>> regionPopulations = getCountryPopulationLargestToSmallestInRegion();
        int count;
        //
        for (Map.Entry<String, List<Country>> entry : regionPopulations.entrySet()) {
            count = 0;
            System.out.println("The top " + n + " most populated countries in the region of " + entry.getKey() + ":\n");
            List<Country> currentCountryList = entry.getValue();
            List<Country> trimmedList;

            if (currentCountryList.size() < n) {
                trimmedList = currentCountryList;
            } else {
                trimmedList = new ArrayList<>(currentCountryList.subList(0, n));
            }

            for (Country country : trimmedList) {
                System.out.println(count+1 + ". " + country.getName() + " " + country.getPopulation());
                count++;
            }

            System.out.println("\n");
        }
    }

    // Prints a report on all the cities in the world organised by largest population to smallest.
    public static List<City> getCitiesPopulationLargestToSmallestInWorld() {
        List<City> cities = World.getInstance().getCities();

        return HelperFunctions.BubbleSortCityByPop(cities);
    }

    public static void printCityPopulationLargestToSmallestInWorld() {
        System.out.println("Population of all cities in world (L - S): \n");
        List<City> cities = getCitiesPopulationLargestToSmallestInWorld();

        for(City city : cities) {
            System.out.println(city.getName() + " " + city.getPopulation());
        }
    }

    public static void printTopNPopulatedCitiesInWorld(int n) {
        List<City> cities = getCitiesPopulationLargestToSmallestInWorld();

        System.out.println("The top " + n + " most populated cities in the world: \n");

        for (int i = 0; i < n; i++) {
            City currentCity = cities.get(i);
            System.out.println(i+1 +". " + currentCity.getName() + " " + currentCity.getPopulation());
        }
    }

    // Prints a report on all the cities in a continent organised by largest population to smallest.
    public static HashMap<String, List<City>> getCityPopulationLargestToSmallestInContinent() {
        List<Country> countries = World.getInstance().getCountries();
        HashMap<String, List<City>> continentPopulations = HelperFunctions.SeperateCitiesByProvidedCountryStringFunction(countries, Country::getContinent);

        continentPopulations.replaceAll((key, vCities) -> HelperFunctions.BubbleSortCityByPop(vCities));

        return continentPopulations;
    }

    public static void printCityPopulationLargestToSmallestInContinent() {
        HashMap<String, List<City>> continentPopulations = getCityPopulationLargestToSmallestInContinent();

        for(Map.Entry<String, List<City>> entry : continentPopulations.entrySet()) {
            List<City> continentCountries = entry.getValue();
            System.out.println("Populated cities largest to smallest for the continent of: " + entry.getKey() + "\n");

            for(City city : continentCountries) {
                System.out.println(city.getName() + " " + city.getPopulation());
            }

            System.out.println("\n");
        }
    }

    public static void printTopNPopulatedCitiesInContinent(int n) {
        HashMap<String, List<City>> continentPopulations = getCityPopulationLargestToSmallestInContinent();
        int count;

        //
        for (Map.Entry<String, List<City>> entry : continentPopulations.entrySet()) {
            count = 0;
            System.out.println("The top " + n + " most populated cities in the continent of " + entry.getKey() + ":\n");
            List<City> currentCityList = entry.getValue();
            List<City> trimmedList;

            if (currentCityList.size() < n) {
                trimmedList = currentCityList;
            } else {
                trimmedList = new ArrayList<>(currentCityList.subList(0, n));
            }

            for (City city : trimmedList) {
                System.out.println(count+1 + ". " + city.getName() + " " + city.getPopulation());
                count++;
            }

            System.out.println("\n");
        }
    }

    public static HashMap<String, List<City>> getCityPopulationLargestToSmallestInRegion() {
        List<Country> countries = World.getInstance().getCountries();
        HashMap<String, List<City>> regionPopulations = HelperFunctions.SeperateCitiesByProvidedCountryStringFunction(countries, Country::getRegion);

        regionPopulations.replaceAll((key, vCities) -> HelperFunctions.BubbleSortCityByPop(vCities));

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
        int count;

        //
        for (Map.Entry<String, List<City>> entry : regionPopulations.entrySet()) {
            count = 0;
            System.out.println("The top " + n + " most populated cities in the region of " + entry.getKey() + ":\n");
            List<City> currentCityList = entry.getValue();
            List<City> trimmedList;

            if (currentCityList.size() < n) {
                trimmedList = currentCityList;
            } else {
                trimmedList = new ArrayList<>(currentCityList.subList(0, n));
            }

            for (City city : trimmedList) {
                System.out.println(count+1 + ". " + city.getName() + " " + city.getPopulation());
                count++;
            }

            System.out.println("\n");
        }
    }

    public static HashMap<String, List<City>> getCityPopulationLargestToSmallestInCountry() {
        List<Country> countries = World.getInstance().getCountries();
        HashMap<String, List<City>> countryPopulations = HelperFunctions.SeperateCitiesByProvidedCountryStringFunction(countries, Country::getName);

        countryPopulations.replaceAll((key, vCities) -> HelperFunctions.BubbleSortCityByPop(vCities));

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
        HashMap<String, List<City>> countryPopulations = getCityPopulationLargestToSmallestInCountry();
        int count;

        // Use a running total to ensure only n amount of values get added.
        for (Map.Entry<String, List<City>> entry : countryPopulations.entrySet()) {
            count = 0;
            System.out.println("The top " + n + " most populated cities in the region of " + entry.getKey() + ":\n");
            List<City> currentCityList = entry.getValue();
            List<City> trimmedList;

            if (currentCityList.size() < n) {
                trimmedList = currentCityList;
            } else {
                trimmedList = new ArrayList<>(currentCityList.subList(0, n));
            }

            for (City city : trimmedList) {
                System.out.println(count+1 + ". " + city.getName() + " " + city.getPopulation());
                count++;
            }

            System.out.println("\n");
        }

    }

    public static HashMap<String, List<City>> getCityPopulationLargestToSmallestInDistrict() {
        List<City> cities = World.getInstance().getCities();
        HashMap<String, List<City>> districtPopulations = HelperFunctions.SeperateCitiesByProvidedStringFunction(cities, City::getDistrict);

        districtPopulations.replaceAll((key, vCities) -> HelperFunctions.BubbleSortCityByPop(vCities));

        return districtPopulations;
    }

    public static void printCitiesLargestToSmallestInDistrict() {
        HashMap<String, List<City>> districtPopulations = getCityPopulationLargestToSmallestInDistrict();

        for(Map.Entry<String, List<City>> entry : districtPopulations.entrySet()) {
            System.out.println("District: " + entry.getKey() + "\n");

            for (City city : entry.getValue()) {
                System.out.println("Name: " + city.getName() + " Population: " + city.getPopulation());
            }
        }
    }

    public static void printTopNLargestCitiesInDistrict(int n) {
        HashMap<String, List<City>> districtPopulations = getCapitalCitiesLargestToSmallestInContinent();
        int count;

        // Use a running total to ensure only n amount of values get added.
        for (Map.Entry<String, List<City>> entry : districtPopulations.entrySet()) {
            count = 0;
            System.out.println("The top " + n + " most populated cities in the district of " + entry.getKey() + ":\n");
            List<City> currentCityList = entry.getValue();
            List<City> trimmedList;

            if (currentCityList.size() < n) {
                trimmedList = currentCityList;
            } else {
                trimmedList = new ArrayList<>(currentCityList.subList(0, n));
            }

            for (City city : trimmedList) {
                System.out.println(count+1 + ". " + city.getName() + " " + city.getPopulation());
                count++;
            }

            System.out.println("\n");
        }

    }

    // ll the capital cities in the world organised by largest population to smallest.
    public static List<City> getCapitalCitiesLargestToSmallestInWorld() {
        List<Country> countries = World.getInstance().getCountries();
        List<City> capitalCities = new ArrayList<>();

        for(Country country : countries) {
            for (City city : country.getCities()) {
                if (city.getID() == country.getCapital()) {
                    capitalCities.add(city);
                    break;
                }
            }
        }

        HelperFunctions.BubbleSortCityByPop(capitalCities);

        return capitalCities;
    }

    public static void printCapitalCitiesLargestToSmallestInWorld() {
        List<City> cities = getCapitalCitiesLargestToSmallestInWorld();
        System.out.println("The most populated cities in the world (L - S): \n");

        for(City city : cities) {
            System.out.println("City name: " + city.getName() + " Population: " + city.getPopulation());
        }
    }

    public static void printTopNLargestCapitalCitiesInWorld(int n) {
        List<City> cities = getCapitalCitiesLargestToSmallestInWorld();
        System.out.println("The top " + n + " populated cities in the world (L - S): \n");

        for (int i = 0; i < n; i++) {
            City currentCity = cities.get(i);
            System.out.println(i+1 + ". " + currentCity.getName() + currentCity.getPopulation());
        }
    }

    public static HashMap<String, List<City>> getCapitalCitiesLargestToSmallestInContinent() {
        List<Country> countries = World.getInstance().getCountries();
        List<City> tempList;
        HashMap<String, List<City>> continentPopulations = new HashMap<>();

        // All the capital cities in a continent organised by largest population to smallest.

        for(Country country : countries) {
            if(!continentPopulations.containsKey(country.getContinent())) {
                for(City city : country.getCities()) {
                    if(city.getID() == country.getCapital()) {
                        continentPopulations.put(country.getContinent(),
                                new ArrayList<>(Arrays.asList(city)));
                    }
                }
            }
            else {
                for(City city : country.getCities()) {
                    if(city.getID() == country.getCapital()) {
                        tempList = continentPopulations.get(country.getContinent());
                        tempList.add(city);
                        continentPopulations.put(country.getContinent(), tempList);
                    }
                }
            }
        }

        continentPopulations.replaceAll((key, vCities) -> HelperFunctions.BubbleSortCityByPop(vCities));

        return continentPopulations;
    }

    public static void printCapitalCitiesLargestToSmallestInContinent() {
        HashMap<String, List<City>> continentPopulations = getCapitalCitiesLargestToSmallestInContinent();

        for(Map.Entry<String, List<City>> entry : continentPopulations.entrySet()) {
            System.out.println("The most populated capital cities for the continent of: " + entry.getKey() + "\n");

            for (City city : entry.getValue()) {
                System.out.println("City Name: " + city.getName() + " Population: " + city.getPopulation());
            }

            System.out.println("\n");
        }
    }

    public static void printTopNLargestCapitalCitiesInContinent(int n) {
        HashMap<String, List<City>> continentPopulations = getCapitalCitiesLargestToSmallestInContinent();
        int count;

        for (Map.Entry<String, List<City>> entry : continentPopulations.entrySet()) {
            count = 0;
            System.out.println("The top " + n + " most populated capital cities in the continent of " + entry.getKey() + ":\n");
            List<City> currentCityList = entry.getValue();
            List<City> trimmedList;

            if (currentCityList.size() < n) {
                trimmedList = currentCityList;
            } else {
                trimmedList = new ArrayList<>(currentCityList.subList(0, n));
            }

            for (City city : trimmedList) {
                System.out.println(count+1 + ". " + city.getName() + " " + city.getPopulation());
                count++;
            }

            System.out.println("\n");
        }

    }

    public static HashMap<String, List<City>> getCapitalCitiesLargestToSmallestInRegion() {
        List<Country> countries = World.getInstance().getCountries();

        List<City> tempList;

        HashMap<String, List<City>> regionPopulations = new HashMap<>();

        // All the cities in a country organised by largest population to smallest.
        for(Country country : countries) {
            for(City city : country.getCities()) {
                if(city.getID() == country.getCapital()) {
                    if(!regionPopulations.containsKey(country.getRegion())) {
                        regionPopulations.put(country.getRegion(),
                                new ArrayList<>(Arrays.asList(city)));
                    } else {
                        tempList = regionPopulations.get(country.getRegion());
                        tempList.add(city);
                        regionPopulations.put(country.getRegion(), tempList);
                    }
                }
            }
        }

        regionPopulations.replaceAll((key, vCities) -> HelperFunctions.BubbleSortCityByPop(vCities));

        return regionPopulations;
    }

    public static void printCapitalCitiesLargestToSmallestInRegion() {
        HashMap<String, List<City>> regionPopulations = getCapitalCitiesLargestToSmallestInRegion();

        for(Map.Entry<String, List<City>> entry : regionPopulations.entrySet()) {
            System.out.println("The most populated capital cities for the region of: " + entry.getKey() + "\n");

            for (City city : entry.getValue()) {
                System.out.println("City Name: " + city.getName() + " Population: " + city.getPopulation());
            }

            System.out.println("\n");
        }
    }

    public static void printTopNLargestCapitalCitiesInRegion(int n) {
        HashMap<String, List<City>> regionPopulations = getCapitalCitiesLargestToSmallestInRegion();
        int count;

        for (Map.Entry<String, List<City>> entry : regionPopulations.entrySet()) {
            count = 0;
            System.out.println("The top " + n + " most populated capital cities in the region of " + entry.getKey() + ":\n");
            List<City> currentCityList = entry.getValue();
            List<City> trimmedList;

            if (currentCityList.size() < n) {
                trimmedList = currentCityList;
            } else {
                trimmedList = new ArrayList<>(currentCityList.subList(0, n));
            }

            for (City city : trimmedList) {
                System.out.println(count+1 + ". " + city.getName() + " " + city.getPopulation());
                count++;
            }

            System.out.println("\n");
        }
    }

    public static void printPeopleInAndNotInCitiesInContinent() {
        HashMap<String, List<Country>> continentPopulations = getCountryPopulationLargestToSmallestInContinent();

        long totalCitiesPop;
        long notInCitiesPop;
        long totalContinentPop;

        for (Map.Entry<String, List<Country>> entry : continentPopulations.entrySet()) {
            totalContinentPop = 0;
            totalCitiesPop = 0;
            notInCitiesPop = 0;

            System.out.println("Population of people in and not in cities for the continent of: " + entry.getKey() + "\n");
            for (Country country : entry.getValue()) {
                totalContinentPop += country.getPopulation();

                for (City city : country.getCities()) {
                    totalCitiesPop += city.getPopulation();
                }
            }

            notInCitiesPop = totalContinentPop - totalCitiesPop;
            System.out.println("Total Population of People: " + totalContinentPop + " People in cities: " + totalCitiesPop + " People not in cities: " + notInCitiesPop + "\n");
        }
    }

    public static void printPeopleInAndNotInCitiesInRegion() {
        HashMap<String, List<Country>> regionPopulations = getCountryPopulationLargestToSmallestInRegion();
        int totalRegionPop;
        int totalCitiesPop;
        int notInCitiesPop;

        for (Map.Entry<String, List<Country>> entry : regionPopulations.entrySet()) {
            totalRegionPop = 0;
            totalCitiesPop = 0;
            notInCitiesPop = 0;

            System.out.println("Population of people in and not in cities for the region of: " + entry.getKey() +"\n");

            for (Country country : entry.getValue()) {
                totalRegionPop += country.getPopulation();

                for (City city : country.getCities()) {
                    totalCitiesPop += city.getPopulation();
                }
            }

            notInCitiesPop = totalRegionPop - totalCitiesPop;

            System.out.println("Total Population of People: " + totalRegionPop + " People in Cities " + totalCitiesPop + " People not in cities: " + notInCitiesPop + "\n");
        }
    }

    public static void printPeopleInAndNotInCitiesInCountry() {
       List<Country> countryPopulations = getCountryPopulationLargestToSmallestInWorld();
        int totalCitiesPop;
        int notInCitiesPop;

        for (Country country: countryPopulations) {
            System.out.println("Population of people in and not in cities for the country of: " + country.getName() +"\n");
            totalCitiesPop = 0;
            notInCitiesPop = 0;

            for (City city : country.getCities()) {
                totalCitiesPop += city.getPopulation();
            }
            notInCitiesPop = country.getPopulation() - totalCitiesPop;
            System.out.println("Total Population of People: " +  country.getPopulation() + " People in cities: " + totalCitiesPop + " People not in cities: " + notInCitiesPop + "\n");
        }
    }
}

