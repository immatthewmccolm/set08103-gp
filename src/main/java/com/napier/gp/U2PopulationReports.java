package com.napier.gp;

import java.sql.Array.*;
import java.sql.ResultSet;
import java.sql.Statement;
import com.napier.gp.world.City.*;
import com.napier.gp.Db.*;

import java.sql.*;

// Contains all code related to Use-Case 2: Produce population reports as a baseline to compare to other reports etc.
public class U2PopulationReports {
    // Will call all Use Case 2 related reports in one function for ease of use
    public void printAllU2PopulationReports() {
        printWorldPopulation();
        printContinentPopulations();
        printRegionPopulations();
        printCountryPopulations();
        printDistrictPopulations();
        printCityPopulations();
    }

    // Prints a report on the total population of the world
    public void printWorldPopulation() {
        try {
            Connection con = null;

            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT SUM(population) AS `World Population` FROM country";

            ResultSet rset = stmt.executeQuery(strSelect);

            if (rset.next()) {
                String worldPopulation = rset.getString("World Population");
                System.out.println(worldPopulation);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Prints a report on the total population of each of the 7 continents
    public void printContinentPopulations() {
        try {
            Connection con = null;

            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT DISTINCT(continent) AS Continent, SUM(population) AS Population FROM country WHERE continent IN (SELECT continent FROM country) GROUP BY continent ORDER BY population DESC";

            ResultSet rset = stmt.executeQuery(strSelect);

            if (rset.next()) {
                String continentName = rset.getString("Continent");
                int population = rset.getInt("Population");

                System.out.println(continentName + " " + population);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Prints a report on the total population of each of the regions of the world
    public void printRegionPopulations() {
        try {
            Connection con = null;

            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT DISTINCT(region) AS Region, SUM(population) AS Population FROM country WHERE region IN (SELECT region FROM country) GROUP BY region ORDER BY population DESC";

            ResultSet rset = stmt.executeQuery(strSelect);

            if (rset.next()) {
                String regionName = rset.getString("Region");
                int population = rset.getInt("Population");

                System.out.println(regionName + " " + population);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Prints a report on the total population of each of the countries of the world
    public void printCountryPopulations() {
        try {
            Connection con = null;

            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT DISTINCT(name) AS Country, population AS Population FROM country ORDER BY population DESC";

            ResultSet rset = stmt.executeQuery(strSelect);

            if (rset.next()) {
                String countryName = rset.getString("Country");
                int population = rset.getInt("Population");

                System.out.println(countryName + " " + population);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Prints a report on the total population of each of the districts of the world
    public void printDistrictPopulations() {
        try {
            Connection con = null;

            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT DISTINCT(district) AS District, SUM(population) AS Population FROM city WHERE district IN (SELECT district FROM city) GROUP BY district ORDER BY population DESC";

            ResultSet rset = stmt.executeQuery(strSelect);

            if (rset.next()) {
                String districtName = rset.getString("District");
                int population = rset.getInt("Population");

                System.out.println(districtName + " " + population);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Prints a report on the total population of each of the cities of the world
    public void printCityPopulations() {
        try {
            Connection con = null;

            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT city.name, population FROM city ORDER BY population DESC";

            ResultSet rset = stmt.executeQuery(strSelect);

            if (rset.next()) {
                String cityName = rset.getString("City");
                int population = rset.getInt("Population");

                System.out.println(cityName + " " + population);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}



