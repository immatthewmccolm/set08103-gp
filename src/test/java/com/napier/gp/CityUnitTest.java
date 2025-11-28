package com.napier.gp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.napier.gp.world.*;

import static org.junit.jupiter.api.Assertions.*;

class CityUnitTest {

    private City city;

    @BeforeEach
    void setUp() {
        city = new City(
                1,
                "Edinburgh",
                "GBR",
                "Scotland",
                550000
        );
    }

    // -------------------------------------------------------
    // Constructor + Getters
    // -------------------------------------------------------

    @Test
    void constructor_setsAllFieldsCorrectly() {
        assertEquals(1, city.getID());
        assertEquals("Edinburgh", city.getName());
        assertEquals("GBR", city.getCountryCode());
        assertEquals("Scotland", city.getDistrict());
        assertEquals(550000, city.getPopulation());
    }

    // -------------------------------------------------------
    // Setters
    // -------------------------------------------------------

    @Test
    void setID_updatesID() {
        city.setID(2);
        assertEquals(2, city.getID());
    }

    @Test
    void setName_updatesName() {
        city.setName("Glasgow");
        assertEquals("Glasgow", city.getName());
    }

    @Test
    void setCountryCode_updatesCountryCode() {
        city.setCountryCode("FRA");
        assertEquals("FRA", city.getCountryCode());
    }

    @Test
    void setDistrict_updatesDistrict() {
        city.setDistrict("Île-de-France");
        assertEquals("Île-de-France", city.getDistrict());
    }

    @Test
    void setPopulation_updatesPopulation() {
        city.setPopulation(1200000);
        assertEquals(1200000, city.getPopulation());
    }

    // -------------------------------------------------------
    // toString()
    // -------------------------------------------------------

    @Test
    void toString_returnsFormattedString() {
        String result = city.toString();

        assertTrue(result.contains("ID=1"));
        assertTrue(result.contains("Edinburgh"));
        assertTrue(result.contains("Scotland"));
        assertTrue(result.contains("Population=550000"));
    }
}
