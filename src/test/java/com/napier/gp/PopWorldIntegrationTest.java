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
 * Integration tests for PopulateWorld when DB runs in a separate Docker container.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PopWorldIntegrationTest {

    private Connection connection;
    private World world;
    private boolean dbAvailable = false;

    // -------------------------------------------------------
    // Connect using Docker service name "db"
    // -------------------------------------------------------
    @BeforeAll
    void connectToDockerDatabase() {
        try {
            // These match your Docker container config
            String url = System.getenv().getOrDefault(
                    "DB_URL",
                    "jdbc:mysql://db:3306/world?allowPublicKeyRetrieval=true&useSSL=false"
            );
            String user = System.getenv().getOrDefault("DB_USER", "root");
            String password = System.getenv().getOrDefault("DB_PASSWORD", "example");

            connection = DriverManager.getConnection(url, user, password);
            dbAvailable = true;
        }
        catch (SQLException e) {
            dbAvailable = false;
            System.out.println("Docker MySQL container not available — skipping integration tests.");
        }
    }

    @AfterAll
    void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @BeforeEach
    void resetWorld() {
        assumeTrue(dbAvailable, "Skipping PopulateWorld integration tests – Docker DB not available");

        world = World.getInstance();
        world.getCountries().clear();
        world.getCities().clear();
        world.getLanguages().clear();
    }

    // -------------------------------------------------------
    // MAIN POPULATION TEST
    // -------------------------------------------------------

    @Test
    void tryPopulateWorld_loadsDatabaseTablesIntoWorld() {
        PopulateWorld.TryPopulateWorld(connection);

        assertFalse(world.getCountries().isEmpty(), "Countries should be populated from DB");
        assertFalse(world.getCities().isEmpty(), "Cities should be populated from DB");
        assertFalse(world.getLanguages().isEmpty(), "Languages should be populated from DB");
    }

    // -------------------------------------------------------
    // RELATIONSHIP LINKING TEST
    // -------------------------------------------------------

    @Test
    void tryPopulateWorld_linksCitiesAndLanguagesToTheirCountries() {
        PopulateWorld.TryPopulateWorld(connection);

        Country sample = world.getCountries().get(0);

        assertNotNull(sample.getCities(), "Cities list should not be null");
        assertNotNull(sample.getLanguages(), "Languages list should not be null");

        assertFalse(sample.getCities().isEmpty(),
                "Country should have cities linked after PopulateCitiesAndLanguages");

        assertFalse(sample.getLanguages().isEmpty(),
                "Country should have languages linked after PopulateCitiesAndLanguages");
    }

    // -------------------------------------------------------
    // KNOWN DATA TEST (STANDARD world DB)
    // -------------------------------------------------------

    @Test
    void tryPopulateWorld_containsKnownWorldData() {
        PopulateWorld.TryPopulateWorld(connection);

        boolean hasGBR = world.getCountries().stream()
                .anyMatch(c -> "GBR".equals(c.getCode()));

        boolean hasLondon = world.getCities().stream()
                .anyMatch(c ->"London".equalsIgnoreCase(c.getName()));

        assertTrue(hasGBR, "Country GBR should exist");
        assertTrue(hasLondon, "City London should exist");
    }

    // -------------------------------------------------------
    // SAFETY TEST
    // -------------------------------------------------------

    @Test
    void tryPopulateWorld_doesNotThrow() {
        assertDoesNotThrow(() -> PopulateWorld.TryPopulateWorld(connection),
                "TryPopulateWorld should not throw with valid Docker DB connection");
    }
}
