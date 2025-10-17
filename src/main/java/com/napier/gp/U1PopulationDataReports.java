package com.napier.gp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

// Contains all code related to Use-Case 1: Produce propulation data reports, so they can be used by the organisation etc.
public class U1PopulationDataReports {

        // Will call all Use Case 1 related reports in one function for ease of use

        /* For Code Review 2: Some SQL statements from Use Case 1 implemented
         * to fulfil requirement of approximately 25% of all SQL statements
         * being implemented.
         */
        public void printAllU1PopulationReports() {
            printCountryPopulationLargestToSmallestInWorld();
            printCountryPopulationLargestToSmallestInContinent();
            printCountryPopulationLargestToSmallestInRegion();
        }

        // Prints a report on all the countries in the world organised by largest population to smallest.
        public void printCountryPopulationLargestToSmallestInWorld() {
            try {
                Connection con = null;

                Statement stmt = con.createStatement();

                String strSelect =
                        "SELECT * FROM country ORDER BY population DESC";

                ResultSet rset = stmt.executeQuery(strSelect);

                if (rset.next()) {
                    int code = rset.getInt("Code");
                    String countryName = rset.getString("Name");
                    String continentName = rset.getString("Continent");
                    String regionName = rset.getString("Region");
                    int surfaceArea =  rset.getInt("SurfaceArea");
                    int indepYear =  rset.getInt("IndepYear");
                    int countryPopulation = rset.getInt("Population");
                    double lifeExpectancy = rset.getDouble("LifeExpectancy");
                    double gnp = rset.getDouble("GNP");
                    double gnpOld = rset.getDouble("GNPOld");
                    String localName = rset.getString("LocalName");
                    String governmentForm = rset.getString("GovernmentForm");
                    String headOfState = rset.getString("HeadOfState");
                    int capital = rset.getInt("Capital");
                    String code2 = rset.getString("Code2");

                    System.out.println(code + " " + countryName + " " + continentName + " " + regionName + " "
                            + surfaceArea + " " + indepYear + " " + countryPopulation + " " + lifeExpectancy + " "
                            + gnp + " " + gnpOld + " " + localName + " " + governmentForm + " " + headOfState + " "
                            + capital + " " + code2);
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        // Prints a report on all the countries in a continent organised by largest population to smallest.
        public void printCountryPopulationLargestToSmallestInContinent() {
            try {
                Connection con = null;

                Statement stmt = con.createStatement();

                String strSelect =
                        "SELECT * FROM country ORDER BY continent ASC, population DESC";

                ResultSet rset = stmt.executeQuery(strSelect);

                if (rset.next()) {
                    int code = rset.getInt("Code");
                    String countryName = rset.getString("Name");
                    String continentName = rset.getString("Continent");
                    String regionName = rset.getString("Region");
                    int surfaceArea =  rset.getInt("SurfaceArea");
                    int indepYear =  rset.getInt("IndepYear");
                    int countryPopulation = rset.getInt("Population");
                    double lifeExpectancy = rset.getDouble("LifeExpectancy");
                    double gnp = rset.getDouble("GNP");
                    double gnpOld = rset.getDouble("GNPOld");
                    String localName = rset.getString("LocalName");
                    String governmentForm = rset.getString("GovernmentForm");
                    String headOfState = rset.getString("HeadOfState");
                    int capital = rset.getInt("Capital");
                    String code2 = rset.getString("Code2");

                    System.out.println(code + " " + countryName + " " + continentName + " " + regionName + " "
                            + surfaceArea + " " + indepYear + " " + countryPopulation + " " + lifeExpectancy + " "
                            + gnp + " " + gnpOld + " " + localName + " " + governmentForm + " " + headOfState + " "
                            + capital + " " + code2);
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        // Prints a report on all the countries in a region organised by largest population to smallest.
        public void printCountryPopulationLargestToSmallestInRegion() {
            try {
                Connection con = null;

                Statement stmt = con.createStatement();

                String strSelect =
                        "SELECT * FROM country ORDER BY region ASC, population DESC\n";

                ResultSet rset = stmt.executeQuery(strSelect);

                if (rset.next()) {
                    int code = rset.getInt("Code");
                    String countryName = rset.getString("Name");
                    String continentName = rset.getString("Continent");
                    String regionName = rset.getString("Region");
                    int surfaceArea =  rset.getInt("SurfaceArea");
                    int indepYear =  rset.getInt("IndepYear");
                    int countryPopulation = rset.getInt("Population");
                    double lifeExpectancy = rset.getDouble("LifeExpectancy");
                    double gnp = rset.getDouble("GNP");
                    double gnpOld = rset.getDouble("GNPOld");
                    String localName = rset.getString("LocalName");
                    String governmentForm = rset.getString("GovernmentForm");
                    String headOfState = rset.getString("HeadOfState");
                    int capital = rset.getInt("Capital");
                    String code2 = rset.getString("Code2");

                    System.out.println(code + " " + countryName + " " + continentName + " " + regionName + " "
                            + surfaceArea + " " + indepYear + " " + countryPopulation + " " + lifeExpectancy + " "
                            + gnp + " " + gnpOld + " " + localName + " " + governmentForm + " " + headOfState + " "
                            + capital + " " + code2);
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
}

