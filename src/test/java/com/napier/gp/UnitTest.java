package com.napier.gp;

import com.napier.gp.world.Country;
import com.napier.gp.world.City;
import com.napier.gp.world.World;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UnitTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream out;

    @BeforeAll
    void loadWorldSampleData() {
        World world = World.getInstance();

        // Clear existing data (if any)
        List<Country> countries = world.getCountries();
        countries.clear();

        // Clear existing data (if any)
        List<City> cities = world.getCities();
        cities.clear();

        // Add sample countries – dummy values for fields we don't care about
        countries.add(new Country(
                "CHN", "China", "Asia", "Eastern Asia",
                9596961.0, 1949, 14,
                76.9, 14342903.0, 0.0,
                "Zhongguo", "Republic", "Head", 1, "CN"
        ));

        countries.add(new Country(
                "IND", "India", "Asia", "Southern Asia",
                3287263.0, 1947, 13,
                69.7, 2875142.0, 0.0,
                "Bharat", "Republic", "Head", 2, "IN"
        ));

        countries.add(new Country(
                "USA", "United States", "North America", "North America",
                9833517.0, 1776, 33,
                78.9, 21433226.0, 0.0,
                "United States", "Federal Republic", "Head", 3, "US"
        ));

        countries.add(new Country(
                "GBR", "United Kingdom", "Europe", "British Islands",
                243610.0, 1707, 67,
                81.2, 2827113.0, 0.0,
                "United Kingdom", "Constitutional Monarchy", "Head", 4, "GB"
        ));

        countries.add(new Country(
                "FRA", "France", "Europe", "Western Europe",
                551695.0, 843, 65,
                82.5, 2715518.0, 0.0,
                "France", "Republic", "Head", 5, "FR"
        ));

        cities.add(new City(
                2974, "Paris", "FRA",
                "Île-de-France", 2125246
        ));

        cities.add(new City(
                2975, "Marseille", "FRA",
                "Provence-Alpes-Côte", 798430
        ));

        cities.add(new City(
                2976, "Lyon", "FRA",
                "Rhône-Alpes", 445452
        ));

        cities.add(new City(
                2977, "Toulouse", "FRA",
                "Midi-Pyrénées", 390350
        ));
    }
    // Runs before every test: redirect System.out so we can capture printed output
    @BeforeEach
    void startCapture() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }
    // Runs after every test: restore the original System.out
    @AfterEach
    void stopCapture() {
        System.setOut(originalOut);
    }
    // Test 1: Check the world population report returns the expected value
    @Test
    void checkWorldPopulation() {
        assertEquals(192, U2PopulationReports.returnWorldPopulation());
    }
    // Test 2: Check that city population lookup works correctly
    @Test
    void checkCityPopulation() {
        assertEquals(2125246, U2PopulationReports.printCityPopulationByKey("Paris"));
    }
    // Test 3: Check that district population lookup works correctly
    @Test
    void checkDistrictPopulation() {
        assertEquals(2125246, U2PopulationReports.printDistrictPopulationByKey("Île-de-France"));
    }
    // Test 4: Check that country population lookup works correctly
    @Test
    void checkCountryPopulation() {
        assertEquals(65, U2PopulationReports.printCountryPopulationByKey("France"));
    }
    // Test 5: Check that region population lookup works correctly
    @Test
    void checkRegionPopulation() {
        assertEquals(65, U2PopulationReports.printRegionPopulationByKey("Western Europe"));
    }
    // Test 6: Check that continent population lookup works correctly
    @Test
    void checkContinentPopulation() {
        assertEquals(132, U2PopulationReports.printContinentPopulationByKey("Europe"));
    }
}
