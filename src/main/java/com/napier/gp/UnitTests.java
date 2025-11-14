//package com.napier.gp;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class UnitTests {
//
//    private Db testDB;
//
//    @BeforeEach
//    void setUp() {
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    void checkWorldPopulation() {
//        assertEquals(6078749450L, U2PopulationReports.returnWorldPopulation());
//    }
//
//    @Test
//    void checkCityPopulation() {
//        assertEquals(609823, U2PopulationReports.printCityPopulationByKey("Oran"));
//    }
//
//    @Test
//    void checkDistrictPopulation() {
//        assertEquals(253009, U2PopulationReports.printDistrictPopulationByKey("Aceh"));
//    }
//
//    @Test
//    void checkCountryPopulation() {
//        assertEquals(103000, U2PopulationReports.printCountryPopulationByKey("Aruba"));
//    }
//
//    @Test
//    void checkRegionPopulation() {
//        assertEquals(38140000, U2PopulationReports.printRegionPopulationByKey("Caribbean"));
//    }
//
//    @Test
//    void checkContinentPopulation() {
//        assertEquals(730074600, U2PopulationReports.printContinentPopulationByKey("Europe"));
//    }
//}
