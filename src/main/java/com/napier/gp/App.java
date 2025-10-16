/*
 * App.java
 * Main Class File used to run the program.
 */

package com.napier.gp;
import static com.napier.gp.Menu.*;
import static com.napier.gp.UserInput.*;
import java.sql.*;


/**
 * Main class used to run the program.
 */
public class App {
    public static void main(String[] args) {
        // Loads database
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        // Connection to the database
        Connection con = null;
        int retries = 100;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                // Wait a bit
                Thread.sleep(10000);
                // Exit for loop
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }

        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }


/*

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

        } while (true);*/
    }
}