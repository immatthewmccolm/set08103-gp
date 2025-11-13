package com.napier.gp;

import com.napier.gp.world.City;
import com.napier.gp.world.Country;
import com.napier.gp.world.CountryLanguage;
import com.napier.gp.world.World;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PopulateWorld {
    static void TryPopulateWorld(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM country;";
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
                String code = resultSet.getString("Code");
                String countryName = resultSet.getString("Name");
                String continentName = resultSet.getString("Continent");
                String regionName = resultSet.getString("Region");
                double surfaceArea =  resultSet.getDouble("SurfaceArea");
                int indepYear =  resultSet.getInt("IndepYear");
                int countryPopulation = resultSet.getInt("Population");
                double lifeExpectancy = resultSet.getDouble("LifeExpectancy");
                double gnp = resultSet.getDouble("GNP");
                double gnpOld = resultSet.getDouble("GNPOld");
                String localName = resultSet.getString("LocalName");
                String governmentForm = resultSet.getString("GovernmentForm");
                String headOfState = resultSet.getString("HeadOfState");
                int capital = resultSet.getInt("Capital");
                String code2 = resultSet.getString("Code2");


                World.getInstance().addCountry(new Country(code, countryName, continentName,
                        regionName, surfaceArea, indepYear, countryPopulation,
                        lifeExpectancy, gnp, gnpOld, localName, governmentForm,
                        headOfState, capital, code2));
            }

            sql = "SELECT * FROM city;";
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("Name");
                String countryCode = resultSet.getString("CountryCode");
                String district = resultSet.getString("District");
                int population = resultSet.getInt("Population");

                World.getInstance().addCity(new City(id, name, countryCode,
                        district, population));
            }

            sql = "SELECT * FROM countrylanguage;";
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
                String countryCode = resultSet.getString("CountryCode");
                String language = resultSet.getString("Language");
                String isOfficial = resultSet.getString("IsOfficial");
                Double percentage = resultSet.getDouble("Percentage");

                World.getInstance().addLanguage(new CountryLanguage(countryCode, language,
                        isOfficial, percentage));
            }

            PopulateCitiesAndLanguages();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    static void PopulateCitiesAndLanguages() {
        for(Country country : World.getInstance().getCountries()) {
            for(City city : World.getInstance().getCities()) {
                if(city.getCountryCode().equals(country.getCode())) {
                    country.addCity(city);
                }
            }
            for(CountryLanguage language : World.getInstance().getLanguages()) {
                if(language.getCountryCode().equals(country.getCode())) {
                    country.addLanguage(language);
                }
            }
        }
    }
}