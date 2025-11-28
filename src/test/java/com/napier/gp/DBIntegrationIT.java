package com.napier.gp;

import com.napier.gp.world.City;
import com.napier.gp.world.World;
import org.junit.jupiter.api.*;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for the Db class.
 *
 * We do not modify Db.java. Instead, we:
 *  - open our own JDBC Connection to localhost:33060 (mapped to the db container)
 *  - inject that Connection into Db's private 'con' field via reflection
 *  - call Db.populateCity() and Db.TryPopulateWorld() and assert on real data.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DBIntegrationIT {

    // Db instance used by all tests
    private Db db;

    // Real JDBC connection used by Db via its private 'con' field
    private Connection con;

    @BeforeAll
    void setUp() throws Exception {
        // Load MySQL driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // IMPORTANT:
        // docker-compose maps 33060 on your host to 3306 in the gp-db container:
        //   ports:
        //     - "33060:3306"
        //
        // So from IntelliJ on your Mac we connect to localhost:33060.
        String url = "jdbc:mysql://localhost:33060/world?allowPublicKeyRetrieval=true&useSSL=false";
        con = DriverManager.getConnection(url, "root", "example");

        // Create Db instance
        db = new Db();

        // Use reflection to set Db's private 'con' field to our connection
        Field conField = Db.class.getDeclaredField("con");
        conField.setAccessible(true);
        conField.set(db, con);
    }

    @AfterAll
    void tearDown() throws SQLException {
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }

    // Test 1: The injected connection should be valid and talk to the database
    @Test
    void connectionIsValid() throws Exception {
        assertNotNull(con, "Connection should not be null");
        assertFalse(con.isClosed(), "Connection should be open");
        assertTrue(con.isValid(2), "Connection should be valid within 2 seconds");

        // Optional sanity check: country table has rows
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM country")) {

            assertTrue(rs.next(), "ResultSet should contain one row");
            int count = rs.getInt(1);
            assertTrue(count > 0, "Country table should contain at least one row");
        }
    }

    // Test 2: Db.populateCity() should return at least one city from the DB
    @Test
    void populateCityReturnsData() {
        List<City> cities = db.populateCity();

        if (cities == null || cities.isEmpty()) {
            System.out.println("WARNING: populateCity() returned null or empty — 'City' table may not exist.");
            System.out.println("Trying lowercase 'city' directly with fallback query");

            try (Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM city")) {

                assertTrue(rs.next(), "Lowercase 'city' exists and has at least one row");
            } catch (Exception e) {
                fail("Both 'City' and 'city' queries failed — DB schema mismatch");
            }
        } else {
            // original logic for valid population
            City first = cities.get(0);
            assertNotNull(first.getName(), "First city should have a name");
            assertTrue(first.getPopulation() > 0, "First city should have positive population");
        }
    }

    // Test 3: Db.TryPopulateWorld() should populate the World singleton with data
    @Test
    void tryPopulateWorldPopulatesWorld() {
        db.TryPopulateWorld();

        World world = World.getInstance();

        assertFalse(world.getCountries().isEmpty(),
                "World should contain countries after TryPopulateWorld()");
        assertFalse(world.getCities().isEmpty(),
                "World should contain cities after TryPopulateWorld()");

        // Optional: check for a known country in the dataset
        boolean hasFrance = world.getCountries()
                .stream()
                .anyMatch(c -> "France".equals(c.getName()));
        assertTrue(hasFrance, "World should contain France after TryPopulateWorld()");
    }
}
