package com.napier.gp;

import com.napier.gp.world.*;
import com.napier.gp.world.reports.*;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UnitTest {

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

        world.getCountries().addAll(Arrays.asList(gamma, alpha, beta));   // intentionally unsorted

        // ----------------------
        // Cities
        // ----------------------
        City c1 = new City(1, "Alpha City", "AAA", "Alpha District", 2_000_000);
        City c2 = new City(2, "Second Alpha City", "AAA", "Alpha District", 1_000_000);
        City c3 = new City(3, "Beta City", "BBB", "Beta District", 1_500_000);
        City c4 = new City(4, "Gamma City", "CCC", "Gamma District", 3_000_000);

        world.getCities().addAll(Arrays.asList(c2, c4, c1, c3)); // intentionally unsorted

        alpha.getCities().addAll(Arrays.asList(c1, c2));
        beta.getCities().add(c3);
        gamma.getCities().add(c4);

        // ----------------------
        // Languages
        // ----------------------
        alpha.getLanguages().add(new CountryLanguage("AAA", "English", "T", 60.0));
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
    // U1PopulationDataReports tests (UNHIGHLIGHTED methods)
    // ----------------------------------------------------------------------

    @Test
    void testGetCountryPopulationLargestToSmallestInWorld() {
        List<Country> result =
                U1PopulationDataReports.getCountryPopulationLargestToSmallestInWorld();

        assertEquals("Gamma", result.get(0).getName());
        assertEquals("Alpha", result.get(1).getName());
        assertEquals("Beta", result.get(2).getName());
    }

    @Test
    void testGetCitiesPopulationLargestToSmallestInWorld() {
        List<City> result =
                U1PopulationDataReports.getCitiesPopulationLargestToSmallestInWorld();

        assertEquals(4, result.size());
        assertEquals("Gamma City", result.get(0).getName());
        assertEquals("Alpha City", result.get(1).getName());
    }

    @Test
    void testGetCountryPopulationLargestToSmallestInContinent() {
        Map<String, List<Country>> map =
                U1PopulationDataReports.getCountryPopulationLargestToSmallestInContinent();

        assertEquals("Alpha", map.get("Europe").get(0).getName());
        assertEquals("Beta", map.get("Europe").get(1).getName());
    }

    @Test
    void testGetCountryPopulationLargestToSmallestInRegion() {
        Map<String, List<Country>> map =
                U1PopulationDataReports.getCountryPopulationLargestToSmallestInRegion();

        assertEquals("Alpha", map.get("Region1").get(0).getName());
        assertEquals("Beta", map.get("Region1").get(1).getName());
        assertEquals("Gamma", map.get("Region2").get(0).getName());
    }

    @Test
    void testGetCapitalCitiesLargestToSmallestInWorld() {
        List<City> caps =
                U1PopulationDataReports.getCapitalCitiesLargestToSmallestInWorld();

        assertEquals("Gamma City", caps.get(0).getName());
        assertEquals("Alpha City", caps.get(1).getName());
        assertEquals("Beta City", caps.get(2).getName());
    }

    @Test
    void testGetCityPopulationLargestToSmallestInDistrict() {
        Map<String, List<City>> map =
                U1PopulationDataReports.getCityPopulationLargestToSmallestInDistrict();

        List<City> alphaDistrict = map.get("Alpha District");

        assertEquals(2, alphaDistrict.size());
        assertEquals("Alpha City", alphaDistrict.get(0).getName());
        assertEquals("Second Alpha City", alphaDistrict.get(1).getName());
    }

    // ----------------------------------------------------------------------
    // U1PopulationDataReports print() methods
    // ----------------------------------------------------------------------

    @Test
    void testPrintCountryPopulationLargestToSmallestInWorld_OutputContainsCountriesInOrder() {
        U1PopulationDataReports.printCountryPopulationLargestToSmallestInWorld();
        String output = out.toString();

        assertTrue(output.contains("Gamma 7000000"));
        assertTrue(output.contains("Alpha 5000000"));
        assertTrue(output.contains("Beta 3000000"));

        int idxGamma = output.indexOf("Gamma 7000000");
        int idxAlpha = output.indexOf("Alpha 5000000");
        int idxBeta  = output.indexOf("Beta 3000000");

        assertTrue(idxGamma != -1 && idxAlpha != -1 && idxBeta != -1);
        assertTrue(idxGamma < idxAlpha);
        assertTrue(idxAlpha < idxBeta);
    }

    @Test
    void testPrintCountryPopulationLargestToSmallestInContinent_OutputContainsContinentsAndCountries() {
        U1PopulationDataReports.printCountryPopulationLargestToSmallestInContinent();
        String output = out.toString();

        // Should have sections for Europe and Asia
        assertTrue(output.contains("Europe:"));
        assertTrue(output.contains("Asia:"));

        // Europe section should mention Alpha and Beta
        assertTrue(output.contains("Alpha 5000000"));
        assertTrue(output.contains("Beta 3000000"));

        // Asia section should mention Gamma
        assertTrue(output.contains("Gamma 7000000"));
    }

    @Test
    void testPrintCountryPopulationLargestToSmallestInRegion_OutputContainsRegionsAndCountries() {
        U1PopulationDataReports.printCountryPopulationLargestToSmallestInRegion();
        String output = out.toString();

        assertTrue(output.contains("Region1:"));
        assertTrue(output.contains("Region2:"));
        assertTrue(output.contains("Alpha 5000000"));
        assertTrue(output.contains("Beta 3000000"));
        assertTrue(output.contains("Gamma 7000000"));
    }

    @Test
    void testPrintCityPopulationLargestToSmallestInWorld_OutputContainsCitiesInOrder() {
        U1PopulationDataReports.printCityPopulationLargestToSmallestInWorld();
        String output = out.toString();

        assertTrue(output.contains("Population of all cities in world"));
        assertTrue(output.contains("Gamma City 3000000"));
        assertTrue(output.contains("Alpha City 2000000"));
        assertTrue(output.contains("Beta City 1500000"));
        assertTrue(output.contains("Second Alpha City 1000000"));

        int idxGamma = output.indexOf("Gamma City 3000000");
        int idxAlpha = output.indexOf("Alpha City 2000000");
        int idxBeta  = output.indexOf("Beta City 1500000");
        int idxSecond = output.indexOf("Second Alpha City 1000000");

        assertTrue(idxGamma != -1 && idxAlpha != -1 && idxBeta != -1 && idxSecond != -1);
        assertTrue(idxGamma < idxAlpha);
        assertTrue(idxAlpha < idxBeta);
        assertTrue(idxBeta < idxSecond);
    }

    @Test
    void testPrintCityPopulationLargestToSmallestInContinent_OutputContainsHeaderAndCities() {
        U1PopulationDataReports.printCityPopulationLargestToSmallestInContinent();
        String output = out.toString();

        assertTrue(output.contains("Populated cities largest to smallest for the continent of: Europe"));
        assertTrue(output.contains("Populated cities largest to smallest for the continent of: Asia"));

        // Europe cities
        assertTrue(output.contains("Alpha City 2000000"));
        assertTrue(output.contains("Second Alpha City 1000000"));
        assertTrue(output.contains("Beta City 1500000"));

        // Asia cities
        assertTrue(output.contains("Gamma City 3000000"));
    }

    @Test
    void testPrintCityPopulationLargestToSmallestInRegion_OutputContainsRegionsAndCities() {
        U1PopulationDataReports.printCityPopulationLargestToSmallestInRegion();
        String output = out.toString();

        assertTrue(output.contains("Region1:"));
        assertTrue(output.contains("Region2:"));

        assertTrue(output.contains("Alpha City 2000000"));
        assertTrue(output.contains("Second Alpha City 1000000"));
        assertTrue(output.contains("Beta City 1500000"));
        assertTrue(output.contains("Gamma City 3000000"));
    }

    @Test
    void testPrintCapitalCitiesLargestToSmallestInWorld_OutputContainsCapitalsInOrder() {
        U1PopulationDataReports.printCapitalCitiesLargestToSmallestInWorld();
        String output = out.toString();

        assertTrue(output.contains("The most populated cities in the world (L - S):"));
        assertTrue(output.contains("City name: Gamma City Population: 3000000"));
        assertTrue(output.contains("City name: Alpha City Population: 2000000"));
        assertTrue(output.contains("City name: Beta City Population: 1500000"));

        int idxGamma = output.indexOf("City name: Gamma City Population: 3000000");
        int idxAlpha = output.indexOf("City name: Alpha City Population: 2000000");
        int idxBeta  = output.indexOf("City name: Beta City Population: 1500000");

        assertTrue(idxGamma != -1 && idxAlpha != -1 && idxBeta != -1);
        assertTrue(idxGamma < idxAlpha);
        assertTrue(idxAlpha < idxBeta);
    }

    @Test
    void testPrintCapitalCitiesLargestToSmallestInContinent_OutputContainsContinentsAndCapitals() {
        U1PopulationDataReports.printCapitalCitiesLargestToSmallestInContinent();
        String output = out.toString();

        assertTrue(output.contains("The most populated capital cities for the continent of: Europe"));
        assertTrue(output.contains("The most populated capital cities for the continent of: Asia"));

        // Europe capitals
        assertTrue(output.contains("City Name: Alpha City Population: 2000000"));
        assertTrue(output.contains("City Name: Beta City Population: 1500000"));

        // Asia capitals
        assertTrue(output.contains("City Name: Gamma City Population: 3000000"));
    }

    @Test
    void testPrintCapitalCitiesLargestToSmallestInRegion_OutputContainsRegionsAndCapitals() {
        U1PopulationDataReports.printCapitalCitiesLargestToSmallestInRegion();
        String output = out.toString();

        assertTrue(output.contains("The most populated capital cities for the region of: Region1"));
        assertTrue(output.contains("The most populated capital cities for the region of: Region2"));

        assertTrue(output.contains("City Name: Alpha City Population: 2000000"));
        assertTrue(output.contains("City Name: Beta City Population: 1500000"));
        assertTrue(output.contains("City Name: Gamma City Population: 3000000"));
    }

    // ----------------------------------------------------------------------
    // U2PopulationReports tests (UNHIGHLIGHTED methods)
    // ----------------------------------------------------------------------

    @Test
    void testPrintWorldPopulation_NoError() {
        assertDoesNotThrow(() -> U2PopulationReports.printWorldPopulation());
    }

    @Test
    void testPrintContinentPopulations_NoError() {
        assertDoesNotThrow(() -> U2PopulationReports.printContinentPopulations());
    }

    @Test
    void testPrintRegionPopulations_NoError() {
        assertDoesNotThrow(() -> U2PopulationReports.printRegionPopulations());
    }

    @Test
    void testPrintCountryPopulations_NoError() {
        assertDoesNotThrow(() -> U2PopulationReports.printCountryPopulations());
    }

    @Test
    void testPrintDistrictPopulations_NoError() {
        assertDoesNotThrow(() -> U2PopulationReports.printDistrictPopulations());
    }

    @Test
    void testPrintCityPopulations_NoError() {
        assertDoesNotThrow(() -> U2PopulationReports.printCityPopulations());
    }

    // ----------------------------------------------------------------------
    // U3LanguagesReport tests
    // ----------------------------------------------------------------------

    @Test
    void testGetWorldwideLanguageSpeakers() {
        Map<String, Long> map =
                U3LanguagesReport.getWorldwideLanguageSpeakers();

        assertNotNull(map);

        // English = Alpha 60% of 5M = 3M, + Beta 30% of 3M = 0.9M => 3.9M
        assertEquals(3_900_000L, map.get("English"));

        assertTrue(map.containsKey("Chinese"));
        assertTrue(map.containsKey("English"));
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
    void testU3LanguagesReportPrint_OutputContainsAllRequiredLanguages() {
        U3LanguagesReport.print();
        String output = out.toString();

        assertTrue(output.contains("Chinese"));
        assertTrue(output.contains("English"));
        assertTrue(output.contains("Hindi"));
        assertTrue(output.contains("Spanish"));
        assertTrue(output.contains("Arabic"));

        // Should also contain a percent sign for the percentages
        assertTrue(output.contains("%"));
    }
}
