package com.napier.gp;

import com.napier.gp.world.City;
import org.junit.jupiter.api.*;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * INTEGRATION TESTS for Db.java
 *
 * These tests require a REAL running MySQL database.
 * This is NOT a unit test by design.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DBIntegrationTest {

    private Db db;

    @BeforeEach
    void setUp() {
        db = new Db();
    }

    // -------------------------------------------------------
    // connect()
    // -------------------------------------------------------

    @Test
    void testConnect_opensDatabaseConnection() throws Exception {
        db.connect();

        // Access private "con" field via reflection to verify it is not null
        Field field = Db.class.getDeclaredField("con");
        field.setAccessible(true);
        Connection connection = (Connection) field.get(db);

        assertNotNull(connection, "Database connection should not be null after connect()");
        assertFalse(connection.isClosed(), "Database connection should be open after connect()");
    }

    // -------------------------------------------------------
    // populateCity()
    // -------------------------------------------------------

    @Test
    void testPopulateCity_returnsAtLeastOneCityFromDatabase() {
        db.connect();

        List<City> cities = db.populateCity();

        assertNotNull(cities, "populateCity() should not return null");
        assertFalse(cities.isEmpty(), "populateCity() should return at least one city");

        City firstCity = cities.get(0);
        assertTrue(firstCity.getID() > 0);
        assertNotNull(firstCity.getName());
        assertNotNull(firstCity.getCountryCode());
    }

    // -------------------------------------------------------
    // TryPopulateWorld()
    // -------------------------------------------------------

    @Test
    void testTryPopulateWorld_doesNotThrowWithValidConnection() {
        db.connect();

        assertDoesNotThrow(() -> db.TryPopulateWorld(),
                "TryPopulateWorld() should not throw with a valid connection");
    }

    // -------------------------------------------------------
    // disconnect()
    // -------------------------------------------------------

    @Test
    void testDisconnect_closesDatabaseConnection() throws Exception {
        db.connect();
        db.disconnect();

        Field field = Db.class.getDeclaredField("con");
        field.setAccessible(true);
        Connection connection = (Connection) field.get(db);

        assertTrue(connection == null || connection.isClosed(),
                "Database connection should be closed after disconnect()");
    }
}
