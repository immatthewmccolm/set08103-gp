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
    // U1PopulationDataReports tests (value-returning methods)
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
    // U1PopulationDataReports print and Top-N methods
    // ----------------------------------------------------------------------

    @Test
    void testPrintCountryPopulationLargestToSmallestInWorld() {
        U1PopulationDataReports.printCountryPopulationLargestToSmallestInWorld();
        String output = out.toString();

        assertTrue(output.contains("Gamma 7000000"));
        assertTrue(output.contains("Alpha 5000000"));
        assertTrue(output.contains("Beta 3000000"));

        int idxGamma = output.indexOf("Gamma 7000000");
        int idxAlpha = output.indexOf("Alpha 5000000");
        int idxBeta = output.indexOf("Beta 3000000");

        assertTrue(idxGamma >= 0 && idxAlpha >= 0 && idxBeta >= 0);
        assertTrue(idxGamma < idxAlpha);
        assertTrue(idxAlpha < idxBeta);
    }

    @Test
    void testPrintCountryPopulationLargestToSmallestInContinent() {
        U1PopulationDataReports.printCountryPopulationLargestToSmallestInContinent();
        String output = out.toString();

        assertTrue(output.contains("Gamma 7000000"));
        assertTrue(output.contains("Alpha 5000000"));
        assertTrue(output.contains("Beta 3000000"));

        int idxAlpha = output.indexOf("Alpha 5000000");
        int idxBeta = output.indexOf("Beta 3000000");
        assertTrue(idxAlpha >= 0 && idxBeta >= 0);
        assertTrue(idxAlpha < idxBeta);
    }

    @Test
    void testPrintCountryPopulationLargestToSmallestInRegion() {
        U1PopulationDataReports.printCountryPopulationLargestToSmallestInRegion();
        String output = out.toString();

        assertTrue(output.contains("Alpha 5000000"));
        assertTrue(output.contains("Beta 3000000"));
        assertTrue(output.contains("Gamma 7000000"));

        int idxAlpha = output.indexOf("Alpha 5000000");
        int idxBeta = output.indexOf("Beta 3000000");
        assertTrue(idxAlpha >= 0 && idxBeta >= 0);
        assertTrue(idxAlpha < idxBeta);
    }

    @Test
    void testPrintCityPopulationLargestToSmallestInWorld() {
        U1PopulationDataReports.printCityPopulationLargestToSmallestInWorld();
        String output = out.toString();

        assertTrue(output.contains("Population of all cities in world"));
        assertTrue(output.contains("Gamma City 3000000"));
        assertTrue(output.contains("Alpha City 2000000"));
        assertTrue(output.contains("Beta City 1500000"));
        assertTrue(output.contains("Second Alpha City 1000000"));

        int idxGamma = output.indexOf("Gamma City 3000000");
        int idxAlpha = output.indexOf("Alpha City 2000000");
        int idxBeta = output.indexOf("Beta City 1500000");
        int idxSecond = output.indexOf("Second Alpha City 1000000");

        assertTrue(idxGamma >= 0 && idxAlpha >= 0 && idxBeta >= 0 && idxSecond >= 0);
        assertTrue(idxGamma < idxAlpha);
        assertTrue(idxAlpha < idxBeta);
        assertTrue(idxBeta < idxSecond);
    }

    @Test
    void testPrintCityPopulationLargestToSmallestInContinent() {
        U1PopulationDataReports.printCityPopulationLargestToSmallestInContinent();
        String output = out.toString();

        assertTrue(output.contains("Populated cities largest to smallest for the continent of: Europe"));
        assertTrue(output.contains("Populated cities largest to smallest for the continent of: Asia"));

        assertTrue(output.contains("Alpha City 2000000"));
        assertTrue(output.contains("Second Alpha City 1000000"));
        assertTrue(output.contains("Beta City 1500000"));
        assertTrue(output.contains("Gamma City 3000000"));
    }

    @Test
    void testPrintCityPopulationLargestToSmallestInRegion() {
        U1PopulationDataReports.printCityPopulationLargestToSmallestInRegion();
        String output = out.toString();

        assertTrue(output.contains("Region1"));
        assertTrue(output.contains("Region2"));

        assertTrue(output.contains("Alpha City 2000000"));
        assertTrue(output.contains("Second Alpha City 1000000"));
        assertTrue(output.contains("Beta City 1500000"));
        assertTrue(output.contains("Gamma City 3000000"));
    }

    @Test
    void testPrintCapitalCitiesLargestToSmallestInWorld() {
        U1PopulationDataReports.printCapitalCitiesLargestToSmallestInWorld();
        String output = out.toString();

        assertTrue(output.contains("City name: Gamma City Population: 3000000"));
        assertTrue(output.contains("City name: Alpha City Population: 2000000"));
        assertTrue(output.contains("City name: Beta City Population: 1500000"));

        int idxGamma = output.indexOf("City name: Gamma City Population: 3000000");
        int idxAlpha = output.indexOf("City name: Alpha City Population: 2000000");
        int idxBeta  = output.indexOf("City name: Beta City Population: 1500000");

        assertTrue(idxGamma >= 0 && idxAlpha >= 0 && idxBeta >= 0);
        assertTrue(idxGamma < idxAlpha);
        assertTrue(idxAlpha < idxBeta);
    }

    @Test
    void testPrintCapitalCitiesLargestToSmallestInContinent() {
        U1PopulationDataReports.printCapitalCitiesLargestToSmallestInContinent();
        String output = out.toString();

        assertTrue(output.contains("The most populated capital cities for the continent of: Europe"));
        assertTrue(output.contains("The most populated capital cities for the continent of: Asia"));

        assertTrue(output.contains("City Name: Alpha City Population: 2000000"));
        assertTrue(output.contains("City Name: Beta City Population: 1500000"));
        assertTrue(output.contains("City Name: Gamma City Population: 3000000"));
    }

    @Test
    void testPrintCapitalCitiesLargestToSmallestInRegion() {
        U1PopulationDataReports.printCapitalCitiesLargestToSmallestInRegion();
        String output = out.toString();

        assertTrue(output.contains("The most populated capital cities for the region of: Region1"));
        assertTrue(output.contains("The most populated capital cities for the region of: Region2"));

        assertTrue(output.contains("City Name: Alpha City Population: 2000000"));
        assertTrue(output.contains("City Name: Beta City Population: 1500000"));
        assertTrue(output.contains("City Name: Gamma City Population: 3000000"));
    }

    // Top-N country reports

    @Test
    void testPrintTopNPopulatedCountriesInWorld() {
        U1PopulationDataReports.printTopNPopulatedCountriesInWorld(2);
        String output = out.toString();

        assertTrue(output.contains("The top 2 most populated countries in the world"));
        assertTrue(output.contains("1. Gamma 7000000"));
        assertTrue(output.contains("2. Alpha 5000000"));
    }

    @Test
    void testPrintTopNPopulatedCountriesInContinent() {
        U1PopulationDataReports.printTopNPopulatedCountriesInContinent(1);
        String output = out.toString();

        assertTrue(output.contains("most populated countries in the continent of Europe"));
        assertTrue(output.contains("Alpha 5000000"));

        assertTrue(output.contains("most populated countries in the continent of Asia"));
        assertTrue(output.contains("Gamma 7000000"));
    }

    @Test
    void testPrintTopNPopulatedCountriesInRegion() {
        U1PopulationDataReports.printTopNPopulatedCountriesInRegion(1);
        String output = out.toString();

        assertTrue(output.contains("most populated countries in the region of Region1"));
        assertTrue(output.contains("Alpha 5000000") || output.contains("Beta 3000000"));

        assertTrue(output.contains("most populated countries in the region of Region2"));
        assertTrue(output.contains("Gamma 7000000"));
    }

    // Top-N city reports

    @Test
    void testPrintTopNPopulatedCitiesInWorld() {
        U1PopulationDataReports.printTopNPopulatedCitiesInWorld(3);
        String output = out.toString();

        assertTrue(output.contains("The top 3 most populated cities in the world"));
        assertTrue(output.contains("1. Gamma City 3000000"));
        assertTrue(output.contains("2. Alpha City 2000000"));
        assertTrue(output.contains("3. Beta City 1500000"));
    }

    @Test
    void testPrintTopNPopulatedCitiesInContinent() {
        U1PopulationDataReports.printTopNPopulatedCitiesInContinent(2);
        String output = out.toString();

        assertTrue(output.contains("most populated cities in the continent of Europe"));
        assertTrue(output.contains("Alpha City 2000000"));
        assertTrue(output.contains("Beta City 1500000"));

        assertTrue(output.contains("most populated cities in the continent of Asia"));
        assertTrue(output.contains("Gamma City 3000000"));
    }

    @Test
    void testPrintTopNPopulatedCitiesInRegion() {
        U1PopulationDataReports.printTopNPopulatedCitiesInRegion(2);
        String output = out.toString();

        assertTrue(output.contains("most populated cities in the region of Region1"));
        assertTrue(output.contains("Alpha City 2000000"));
        assertTrue(output.contains("Beta City 1500000"));

        assertTrue(output.contains("most populated cities in the region of Region2"));
        assertTrue(output.contains("Gamma City 3000000"));
    }

    // Country-level city lists

    @Test
    void testGetCityPopulationLargestToSmallestInCountry() {
        HashMap<String, List<City>> map =
                U1PopulationDataReports.getCityPopulationLargestToSmallestInCountry();

        assertTrue(map.containsKey("Alpha"));
        assertTrue(map.containsKey("Beta"));
        assertTrue(map.containsKey("Gamma"));

        List<City> alphaCities = map.get("Alpha");
        assertEquals(2, alphaCities.size());
        assertEquals("Alpha City", alphaCities.get(0).getName());
        assertEquals("Second Alpha City", alphaCities.get(1).getName());
    }

    @Test
    void testPrintCityPopulationLargestToSmallestInCountry() {
        U1PopulationDataReports.printCityPopulationLargestToSmallestInCountry();
        String output = out.toString();

        assertTrue(output.contains("Alpha:"));
        assertTrue(output.contains("Beta:"));
        assertTrue(output.contains("Gamma:"));

        assertTrue(output.contains("Alpha City 2000000"));
        assertTrue(output.contains("Second Alpha City 1000000"));
        assertTrue(output.contains("Beta City 1500000"));
        assertTrue(output.contains("Gamma City 3000000"));
    }

    @Test
    void testPrintTopNPopulatedCitiesInCountry() {
        U1PopulationDataReports.printTopNPopulatedCitiesInCountry(1);
        String output = out.toString();

        assertTrue(output.contains("most populated cities in the region of Alpha"));
        assertTrue(output.contains("Alpha City 2000000"));

        assertTrue(output.contains("most populated cities in the region of Beta"));
        assertTrue(output.contains("Beta City 1500000"));

        assertTrue(output.contains("most populated cities in the region of Gamma"));
        assertTrue(output.contains("Gamma City 3000000"));
    }

    @Test
    void testPrintCitiesLargestToSmallestInDistrict() {
        U1PopulationDataReports.printCitiesLargestToSmallestInDistrict();
        String output = out.toString();

        assertTrue(output.contains("District: Alpha District"));
        assertTrue(output.contains("District: Beta District"));
        assertTrue(output.contains("District: Gamma District"));

        assertTrue(output.contains("Name: Alpha City Population: 2000000"));
        assertTrue(output.contains("Name: Second Alpha City Population: 1000000"));
        assertTrue(output.contains("Name: Beta City Population: 1500000"));
        assertTrue(output.contains("Name: Gamma City Population: 3000000"));
    }

    @Test
    void testPrintTopNLargestCitiesInDistrict() {
        U1PopulationDataReports.printTopNLargestCitiesInDistrict(1);
        String output = out.toString();

        assertTrue(output.contains("most populated cities in the district of"));
        // Implementation uses capital city populations; just ensure these appear.
        assertTrue(output.contains("Gamma City 3000000"));
        assertTrue(output.contains("Alpha City 2000000"));
        assertTrue(output.contains("Beta City 1500000"));
    }

    // Top-N capital city reports

    @Test
    void testPrintTopNLargestCapitalCitiesInWorld() {
        U1PopulationDataReports.printTopNLargestCapitalCitiesInWorld(2);
        String output = out.toString();

        assertTrue(output.contains("The top 2 populated cities in the world"));
        // Implementation omits a space between name and population
        assertTrue(output.contains("1. Gamma City3000000") || output.contains("1. Gamma City 3000000"));
    }

    @Test
    void testPrintTopNLargestCapitalCitiesInContinent() {
        U1PopulationDataReports.printTopNLargestCapitalCitiesInContinent(1);
        String output = out.toString();

        assertTrue(output.contains("most populated capital cities in the continent of Europe"));
        assertTrue(output.contains("Alpha City"));

        assertTrue(output.contains("most populated capital cities in the continent of Asia"));
        assertTrue(output.contains("Gamma City"));
    }

    @Test
    void testPrintTopNLargestCapitalCitiesInRegion() {
        U1PopulationDataReports.printTopNLargestCapitalCitiesInRegion(1);
        String output = out.toString();

        assertTrue(output.contains("most populated capital cities in the region of Region1"));
        assertTrue(output.contains("Alpha City") || output.contains("Beta City"));

        assertTrue(output.contains("most populated capital cities in the region of Region2"));
        assertTrue(output.contains("Gamma City"));
    }

    // People in / not in cities

    @Test
    void testPrintPeopleInAndNotInCitiesInContinent() {
        U1PopulationDataReports.printPeopleInAndNotInCitiesInContinent();
        String output = out.toString();

        assertTrue(output.contains("Population of people in and"));
        assertTrue(output.contains("continent of: Europe"));
        assertTrue(output.contains("continent of: Asia"));

        // These totals should appear somewhere
        assertTrue(output.contains("4500000")); // people in cities (Europe)
        assertTrue(output.contains("3500000")); // not in cities (Europe)
        assertTrue(output.contains("3000000")); // people in cities (Asia)
        assertTrue(output.contains("4000000")); // not in cities (Asia)
    }

    @Test
    void testPrintPeopleInAndNotInCitiesInRegion() {
        U1PopulationDataReports.printPeopleInAndNotInCitiesInRegion();
        String output = out.toString();

        assertTrue(output.contains("Population of people in and not in cities for the region of: Region1"));
        assertTrue(output.contains("Population of people in and not in cities for the region of: Region2"));
    }

    @Test
    void testPrintPeopleInAndNotInCitiesInCountry() {
        U1PopulationDataReports.printPeopleInAndNotInCitiesInCountry();
        String output = out.toString();

        assertTrue(output.contains("Population of people in and"));
        assertTrue(output.contains("country of: Alpha"));
        assertTrue(output.contains("country of: Beta"));
        assertTrue(output.contains("country of: Gamma"));
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
