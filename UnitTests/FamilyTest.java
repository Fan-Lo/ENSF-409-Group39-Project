/** 
* FamilyTest.java
* @author     Justin Kuhn
* href= "mailto:justinkuhn@ucalgary.ca">justin.kuhn@ucalgary.ca</a>
* @version 2.2
* @since 1.0	
**/  

package edu.ucalgary.ensf409;

import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.*;
import java.util.*;

public class FamilyTest {

    public FamilyTest() { }

    // Test the constructor for Family
    @Test
    public void testFamilyConstructor() {
		int aMale = 1, aFemale = 1, childA8 = 1, childU8 = 1;
		Family testFamily = new Family(aMale, aFemale, childA8, childU8);

	    // Assert that Family constructor creates a valid Object
        assertNotNull("The Family object is null.", testFamily); 
    }
	
	// Test getWeeklyGrainNeeds()
    @Test
    public void testGetWeeklyGrainNeeds() {
		int aMale = 1, aFemale = 0, childA8 = 0, childU8 = 0;
		Family testFamily = new Family(aMale, aFemale, childA8, childU8);
		
		int expectedGrainNeeds = 400 * 7;
		int actualGrainNeeds = testFamily.getWeeklyGrainNeeds();
		
		// Assert that getWeeklyGrainNeeds() returns the grain needs of the family multiplied by 7
		assertEquals("getWeeklyGrainNeeds() returned the incorrect value.", expectedGrainNeeds, actualGrainNeeds);
    }
	
	// Test getWeeklyVeggieNeeds()
    @Test
    public void testGetWeeklyVeggieNeeds() {
		int aMale = 1, aFemale = 0, childA8 = 0, childU8 = 0;
		Family testFamily = new Family(aMale, aFemale, childA8, childU8);
		
		int expectedVeggieNeeds = 701 * 7;
		int actualVeggieNeeds = testFamily.getWeeklyVeggieNeeds();
		
		// Assert that getWeeklyVeggieNeeds() returns the veggie needs of the family multiplied by 7
		assertEquals("getWeeklyVeggieNeeds() returned the incorrect value.", expectedVeggieNeeds, actualVeggieNeeds);
    }
	
	// Test getWeeklyProteinNeeds()
    @Test
    public void testGetWeeklyProteinNeeds() {
		int aMale = 1, aFemale = 0, childA8 = 0, childU8 = 0;
		Family testFamily = new Family(aMale, aFemale, childA8, childU8);
		
		int expectedProteinNeeds = 650 * 7;
		int actualProteinNeeds = testFamily.getWeeklyProteinNeeds();
		
		// Assert that getWeeklyProteinNeeds() returns the protein needs of the family multiplied by 7
		assertEquals("getWeeklyProteinNeeds() returned the incorrect value.", expectedProteinNeeds, actualProteinNeeds);
    }
	
	// Test getWeeklyOtherNeeds()
    @Test
    public void testGetWeeklyOtherNeeds() {
		int aMale = 1, aFemale = 0, childA8 = 0, childU8 = 0;
		Family testFamily = new Family(aMale, aFemale, childA8, childU8);
		
		int expectedOtherNeeds = 750 * 7;
		int actualOtherNeeds = testFamily.getWeeklyOtherNeeds();
		
		// Assert that getWeeklyOtherNeeds() returns the other needs of the family multiplied by 7
		assertEquals("getWeeklyOtherNeeds() returned the incorrect value.", expectedOtherNeeds, actualOtherNeeds);
    }
	
	// Test getWeeklyCalorieNeeds()
    @Test
    public void testGetWeeklyCalorieNeeds() {
		int aMale = 1, aFemale = 0, childA8 = 0, childU8 = 0;
		Family testFamily = new Family(aMale, aFemale, childA8, childU8);
		
		int expectedCalorieNeeds = 2500 * 7;
		int actualCalorieNeeds = testFamily.getWeeklyCalorieNeeds();
		
		// Assert that getWeeklyCalorieNeeds() returns the calorie needs of the family multiplied by 7
		assertEquals("getWeeklyCalorieNeeds() returned the incorrect value.", expectedCalorieNeeds, actualCalorieNeeds);
    }
	
	// Test createHamper()
    @Test
    public void testCreateHamper() throws ItemNotFoundException{
		int aMale = 1, aFemale = 0, childA8 = 0, childU8 = 0;
		Family testFamily = new Family(aMale, aFemale, childA8, childU8);
		Inventory inventory = new Inventory();
		
		testFamily.createHamper(inventory);
		Hamper theHamper = testFamily.getHamper();
		
		// Assert that createHamper() created a hamper properly
		assertNotNull("createHamper() did not create a hamper properly.", theHamper);
    }
	
	// Test getHamper()
    @Test
    public void testGetHamper() throws ItemNotFoundException{
		int aMale = 1, aFemale = 0, childA8 = 0, childU8 = 0;
		Family testFamily = new Family(aMale, aFemale, childA8, childU8);
		Inventory inventory = new Inventory();
		
		testFamily.createHamper(inventory);
		Hamper theHamper = testFamily.getHamper();
		
		// Assert that getHamper() returns the correct hamper
		assertNotNull("getHamper() did not return the correct hamper.", theHamper);
    }
	
	// Test getNumAMale()
    @Test
    public void testGetNumAMale() {
		int expectedNumAMale = 1, aFemale = 1, childA8 = 2, childU8 = 3;
		Family testFamily = new Family(expectedNumAMale, aFemale, childA8, childU8);
		
		int actualNumAMale = testFamily.getNumAMale();
		
		// Assert that getNumAMale() returns the number of adult males in the family
		assertEquals("getNumAMale() returned the incorrect value.", expectedNumAMale, actualNumAMale);
    }
	
	// Test getNumAFemale()
    @Test
    public void testGetNumAFemale() {
		int aMale = 1, expectedNumAFemale = 1, childA8 = 2, childU8 = 3;
		Family testFamily = new Family(aMale, expectedNumAFemale, childA8, childU8);
		
		int actualNumAFemale = testFamily.getNumAFemale();
		
		// Assert that getNumAFemale() returns the number of adult females in the family
		assertEquals("getNumAFemale() returned the incorrect value.", expectedNumAFemale, actualNumAFemale);
    }
	
	// Test getNumChildA8()
    @Test
    public void testGetNumChildA8() {
		int aMale = 1, aFemale = 1, expectedNumChildA8 = 2, childU8 = 3;
		Family testFamily = new Family(aMale, aFemale, expectedNumChildA8, childU8);
		
		int actualNumChildA8 = testFamily.getNumChildA8();
		
		// Assert that getNumChildA8() returns the number of children over 8 in the family
		assertEquals("getNumChildA8() returned the incorrect value.", expectedNumChildA8, actualNumChildA8);
    }
	
	// Test getNumChildU8()
    @Test
    public void testGetNumChildU8() {
		int aMale = 1, aFemale = 1, childA8 = 2, expectedNumChildU8 = 3;
		Family testFamily = new Family(aMale, aFemale, childA8, expectedNumChildU8);
		
		int actualNumChildU8 = testFamily.getNumChildU8();
		
		// Assert that getNumChildU8() returns the number of children under 8 in the family
		assertEquals("getNumChildU8() returned the incorrect value.", expectedNumChildU8, actualNumChildU8);
    }
    
}