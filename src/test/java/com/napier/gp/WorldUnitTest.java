package com.napier.gp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.napier.gp.world.World;
import com.napier.gp.world.City;
import com.napier.gp.world.CountryLanguage;
import com.napier.gp.world.Country;


import static org.junit.jupiter.api.Assertions.*;

class WorldUnitTest {

    private World world;

    @BeforeEach
    void setUp() {
        world = World.getInstance();
        // Ensure each test starts with a clean world
        world.getCountries().clear();
        world.getCities().clear();
        world.getLanguages().clear();
    }

    // -------------------------------------------------------
    // Singleton behaviour
    // -------------------------------------------------------

    @Test
    void getInstance_returnsSameSingletonInstance() {
        World first = World.getInstance();
        World second = World.getInstance();

        assertNotNull(first, "First instance should not be null");
        assertSame(first, second, "getInstance() should always return the same World instance");
    }

    @Test
    void initialListsAreEmpty() {
        assertTrue(world.getCountries().isEmpty(), "Countries list should start empty");
        assertTrue(world.getCities().isEmpty(), "Cities list should start empty");
        assertTrue(world.getLanguages().isEmpty(), "Languages list should start empty");
    }

    // -------------------------------------------------------
    // Country operations
    // -------------------------------------------------------

    @Test
    void addAndRemoveCountry_updatesCountriesList() {
        Country country = new Country(
                "GBR",
                "United Kingdom",
                "Europe",
                "British Isles",
                243610.0,
                1707,
                67000000,
                81.2,
                2800000.0,
                2700000.0,
                "United Kingdom",
                "Constitutional Monarchy",
                "King",
                456,
                "GB"
        );

        assertEquals(0, world.getCountries().size(), "Countries list should initially be empty");

        world.addCountry(country);
        assertEquals(1, world.getCountries().size(), "Countries list should contain one country after add");
        assertSame(country, world.getCountries().get(0));

        world.removeCountry(country);
        assertTrue(world.getCountries().isEmpty(), "Countries list should be empty after remove");
    }

    // -------------------------------------------------------
    // City operations
    // -------------------------------------------------------

    @Test
    void addAndRemoveCity_updatesCitiesList() {
        City city = new City(1, "London", "GBR", "England", 9000000);

        assertEquals(0, world.getCities().size(), "Cities list should initially be empty");

        world.addCity(city);
        assertEquals(1, world.getCities().size(), "Cities list should contain one city after add");
        assertSame(city, world.getCities().get(0));

        world.removeCity(city);
        assertTrue(world.getCities().isEmpty(), "Cities list should be empty after remove");
    }

    // -------------------------------------------------------
    // CountryLanguage operations
    // -------------------------------------------------------

    @Test
    void addAndRemoveLanguage_updatesLanguagesList() {
        CountryLanguage language = new CountryLanguage("GBR", "English", "T", 98.5);

        assertEquals(0, world.getLanguages().size(), "Languages list should initially be empty");

        world.addLanguage(language);
        assertEquals(1, world.getLanguages().size(), "Languages list should contain one language after add");
        assertSame(language, world.getLanguages().get(0));

        world.removeLanguage(language);
        assertTrue(world.getLanguages().isEmpty(), "Languages list should be empty after remove");
    }
}
