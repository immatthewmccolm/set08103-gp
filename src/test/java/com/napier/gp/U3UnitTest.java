package com.napier.gp;

import com.napier.gp.world.*;
import com.napier.gp.world.reports.*;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class U3UnitTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream out;
    private World world;

    // ----------------------------------------------------------------------
    //  Initialise REAL test data (NO Mockito)
    // ----------------------------------------------------------------------
    @BeforeAll
    void loadWorldSampleData() {
        world = World.getInstance();

        world.getCountries().clear();
        world.getCities().clear();

        // ----------------------
        // Countries
        // ----------------------
        Country alpha = new Country("AAA", "Alpha", "Europe", "Region1",
                0.0, 0, 0, 0.0, 0.0, 0.0,
                "", "", "", 1, "");

        alpha.setPopulation(5_000_000);

        Country beta = new Country("BBB", "Beta", "Europe", "Region1",
                0.0, 0, 0, 0.0, 0.0, 0.0,
                "", "", "", 3, "");

        beta.setPopulation(3_000_000);

        Country gamma = new Country("CCC", "Gamma", "Asia", "Region2",
                0.0, 0, 0, 0.0, 0.0, 0.0,
                "", "", "", 4, "");

        gamma.setPopulation(7_000_000);

        // Intentionally unsorted
        world.getCountries().addAll(Arrays.asList(gamma, alpha, beta));

        // ----------------------
        // Cities
        // ----------------------
        City c1 = new City(1, "Alpha City", "AAA", "Alpha District", 2_000_000);
        City c2 = new City(2, "Second Alpha City", "AAA", "Alpha District", 1_000_000);
        City c3 = new City(3, "Beta City", "BBB", "Beta District", 1_500_000);
        City c4 = new City(4, "Gamma City", "CCC", "Gamma District", 3_000_000);

        // Intentionally unsorted
        world.getCities().addAll(Arrays.asList(c2, c4, c1, c3));

        alpha.getCities().addAll(Arrays.asList(c1, c2));
        beta.getCities().add(c3);
        gamma.getCities().add(c4);

        // ----------------------
        // Languages
        // ----------------------
        alpha.getLanguages().add(new CountryLanguage("AAA", "English", "T", 40.0));
        alpha.getLanguages().add(new CountryLanguage("AAA", "Spanish", "T", 40.0));

        beta.getLanguages().add(new CountryLanguage("BBB", "English", "T", 30.0));
        beta.getLanguages().add(new CountryLanguage("BBB", "Arabic", "T", 70.0));

        gamma.getLanguages().add(new CountryLanguage("CCC", "Chinese", "T", 50.0));
        gamma.getLanguages().add(new CountryLanguage("CCC", "Hindi", "T", 50.0));
    }

    // Capture print output
    @BeforeEach
    void startCapture() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void stopCapture() {
        System.setOut(originalOut);
    }

    // ----------------------------------------------------------------------
    // U3LanguagesReport tests
    // ----------------------------------------------------------------------

    @Test
    void testGetWorldwideLanguageSpeakers() {
        Map<String, Long> map =
                U3LanguagesReport.getWorldwideLanguageSpeakers();

        // English: 40% of 5M (Alpha) + 30% of 3M (Beta) = 2.9M
        assertEquals(2_900_000L, map.get("English"));

        assertTrue(map.containsKey("Chinese"));
        assertTrue(map.containsKey("Hindi"));
        assertTrue(map.containsKey("Spanish"));
        assertTrue(map.containsKey("Arabic"));
    }

    @Test
    void testGetWorldwideLanguageSpeakersPercentages_Sorted() {
        List<Map.Entry<String, Double>> list =
                U3LanguagesReport.getWorldwideLanguageSpeakersPercentages();

        for (int i = 0; i < list.size() - 1; i++) {
            assertTrue(list.get(i).getValue() >= list.get(i + 1).getValue());
        }
    }

    // ----------------------------------------------------------------------
    // U3LanguagesReport print() method
    // ----------------------------------------------------------------------

    @Test
    void testLanguagesReportPrint() {
        U3LanguagesReport.print();
        String output = out.toString();

        assertTrue(output.contains("Chinese"));
        assertTrue(output.contains("English"));
        assertTrue(output.contains("Hindi"));
        assertTrue(output.contains("Spanish"));
        assertTrue(output.contains("Arabic"));
        assertTrue(output.contains("%"));
    }
}
