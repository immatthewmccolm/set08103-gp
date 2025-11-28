package com.napier.gp;

import com.napier.gp.world.City;
import com.napier.gp.world.Country;
import com.napier.gp.world.CountryLanguage;
import com.napier.gp.world.World;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PopWorldUnitTest {

    private World world;

    @BeforeEach
    void setUp() {
        world = World.getInstance();
        world.getCountries().clear();
        world.getCities().clear();
        world.getLanguages().clear();
    }

    // -------------------------------------------------------
    // Unit test for PopulateCitiesAndLanguages()
    // -------------------------------------------------------
    @Test
    void populateCitiesAndLanguages_linksCitiesAndLanguagesToMatchingCountry() {
        // Arrange: create one country and a mix of matching / non-matching cities and languages
        Country countryAAA = new Country(
                "AAA", "Alpha", "Europe", "Region1",
                0.0, 0, 0,
                0.0, 0.0, 0.0,
                "", "", "", 1, "AA"
        );

        Country countryBBB = new Country(
                "BBB", "Beta", "Europe", "Region1",
                0.0, 0, 0,
                0.0, 0.0, 0.0,
                "", "", "", 2, "BB"
        );

        world.addCountry(countryAAA);
        world.addCountry(countryBBB);

        // Cities: two for AAA, one for BBB
        City city1 = new City(1, "Alpha City", "AAA", "Alpha District", 2000000);
        City city2 = new City(2, "Second Alpha City", "AAA", "Alpha District", 1000000);
        City city3 = new City(3, "Beta City", "BBB", "Beta District", 1500000);

        world.addCity(city1);
        world.addCity(city2);
        world.addCity(city3);

        // Languages: two for AAA, one for BBB
        CountryLanguage lang1 = new CountryLanguage("AAA", "English", "T", 60.0);
        CountryLanguage lang2 = new CountryLanguage("AAA", "Spanish", "T", 40.0);
        CountryLanguage lang3 = new CountryLanguage("BBB", "Arabic", "T", 70.0);

        world.addLanguage(lang1);
        world.addLanguage(lang2);
        world.addLanguage(lang3);

        // Act
        PopulateWorld.PopulateCitiesAndLanguages();

        // Assert: AAA should have 2 cities, 2 languages
        List<City> aaaCities = countryAAA.getCities();
        List<CountryLanguage> aaaLangs = countryAAA.getLanguages();

        assertEquals(2, aaaCities.size(), "AAA should have 2 cities attached");
        assertTrue(aaaCities.stream().anyMatch(c -> c.getName().equals("Alpha City")));
        assertTrue(aaaCities.stream().anyMatch(c -> c.getName().equals("Second Alpha City")));

        assertEquals(2, aaaLangs.size(), "AAA should have 2 languages attached");
        assertTrue(aaaLangs.stream().anyMatch(l -> l.getLanguage().equals("English")));
        assertTrue(aaaLangs.stream().anyMatch(l -> l.getLanguage().equals("Spanish")));

        // BBB should have 1 city, 1 language
        List<City> bbbCities = countryBBB.getCities();
        List<CountryLanguage> bbbLangs = countryBBB.getLanguages();

        assertEquals(1, bbbCities.size(), "BBB should have 1 city attached");
        assertEquals("Beta City", bbbCities.get(0).getName());

        assertEquals(1, bbbLangs.size(), "BBB should have 1 language attached");
        assertEquals("Arabic", bbbLangs.get(0).getLanguage());
    }

    // -------------------------------------------------------
    // Unit test for TryPopulateWorld(Connection)
    // -------------------------------------------------------
    @Test
    void tryPopulateWorld_withNullConnection_isHandledAndDoesNotThrow() {
        // Arrange: make sure world starts empty
        assertTrue(world.getCountries().isEmpty());
        assertTrue(world.getCities().isEmpty());
        assertTrue(world.getLanguages().isEmpty());

        // Act & Assert:
        // Passing null will cause a NullPointerException at connection.createStatement(),
        // but the method catches all Exceptions, so nothing should escape here.
        assertDoesNotThrow(() -> PopulateWorld.TryPopulateWorld((Connection) null),
                "TryPopulateWorld(null) should catch its own exceptions and not throw");

        // World should still be empty because we couldn't talk to a DB
        assertTrue(world.getCountries().isEmpty(), "Countries should still be empty after TryPopulateWorld(null)");
        assertTrue(world.getCities().isEmpty(), "Cities should still be empty after TryPopulateWorld(null)");
        assertTrue(world.getLanguages().isEmpty(), "Languages should still be empty after TryPopulateWorld(null)");
    }
}
