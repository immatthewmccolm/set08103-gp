/*
 * App.java
 * Main Class File used to run the program.
 */

package com.napier.gp;
import static com.napier.gp.Menu.*;
import static com.napier.gp.UserInput.*;
import static com.napier.gp.world.City.*;
import static com.napier.gp.world.Country.*;
import static com.napier.gp.world.CountryLanguage.*;
import static com.napier.gp.Db.*;
import java.sql.*;
import java.util.List;
import com.napier.gp.world.*;


/**
 * Main class used to run the program.
 */
public class App {
    public static void main(String[] args) {

        Db a = new Db();

        a.connect();

        a.populateWorld();

        a.disconnect();

        // Prints Page Title
        pageTitle("Country Data Analysis Software");

        // Prints Menu Items
        menuItem(1, "All Countries (Ordered by Population)");

        // Gets user input and loads the appropriate method, otherwise loops until a valid input is received
        do {
            String menuInput = getUserInput("Enter your menu choice");

            if (menuInput.equals("1")) {
                // call method to run report
                break; // exit the loop after a valid option
            } else {
                System.out.println("Please enter a valid menu option.\n");
            }

        } while (true);
    }
}