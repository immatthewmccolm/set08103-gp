package com.napier.gp;

import com.napier.gp.world.City;
import com.napier.gp.world.Country;
import com.napier.gp.world.CountryLanguage;
import com.napier.gp.world.World;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * Integration tests for PopulateWorld.TryPopulateWorld and the
 * PopulateCitiesAndLanguages() call inside it.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PopWorldIntegrationTest {

    private Connection connection;
    private World world;
    private boolean dbAvailable = false;

    @BeforeAll
    void setUpConnection() {
        try {
            // Same default connection details as your Db class
            String url = System.getenv().getOrDefault(
                    "DB_URL",
                    "jdbc:mysql://db:3306/world?allowPublicKeyRetrieval=true&useSSL=false"
            );
            String user = System.getenv().getOrDefault("DB_USER", "root");
            String password = System.getenv().getOrDefault("DB_PASSWORD", "example");

            connection = DriverManager.getConnection(url, user, password);
            dbAvailable = true;
        } catch (Exception e) {
            dbAvailable = false;
            System.out.println("Database not available – skipping PopulateWorld integration tests.");
        }
    }

    @AfterAll
    void tearDownConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @BeforeEach
    void resetWorldSingleton() {
        assumeTrue(dbAvailable, "Skipping PopulateWorld integration tests because DB is not available");

        world = World.getInstance();
        world.getCountries().clear();
        world.getCities().clear();
        world.getLanguages().clear();
    }

    /**
     * This test exercises BOTH:
     *  - TryPopulateWorld(connection): loads country, city, language data
     *  - PopulateCitiesAndLanguages(): links cities & languages onto countries
     */
    @Test
    void tryPopulateWorld_populatesAndLinksWorldData() {
        // Act: this also calls PopulateCitiesAndLanguages() at the end
        PopulateWorld.TryPopulateWorld(connection);

        // Step 1: raw data loaded from DB
        List<Country> countries = world.getCountries();
        List<City> cities = world.getCities();
        List<CountryLanguage> languages = world.getLanguages();

        assertFalse(countries.isEmpty(), "Countries should be loaded from the database");
        assertFalse(cities.isEmpty(), "Cities should be loaded from the database");
        assertFalse(languages.isEmpty(), "Languages should be loaded from the database");

        // Step 2: relations linked by PopulateCitiesAndLanguages()
        Country sample = countries.get(0);

        assertNotNull(sample.getCities(), "Country's cities list should not be null");
        assertNotNull(sample.getLanguages(), "Country's languages list should not be null");

        assertFalse(sample.getCities().isEmpty(),
                "PopulateCitiesAndLanguages() should attach at least one city to a country");
        assertFalse(sample.getLanguages().isEmpty(),
                "PopulateCitiesAndLanguages() should attach at least one language to a country");

        // Extra sanity: every attached city should have matching country code
        for (City city : sample.getCities()) {
            assertEquals(sample.getCode(), city.getCountryCode(),
                    "Attached city should have matching country code");
        }

        // And each attached language should have matching country code
        for (CountryLanguage lang : sample.getLanguages()) {
            assertEquals(sample.getCode(), lang.getCountryCode(),
                    "Attached language should have matching country code");
        }
    }

    /**
     * Optional extra: a smoke test just confirming it doesn't throw with a real DB.
     */
    @Test
    void tryPopulateWorld_doesNotThrowWithValidConnection() {
        assertDoesNotThrow(() -> PopulateWorld.TryPopulateWorld(connection),
                "TryPopulateWorld should not throw when the DB connection is valid");
    }
}
