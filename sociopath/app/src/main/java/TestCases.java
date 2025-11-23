/**
 * TestCases class to test the Sociopath finder functionality.
 * This class contains multiple test cases to validate the correctness of the Sociopath class so you have to compile from here
 * 
 * Believe me, I tried to use JUnit but I could not get it to work in the time i had and got so frustrated I made my own simple test handler. It probably didn't help that my Eclipse IDE stopped working.
 * 
 * @author Kody Ho
 */

import java.util.Random; // for random graph generation

public class TestCases {

    private  static Random random;
    private static int testsPassed = 0;
    private static int testsFailed = 0;
    
    private static boolean assertEqual(int expected, int actual, String testCase) {
        if (expected != actual) {
            System.out.println("FAILED: " + testCase + " | Expected: " + expected + " | Got: " + actual);
            testsFailed++;
            return false;
        }
        System.out.println("PASSED: " + testCase);
        testsPassed++;
        return true;
    }
    
    /**
     * Generates a graph with a sociopath at the specified index. (helper method to create graphs for testing)
     * @param groupSize The total number of individuals in the group.
     * @param sociopathIndex The index of the sociopath (0-based).  
     * @return A 2D array representing the edges of the graph.
     */
    private static int[][] generateGraphWithSociopath(int groupSize, int sociopathIndex) {
    int[][] edges = new int[groupSize - 1][2];
    int edgeIndex = 0;
    
    int sociopath = sociopathIndex;
    
    for (int i = 1; i <= groupSize; i++) { // 0 isn't a person so we start at 1
        if (i != sociopath) {
            edges[edgeIndex][0] = i;
            edges[edgeIndex][1] = sociopath;
            edgeIndex++;
        }
    }
    
    return edges;
    }  
    
    /**
     * All of the following functions are test cases for the Sociopath class. not sure if i need javadoc for all of them they should be self explanatory esp. if you read the name
     * each test case creates a Sociopath instance with specific parameters and checks the output of findTheSociopath against the expected result.
     * they are all taken from the lab description except the random graph tests and the large group test
     * 
     */
    private static void testSmallGroupSociopath() {
        int groupSize = 2;
        int[][] edges = {
            {1, 2}
        };
        Sociopath sociopath = new Sociopath(groupSize, edges);
        assertEqual(2, sociopath.findTheSociopath(), "2 = 2 likes no one; everyone likes 2");
    }
    private static void testMidGroupsNoSociopath() {
        int groupSize = 3;
        int[][] edges = {
            {1, 2}
        };
        Sociopath sociopath = new Sociopath(groupSize, edges);
        assertEqual(-1, sociopath.findTheSociopath(), "-1 = No one likes 3");
    }
    private static void testMidGroupSociopath() {
        int groupSize = 3;
        int[][] edges = {
            {1, 2},
            {1, 3},
            {2, 3}
        };
        Sociopath sociopath = new Sociopath(groupSize, edges);
        assertEqual(3, sociopath.findTheSociopath(), "3 = 3 likes no one; everyone likes 3");
    }
    private static void testNoSociopath() {
        int groupSize = 3;
        int[][] edges = {
            {1, 3},
            {2, 3},
            {3, 1}
        };
        Sociopath sociopath = new Sociopath(groupSize, edges);
        assertEqual(-1, sociopath.findTheSociopath(), "-1 = Each person likes someone else");
    }
    private static void testInvalidGroupSize() {
        int groupSize = 0;
        int[][] edges = {
            {1, 2}
        };
        Sociopath sociopath = new Sociopath(groupSize, edges);
        assertEqual(-1, sociopath.findTheSociopath(), "Invalid group size / invalid people");
    }
    private static void testInvalidPerson() {
        int groupSize = 3;
        int[][] edges = {
            {1, 0},
        };
        Sociopath sociopath = new Sociopath(groupSize, edges);
        assertEqual(-1, sociopath.findTheSociopath(), "-1 = 0 is not a person");
    }
    
    private static void testLargeGroupSociopath() {
        int groupSize = 200;
        int sociopathIndex = 100;
        int[][] edges = generateGraphWithSociopath(groupSize, sociopathIndex);
        Sociopath sociopath = new Sociopath(groupSize, edges);
        assertEqual(100, sociopath.findTheSociopath(), "100 = 100 likes no one; everyone likes 100");
    }

    /**
     * Generates a random graph with a sociopath and tests the Sociopath class.
     * The group size is randomly chosen between 2 and 500.
     */
    private static void testRandomGraphSociopath() {
        int groupSize = random.nextInt(2, 501); // 2 to 500 inclusive
        int sociopathIndex = random.nextInt(1, groupSize + 1); // 1 to groupSize inclusive
        int[][] edges = generateGraphWithSociopath(groupSize, sociopathIndex);
        Sociopath sociopath = new Sociopath(groupSize, edges);
        assertEqual(sociopathIndex, sociopath.findTheSociopath(), sociopathIndex + " = " + sociopathIndex + " likes no one; everyone likes " + sociopathIndex);
    }

    /**
     * like above but without a sociopath
     */
    private static void testRandomGraphNoSociopath() {
        int groupSize = random.nextInt(2, 501); // 2 to 500 inclusive
        int sociopathIndex = random.nextInt(1, groupSize + 1); // 1 to groupSize inclusive
        int[][] edges = generateGraphWithSociopath(groupSize, sociopathIndex);

        // Change a random edge to break the sociopath condition (everyone likes them)
        int breakIndex = random.nextInt(0, groupSize - 1); // 0 to groupSize-2 inclusive
        edges[breakIndex][1] = (edges[breakIndex][1] % groupSize) + 1; // change to like someone else, % groupsize to wrap around
        Sociopath sociopath = new Sociopath(groupSize, edges);
        assertEqual(-1, sociopath.findTheSociopath(), "-1 = not everyone likes likes " + sociopathIndex);
    }

    /**
     * Runs all test cases and summarizes results.
     */
    private static void runAllTests() {
        System.out.println("=== Running Sociopath Finder Tests ===\n");
        
        testSmallGroupSociopath();
        testMidGroupsNoSociopath();
        testMidGroupSociopath();
        testNoSociopath();
        testInvalidGroupSize();
        testInvalidPerson();
        testLargeGroupSociopath();
        testRandomGraphSociopath();
        testRandomGraphNoSociopath();
        
        System.out.println("\n=== Test Summary ===");
        System.out.println("Tests Passed: " + testsPassed); // these are calculated inside assertEqual
        System.out.println("Tests Failed: " + testsFailed);
        System.out.println("Total Tests: " + (testsPassed + testsFailed));
    }
    
    public static void main(String[] args) { // i could have put runAllTests in main but :]
        random = new Random();
        runAllTests();
    }
}