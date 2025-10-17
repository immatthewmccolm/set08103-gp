package com.napier.gp;

import java.sql.Array.*;
import java.sql.ResultSet;
import java.sql.Statement;
import com.napier.gp.world.City.*;
import com.napier.gp.Db.*;

import java.sql.*;

// Contains all code related to Use-Case 3: Produce a report about the number of people who speak certain languages, to compare to data from other reports etc.
public class U3LanguagesReport {
    // Print the Use Case 3 report
    public void printU3LanguagesReport() {
        try {
            Connection con = null;

            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT DISTINCT(countrylanguage.language) AS `Language`, ROUND(SUM((((country.population)/100)*countrylanguage.percentage)), 0) AS `Population of Speakers`, CONCAT(ROUND(((SUM((((country.population)/100)*countrylanguage.percentage)))/(SELECT SUM(population) FROM country) * 100), 0),\"%\") AS `Percentage of World Speakers` FROM country JOIN countrylanguage ON (country.code=countrylanguage.CountryCode) WHERE language IN (SELECT language FROM countrylanguage WHERE LANGUAGE IN (\"Chinese\", \"English\", \"Hindi\", \"Spanish\", \"Arabic\")) GROUP BY LANGUAGE ORDER BY `Population of Speakers` DESC";

            ResultSet rset = stmt.executeQuery(strSelect);

            if (rset.next()) {
                String language = rset.getString("Language");
                int populationOfSpeakers = rset.getInt("Population of Speakers");
                String percentageOfSpeakers = rset.getString("Percentage of Speakers");

                System.out.println(language + " " + populationOfSpeakers + " " + percentageOfSpeakers);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

