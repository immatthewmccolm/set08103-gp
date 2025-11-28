package com.napier.gp;

import com.napier.gp.world.City;
import com.napier.gp.world.Country;
import com.napier.gp.world.CountryLanguage;
import com.napier.gp.world.World;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class HelperFunctions {

    public static List<Integer> BubbleSortInteger(List<Integer> integerList) {
        int temp;

        for (int i = 0; i < integerList.size() - 1; i++) {
            for (int j = 0; j < integerList.size() - i - 1; j++) {
                if (integerList.get(j) <
                        integerList.get(j + 1)) {
                    temp = integerList.get(j);
                    integerList.set(j, integerList.get(j + 1));
                    integerList.set(j + 1, temp);
                }
            }
        }

        return integerList;
    }

    public static List<City> BubbleSortCityByPop(List<City> cityList) {
        City temp;

        for (int i = 0; i < cityList.size() - 1; i++) {
            for (int j = 0; j < cityList.size() - i - 1; j++) {
                if (cityList.get(j).getPopulation() <
                        cityList.get(j + 1).getPopulation()) {
                    temp = cityList.get(j);
                    cityList.set(j, cityList.get(j + 1));
                    cityList.set(j + 1, temp);
                }
            }
        }

        return cityList;
    }

    public static List<Country> BubbleSortCountryByPop(List<Country> countryList) {
        Country temp;

        for (int i = 0; i < countryList.size() - 1; i++) {
            for (int j = 0; j < countryList.size() - i - 1; j++) {
                if (countryList.get(j).getPopulation() <
                        countryList.get(j + 1).getPopulation()) {
                    temp = countryList.get(j);
                    countryList.set(j, countryList.get(j + 1));
                    countryList.set(j + 1, temp);
                }
            }
        }

        return countryList;
    }

    public static HashMap<String, List<Country>> SeperateCountriesByProvidedStringFunction(List<Country> countries, Function<Country, String> function) {
        HashMap<String, List<Country>> populations = new HashMap<>();
        List<Country> tempList;

        for (Country country : countries) {
            if (!populations.containsKey(function.apply(country))) {
                populations.put(function.apply(country),
                        new ArrayList<>(Arrays.asList((country))));
            } else {
                tempList = populations.get(function.apply(country));
                tempList.add(country);
                populations.put(function.apply(country), tempList);
            }
        }

        return populations;
    }

    public static HashMap<String, List<City>> SeperateCitiesByProvidedCountryStringFunction(List<Country> countries, Function<Country, String> function) {
        HashMap<String, List<City>> populations = new HashMap<>();
        List<City> tempList;


        for (Country country : countries) {
            for (City city : country.getCities()) {
                if (!populations.containsKey(function.apply(country))) {
                    populations.put(function.apply(country),
                            new ArrayList<>(Arrays.asList(city)));
                } else {
                    tempList = populations.get(function.apply(country));
                    tempList.add(city);
                    populations.put(function.apply(country), tempList);
                }
            }
        }

        return populations;
    }

    public static HashMap<String, List<City>> SeperateCitiesByProvidedStringFunction(List<City> cities, Function<City, String> function) {
        HashMap<String, List<City>> populations = new HashMap<>();
        List<City> tempList;

        for (City city : cities) {
            if (!populations.containsKey(function.apply(city))) {
                populations.put(function.apply(city),
                        new ArrayList<>(Arrays.asList(city)));
            } else {
                tempList = populations.get(function.apply(city));
                tempList.add(city);
                populations.put(function.apply(city), tempList);
            }
        }

        return populations;
    }

    public static HashMap<String, Long> getGenericU2Populations(Function<Country, String> function) {
        List<Country> countries = World.getInstance().getCountries();
        HashMap<String, Long> populations = new HashMap<>();

        for (Country country : countries) {
            if (!populations.containsKey(function.apply(country))) {
                populations.put(function.apply(country), (long) country.getPopulation());
            } else {
                populations.put(function.apply(country),
                        populations.get(country.getRegion()) + (long) country.getPopulation());
            }
        }

        return populations;
    }

    public static HashMap<String, Long> getCityDistrictPopulation() {
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

        return districtPopulations;
    }

}
