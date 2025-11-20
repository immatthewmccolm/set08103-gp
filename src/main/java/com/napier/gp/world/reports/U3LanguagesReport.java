package com.napier.gp.world.reports;

import com.napier.gp.world.Country;
import com.napier.gp.world.CountryLanguage;
import com.napier.gp.world.World;

import java.util.*;

public class U3LanguagesReport {

    //
    public static void print() {

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

        List<Map.Entry<String, Double>> percentages = getWorldwideLanguageSpeakersPercentages();

        for(Map.Entry<String, Long> entry : requiredLanguagePopulations.entrySet()) {
            percentages.add(new AbstractMap.SimpleEntry<String, Double>(entry.getKey(), (double)Math.round((double)entry.getValue() / (double)worldPopulation * 1000) / 10));
        }

        Map.Entry<String, Double> temp;

        for(int i = 0; i < percentages.size() - 1; i++) {
            for(int j = 0; j < percentages.size() - i - 1; j++) {
                if(percentages.get(j).getValue() < percentages.get(j + 1).getValue()) {
                    temp = percentages.get(j);
                    percentages.set(j, percentages.get(j + 1));
                    percentages.set(j + 1, temp);
                }
            }
        }

        for(Map.Entry<String, Double> entry : percentages) {
            for(Map.Entry<String, Long> entry2 : requiredLanguagePopulations.entrySet()) {
                if(entry2.getKey().equals(entry.getKey())) {
                    System.out.println(entry.getKey() + " " + entry2.getValue() + " " + entry.getValue() + "%");
                }
            }
        }

    }

    //
    public static List<Map.Entry<String, Double>>getWorldwideLanguageSpeakersPercentages() {

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

        List<Map.Entry<String, Double>> percentages = new ArrayList<Map.Entry<String, Double>>();

        for(Map.Entry<String, Long> entry : requiredLanguagePopulations.entrySet()) {
            percentages.add(new AbstractMap.SimpleEntry<String, Double>(entry.getKey(), (double)Math.round((double)entry.getValue() / (double)worldPopulation * 1000) / 10));
        }

        Map.Entry<String, Double> temp;

        for(int i = 0; i < percentages.size() - 1; i++) {
            for(int j = 0; j < percentages.size() - i - 1; j++) {
                if(percentages.get(j).getValue() < percentages.get(j + 1).getValue()) {
                    temp = percentages.get(j);
                    percentages.set(j, percentages.get(j + 1));
                    percentages.set(j + 1, temp);
                }
            }
        }

        return percentages;
    }

    //
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

