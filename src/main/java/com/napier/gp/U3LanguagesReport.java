package com.napier.gp;

import java.sql.Array.*;
import java.sql.ResultSet;
import java.sql.Statement;
import com.napier.gp.world.City.*;
import com.napier.gp.Db.*;
import com.napier.gp.world.Country;
import com.napier.gp.world.CountryLanguage;
import com.napier.gp.world.World;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class U3LanguagesReport {
    private static Object languagePopulations;

    static void print() {

        // Step 3: Get results only for key values: Chinese, English, Hindi, Spanish, Arabic
        // Step 5: Order it so the languages with the largest pop of speakers are listed first
        // printCountryPopulationLargestToSmallestInWorld
        HashMap<String, Long> allLanguagePopulations = getWorldwideLanguageSpeakers();
        HashMap<String, Long> requiredLanguagePopulations = new HashMap<>();

        requiredLanguagePopulations.put("Chinese", allLanguagePopulations.get("Chinese"));
        requiredLanguagePopulations.put("English", allLanguagePopulations.get("English"));
        requiredLanguagePopulations.put("Hindi", allLanguagePopulations.get("Hindi"));
        requiredLanguagePopulations.put("Spanish", allLanguagePopulations.get("Spanish"));
        requiredLanguagePopulations.put("Arabic", allLanguagePopulations.get("Arabic"));

        // ALTER U1 METHOD TO RETURN AND NOT PRINT. ALTER ALL TO HAVE SEPARATE PRINT AND VALUE METHODS
        long worldPopulation = 0;

        for(Country country : World.getInstance().getCountries()) {
            worldPopulation += country.getPopulation();
        }
        // world pop end

        List<Long> percentages = new ArrayList<>();

        for(Map.Entry<String, Long> entry : requiredLanguagePopulations.entrySet()) {
            percentages.add(entry.getValue() / worldPopulation * 100);
        }
/*

        ArrayList<Long> allPopulations = new ArrayList<>();
        Map<String, Long> sortedRequiredLanguages = new HashMap<>();

        for (Map.Entry<String, Long> entry : requiredLanguagePopulations.entrySet()) {
            allPopulations.add(entry.getValue());
        }

        Long temp;

        for(int i = 0; i < allPopulations.size() - 1; i++) {
            for (int j = 0; j < allPopulations.size() - i - 1; j++) {
                if(allPopulations.get(j) < allPopulations.get(j + 1)) {
                    temp = allPopulations.get(j);
                    allPopulations.set(j, allPopulations.get(j + 1));
                    allPopulations.set(j + 1, temp);
                }
            }
        }




        //Sorted map
        Map<String, Long> sortedRequiredLanguages = new HashMap<>();

        int index = 0;

        for (Map.Entry<String, Long> entry : requiredLanguagePopulations.entrySet()) {
            if (allPopulations.get(index).equals(entry.getValue())) {
                sortedRequiredLanguages.put(entry.getKey(), entry.getValue());
            }

            index++;
        }

        // Print sorted map
        for (Map.Entry<String, Long> entry : sortedRequiredLanguages.entrySet()) {
            percentageInstance = ((entry.getValue() / worldPopulation) * 100);
            System.out.println("Language: " + entry.getKey() + " Number of Speakers: " + entry.getValue() + " Percentage of World Speakers: " + percentageInstance + "%\n");
        }

         */

    }

    public static HashMap<String, Long> getWorldwideLanguageSpeakers() {
        List<Country> countryList = World.getInstance().getCountries();
        HashMap<String, Double> languagePopulations = new HashMap<>();
        long totalPop;

        for(Country country : countryList) {
            for(CountryLanguage language : country.getLanguages()) {
                if(!languagePopulations.containsKey(language.getLanguage())) {
                    languagePopulations.put(language.getLanguage(),
                            (language.getPercentage() / 100) * country.getPopulation());
                }
                else {
                    languagePopulations.put(language.getLanguage(),
                            ((languagePopulations.get(language.getLanguage()))
                            + (language.getPercentage() / 100) * country.getPopulation()));
                }
            }
        }

        HashMap<String, Long> languagePopulationsRounded = new HashMap<>();

        for(Map.Entry<String, Double> entry : languagePopulations.entrySet()) {
            languagePopulationsRounded.put(entry.getKey(), Math.round(entry.getValue()));
        }

/*
        for (Country country : countryList) {

            for (CountryLanguage countryLanguage : countryLanguages) {
            totalPop = 0L;
                if (countryLanguage.getCountryCode().equals(country.getCode())) {
                    totalPop += country.getPopulation();
                }
            }
            if (!languagePopulations.containsKey(countryLanguage.getLanguage())) {
                languagePopulations.put(countryLanguage.getLanguage(), totalPop);
            } else {
                languagePopulations.put(countryLanguage.getLanguage(),
                        languagePopulations.get(countryLanguage.getLanguage()) + totalPop);
            }

        }
        */

        return languagePopulationsRounded;


    }
}

