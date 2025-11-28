package com.napier.gp;
//
//import com.napier.gp.world.Country;
//import com.napier.gp.world.World;
//import org.junit.jupiter.api.*;
//
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class U1PopulationDataReportsIntegrationTest {

}
//
//    private final PrintStream originalOut = System.out;
//    private ByteArrayOutputStream out;
//
//    @BeforeAll
//    void loadWorldSampleData() {
//        World world = World.getInstance();
//
//        // Clear existing data (if any)
//        List<Country> countries = world.getCountries();
//        countries.clear();
//
//        // Add sample countries – dummy values for fields we don't care about
//        countries.add(new Country(
//                "CHN", "China", "Asia", "Eastern Asia",
//                9596961.0, 1949, 1400000000,
//                76.9, 14342903.0, 0.0,
//                "Zhongguo", "Republic", "Head", 1, "CN"
//        ));
//
//        countries.add(new Country(
//                "IND", "India", "Asia", "Southern Asia",
//                3287263.0, 1947, 1300000000,
//                69.7, 2875142.0, 0.0,
//                "Bharat", "Republic", "Head", 2, "IN"
//        ));
//
//        countries.add(new Country(
//                "USA", "United States", "North America", "North America",
//                9833517.0, 1776, 330000000,
//                78.9, 21433226.0, 0.0,
//                "United States", "Federal Republic", "Head", 3, "US"
//        ));
//
//        countries.add(new Country(
//                "GBR", "United Kingdom", "Europe", "British Islands",
//                243610.0, 1707, 67000000,
//                81.2, 2827113.0, 0.0,
//                "United Kingdom", "Constitutional Monarchy", "Head", 4, "GB"
//        ));
//
//        countries.add(new Country(
//                "FRA", "France", "Europe", "Western Europe",
//                551695.0, 843, 65000000,
//                82.5, 2715518.0, 0.0,
//                "France", "Republic", "Head", 5, "FR"
//        ));
//    }
//    // Runs before each test.
//    // Redirects System.out so any printed output can be captured for assertions.
//    @BeforeEach
//    void startCapture() {
//        out = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(out));
//    }
//
//    // Runs after each test.
//    // Restores the original System.out so normal console output continues.
//    @AfterEach
//    void stopCapture() {
//        System.setOut(originalOut);
//    }
//    // Test 1: Checks that the world report prints countries in descending population order.
//    @Test
//    void printWorldReport_isSortedDescending() {
//        U1PopulationDataReports.printCountryPopulationLargestToSmallestInWorld();
//
//        String output = out.toString().trim();
//        assertFalse(output.isEmpty());
//
//        String[] lines = output.split("\\R+");
//        // Expect at least the 5 we added
//        assertTrue(lines.length >= 5);
//
//        // First two lines should be China then India (based on population we set)
//        assertEquals("China 1400000000", lines[0]);
//        assertEquals("India 1300000000", lines[1]);
//
//        // Check descending populations
//        long previous = Long.MAX_VALUE;
//        for (String line : lines) {
//            String popStr = line.substring(line.lastIndexOf(" ") + 1);
//            long pop = Long.parseLong(popStr);
//            assertTrue(pop <= previous, "Population not in descending order");
//            previous = pop;
//        }
//    }
//    // Test 2: Checks that the continent report groups countries by continent and sorts each group.
//    @Test
//    void printContinentReport_groupsAndSorts() {
//        U1PopulationDataReports.printCountryPopulationLargestToSmallestInContinent();
//
//        String output = out.toString();
//        assertFalse(output.isEmpty());
//
//        // Check continent headers exist
//        assertTrue(output.contains("Asia:"), "Asia header missing");
//        assertTrue(output.contains("Europe:"), "Europe header missing");
//
//        // Within Asia, China should appear before India
//        int asiaIndex = output.indexOf("Asia:");
//        int chinaIndex = output.indexOf("China", asiaIndex);
//        int indiaIndex = output.indexOf("India", asiaIndex);
//
//        assertTrue(chinaIndex > asiaIndex);
//        assertTrue(indiaIndex > asiaIndex);
//        assertTrue(chinaIndex < indiaIndex, "China should appear before India in Asia block");
//    }
//    // Test 3: Checks that the region report prints regional headers and correctly groups countries.
//    @Test
//    void printRegionReport_groupsAndSorts() {
//        U1PopulationDataReports.printCountryPopulationLargestToSmallestInRegion();
//
//        String output = out.toString();
//        assertFalse(output.isEmpty());
//
//        // Region headers
//        assertTrue(output.contains("Eastern Asia:"), "Eastern Asia header missing");
//        assertTrue(output.contains("Southern Asia:"), "Southern Asia header missing");
//        assertTrue(output.contains("British Islands:"), "British Islands header missing");
//        assertTrue(output.contains("Western Europe:"), "Western Europe header missing");
//
//        // Example: China must appear after its region header
//        int regionIndex = output.indexOf("Eastern Asia:");
//        int chinaIndex = output.indexOf("China", regionIndex);
//
//        assertTrue(chinaIndex > regionIndex, "China should appear under Eastern Asia region");
//    }
//}
