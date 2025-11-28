package com.napier.gp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.napier.gp.world.*;

import static org.junit.jupiter.api.Assertions.*;

class CountryUnitTest {

    private Country country;
    private City city;
    private CountryLanguage language;

    @BeforeEach
    void setUp() {
        country = new Country(
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

        city = new City(1, "London", "GBR", "England", 9000000);
        language = new CountryLanguage("GBR", "English", "T", 98.0);
    }

    // -------------------------------------------------------
    // CONSTRUCTOR + GETTERS
    // -------------------------------------------------------

    @Test
    void constructor_setsAllFieldsCorrectly() {
        assertEquals("GBR", country.getCode());
        assertEquals("United Kingdom", country.getName());
        assertEquals("Europe", country.getContinent());
        assertEquals("British Isles", country.getRegion());
        assertEquals(243610.0, country.getSurfaceArea());
        assertEquals(1707, country.getIndepYear());
        assertEquals(67000000, country.getPopulation());
        assertEquals(81.2, country.getLifeExpectancy());
        assertEquals(2800000.0, country.getGNP());
        assertEquals(2700000.0, country.getGNPOld());
        assertEquals("United Kingdom", country.getLocalName());
        assertEquals("Constitutional Monarchy", country.getGovernmentForm());
        assertEquals("King", country.getHeadOfState());
        assertEquals(456, country.getCapital());
        assertEquals("GB", country.getCode2());
        assertNotNull(country.getCities());
        assertNotNull(country.getLanguages());
        assertTrue(country.getCities().isEmpty());
        assertTrue(country.getLanguages().isEmpty());
    }

    // -------------------------------------------------------
    // SETTERS
    // -------------------------------------------------------

    @Test
    void setters_updateValuesCorrectly() {
        country.setCode("USA");
        country.setName("United States");
        country.setContinent("North America");
        country.setRegion("NA");
        country.setSurfaceArea(9833520.0);
        country.setIndepYear(1776);
        country.setPopulation(331000000);
        country.setLifeExpectancy(78.9);
        country.setGNP(21000000.0);
        country.setGNPOld(20000000.0);
        country.setLocalName("USA");
        country.setGovernmentForm("Federal Republic");
        country.setHeadOfState("President");
        country.setCapital(999);
        country.setCode2("US");

        assertEquals("USA", country.getCode());
        assertEquals("United States", country.getName());
        assertEquals("North America", country.getContinent());
        assertEquals("NA", country.getRegion());
        assertEquals(9833520.0, country.getSurfaceArea());
        assertEquals(1776, country.getIndepYear());
        assertEquals(331000000, country.getPopulation());
        assertEquals(78.9, country.getLifeExpectancy());
        assertEquals(21000000.0, country.getGNP());
        assertEquals(20000000.0, country.getGNPOld());
        assertEquals("USA", country.getLocalName());
        assertEquals("Federal Republic", country.getGovernmentForm());
        assertEquals("President", country.getHeadOfState());
        assertEquals(999, country.getCapital());
        assertEquals("US", country.getCode2());
    }

    // -------------------------------------------------------
    // CITY MANAGEMENT
    // -------------------------------------------------------

    @Test
    void addAndRemoveCity_updatesCityListCorrectly() {
        assertTrue(country.getCities().isEmpty());

        country.addCity(city);
        assertEquals(1, country.getCities().size());
        assertEquals("London", country.getCities().get(0).getName());

        country.removeCity(city);
        assertTrue(country.getCities().isEmpty());
    }

    // -------------------------------------------------------
    // LANGUAGE MANAGEMENT
    // -------------------------------------------------------

    @Test
    void addAndRemoveLanguage_updatesLanguageListCorrectly() {
        assertTrue(country.getLanguages().isEmpty());

        country.addLanguage(language);
        assertEquals(1, country.getLanguages().size());
        assertEquals("English", country.getLanguages().get(0).getLanguage());

        country.removeLanguage(language);
        assertTrue(country.getLanguages().isEmpty());
    }

    // -------------------------------------------------------
    // toString()
    // -------------------------------------------------------

    @Test
    void toString_containsCoreFields() {
        String result = country.toString();

        assertTrue(result.contains("GBR"));
        assertTrue(result.contains("United Kingdom"));
        assertTrue(result.contains("Europe"));
        assertTrue(result.contains("British Isles"));
        assertTrue(result.contains("SurfaceArea=243610.0"));
        assertTrue(result.contains("Capital=456"));
        assertTrue(result.contains("Code2='GB'"));
    }

    @Test
    void toString_includesCitiesAndLanguages() {
        country.addCity(city);
        country.addLanguage(language);

        String result = country.toString();

        assertTrue(result.contains("London"));
        assertTrue(result.contains("English"));
    }
}
