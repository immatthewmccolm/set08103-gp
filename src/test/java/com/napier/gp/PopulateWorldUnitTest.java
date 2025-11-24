package com.napier.gp;

import com.napier.gp.world.City;
import com.napier.gp.world.Country;
import com.napier.gp.world.CountryLanguage;
import com.napier.gp.world.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PopulateWorldUnitTest {

    private World world;

    @BeforeEach
    void setUp() {
        world = World.getInstance();

        // Clear existing data so each test starts from a known state
        world.getCountries().clear();
        world.getCities().clear();
        world.getLanguages().clear();

        // --------------------------------------------------------------
        // Countries (same style as your existing UnitTest)
        // --------------------------------------------------------------
        Country alpha = new Country(
                "AAA", "Alpha", "Europe", "Region1",
                0.0, 0, 0,
                0.0, 0.0, 0.0,
                "", "", "", 1, "AA"
        );
        alpha.setPopulation(5_000_000);

        Country beta = new Country(
                "BBB", "Beta", "Europe", "Region1",
                0.0, 0, 0,
                0.0, 0.0, 0.0,
                "", "", "", 3, "BB"
        );
        beta.setPopulation(3_000_000);

        world.getCountries().add(alpha);
        world.getCountries().add(beta);

        // --------------------------------------------------------------
        // Cities
        // --------------------------------------------------------------
        City alphaCity = new City(1, "Alpha City", "AAA", "Alpha District", 2_000_000);
        City secondAlphaCity = new City(2, "Second Alpha City", "AAA", "Alpha District", 1_000_000);
        City betaCity = new City(3, "Beta City", "BBB", "Beta District", 1_500_000);

        world.getCities().add(alphaCity);
        world.getCities().add(secondAlphaCity);
        world.getCities().add(betaCity);

        // --------------------------------------------------------------
        // Languages
        // --------------------------------------------------------------
        CountryLanguage alphaEnglish =
                new CountryLanguage("AAA", "English", "T", 60.0);
        CountryLanguage alphaSpanish =
                new CountryLanguage("AAA", "Spanish", "T", 40.0);

        CountryLanguage betaEnglish =
                new CountryLanguage("BBB", "English", "T", 30.0);
        CountryLanguage betaArabic =
                new CountryLanguage("BBB", "Arabic", "T", 70.0);

        world.getLanguages().add(alphaEnglish);
        world.getLanguages().add(alphaSpanish);
        world.getLanguages().add(betaEnglish);
        world.getLanguages().add(betaArabic);
    }

    // -----------------------------------------------------------------
    // Tests for PopulateWorld.PopulateCitiesAndLanguages()
    // -----------------------------------------------------------------

    @Test
    void populateCitiesAndLanguages_assignsCitiesToCorrectCountries() {
        // Act
        PopulateWorld.PopulateCitiesAndLanguages();

        // Find countries back from the World singleton
        Country alpha = world.getCountries().stream()
                .filter(c -> c.getCode().equals("AAA"))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Alpha country not found"));

        Country beta = world.getCountries().stream()
                .filter(c -> c.getCode().equals("BBB"))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Beta country not found"));

        // Assert Alpha's cities
        List<City> alphaCities = alpha.getCities();
        assertEquals(2, alphaCities.size(), "Alpha should have 2 cities");
        assertTrue(alphaCities.stream().anyMatch(c -> c.getName().equals("Alpha City")),
                "Alpha should contain 'Alpha City'");
        assertTrue(alphaCities.stream().anyMatch(c -> c.getName().equals("Second Alpha City")),
                "Alpha should contain 'Second Alpha City'");

        // Assert Beta's cities
        List<City> betaCities = beta.getCities();
        assertEquals(1, betaCities.size(), "Beta should have 1 city");
        assertEquals("Beta City", betaCities.get(0).getName(),
                "Beta's city should be 'Beta City'");
    }

    @Test
    void populateCitiesAndLanguages_assignsLanguagesToCorrectCountries() {
        // Act
        PopulateWorld.PopulateCitiesAndLanguages();

        Country alpha = world.getCountries().stream()
                .filter(c -> c.getCode().equals("AAA"))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Alpha country not found"));

        Country beta = world.getCountries().stream()
                .filter(c -> c.getCode().equals("BBB"))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Beta country not found"));

        // Assert Alpha's languages
        List<CountryLanguage> alphaLangs = alpha.getLanguages();
        assertEquals(2, alphaLangs.size(), "Alpha should have 2 languages");
        assertTrue(alphaLangs.stream().anyMatch(l -> l.getLanguage().equals("English")),
                "Alpha should have English");
        assertTrue(alphaLangs.stream().anyMatch(l -> l.getLanguage().equals("Spanish")),
                "Alpha should have Spanish");

        // Assert Beta's languages
        List<CountryLanguage> betaLangs = beta.getLanguages();
        assertEquals(2, betaLangs.size(), "Beta should have 2 languages");
        assertTrue(betaLangs.stream().anyMatch(l -> l.getLanguage().equals("English")),
                "Beta should have English");
        assertTrue(betaLangs.stream().anyMatch(l -> l.getLanguage().equals("Arabic")),
                "Beta should have Arabic");
    }

    @Test
    void populateCitiesAndLanguages_doesNothingWhenWorldIsEmpty() {
        // Arrange: clear everything
        world.getCountries().clear();
        world.getCities().clear();
        world.getLanguages().clear();

        // Act – should not throw even with no data
        assertDoesNotThrow(PopulateWorld::PopulateCitiesAndLanguages,
                "PopulateCitiesAndLanguages should not throw when world is empty");

        // Assert
        assertTrue(world.getCountries().isEmpty(), "Countries should still be empty");
        assertTrue(world.getCities().isEmpty(), "Cities should still be empty");
        assertTrue(world.getLanguages().isEmpty(), "Languages should still be empty");
    }

    // -----------------------------------------------------------------
    // Tests for PopulateWorld.TryPopulateWorld(Connection)
    // -----------------------------------------------------------------

    @Test
    void tryPopulateWorld_withNullConnection_doesNotThrow() {
        // The method wraps its body in a try/catch, so passing null should not
        // cause an exception to escape (NullPointerException should be caught).
        assertDoesNotThrow(() -> PopulateWorld.TryPopulateWorld(null),
                "TryPopulateWorld should not throw even if connection is null");
    }
}
