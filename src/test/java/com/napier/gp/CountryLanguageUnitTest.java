package com.napier.gp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.napier.gp.world.CountryLanguage;

import static org.junit.jupiter.api.Assertions.*;

class CountryLanguageUnitTest {

    private CountryLanguage language;

    @BeforeEach
    void setUp() {
        language = new CountryLanguage(
                "GBR",
                "English",
                "T",
                98.5
        );
    }

    // -------------------------------------------------------
    // CONSTRUCTOR + GETTERS
    // -------------------------------------------------------

    @Test
    void constructor_setsAllFieldsCorrectly() {
        assertEquals("GBR", language.getCountryCode());
        assertEquals("English", language.getLanguage());
        assertEquals("T", language.getIsOfficial());
        assertEquals(98.5, language.getPercentage());
    }

    // -------------------------------------------------------
    // SETTERS
    // -------------------------------------------------------

    @Test
    void setCountryCode_updatesCountryCode() {
        language.setCountryCode("USA");
        assertEquals("USA", language.getCountryCode());
    }

    @Test
    void setLanguage_updatesLanguage() {
        language.setLanguage("French");
        assertEquals("French", language.getLanguage());
    }

    @Test
    void setIsOfficial_updatesIsOfficial() {
        language.setIsOfficial("F");
        assertEquals("F", language.getIsOfficial());
    }

    @Test
    void setPercentage_updatesPercentage() {
        language.setPercentage(45.0);
        assertEquals(45.0, language.getPercentage());
    }

    // -------------------------------------------------------
    // toString()
    // -------------------------------------------------------

    @Test
    void toString_containsAllImportantFields() {
        String result = language.toString();

        assertTrue(result.contains("English"));
        assertTrue(result.contains("T"));
        assertTrue(result.contains("98.5"));
    }
}
