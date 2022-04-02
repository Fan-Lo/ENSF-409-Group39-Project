package edu.ucalgary.ensf409;

import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.*;
import java.util.*;

public class FamilyTest {

    public FamilyTest() { }

    // Test the constructor for Family
    @Test
    public void testPersonConstructor() {
		Family testFamily = new Family();

	    // Assert that Family constructor creates a valid Object
        assertNotNull("The Family object is null.", testFamily); 
    }
	
	// Test addFamilyMember()
    @Test
    public void testAddFamilyMember() {
		Nutrition nutrition = new Nutrition(10, 20, 30, 40, 2000);
		int clientID = 1;
        Person testPerson = new Person(nutrition, clientID);
		Family testFamily = new Family();
		testFamily.addFamilyMember(testPerson);
		
		int expectedSize = 1;
		int actualSize = testFamily.getFamilyMembers().size();
		
		assertEquals("addFamilyMember() did not work as intended.", actualSize, expectedSize);
    }
	
	// Test removeFamilyMember()
    @Test
    public void testRemoveFamilyMember() {
		Nutrition nutrition = new Nutrition(10, 20, 30, 40, 2000);
		int clientID = 1;
        Person testPerson = new Person(nutrition, clientID);
		Family testFamily = new Family();
		testFamily.addFamilyMember(testPerson);
		testFamily.removeFamilyMember(testPerson);
		
		int expectedSize = 0;
		int actualSize = testFamily.getFamilyMembers().size();
		
		assertEquals("addFamilyMember() did not work as intended.", actualSize, expectedSize);
    }
	
	// Test weeklyFamilyNeeds()
    @Test
    public void testWeeklyFamilyNeeds() {
		Nutrition nutrition = new Nutrition(10, 20, 30, 40, 2000);
		int clientID = 1;
        Person testPerson = new Person(nutrition, clientID);
		Family testFamily = new Family();
		testFamily.addFamilyMember(testPerson);
		
		Nutrition weeklyNeeds = testFamily.weeklyFamilyNeeds();
		int expectedCals = 2000 * 7;
		int actualCals = weeklyNeeds.getCalories();
		
		assertEquals("An incorrect calculation for weekly needs occured.", actualCals, expectedCals);
    }
	
	// Test getGrainNeeds()
    @Test
    public void testGetGrainNeeds() {
		Nutrition nutrition = new Nutrition(10, 20, 30, 40, 2000);
		int clientID = 1;
        Person testPerson = new Person(nutrition, clientID);
		Family testFamily = new Family();
		testFamily.addFamilyMember(testPerson);
		
		int expectedGrain = (int)((10/(double)100) * 2000);
		int actualGrain = testFamily.getGrainNeeds();
		
		assertEquals("getGrainNeeds() returned the incorrect amount.", actualGrain, expectedGrain);
    }
	
	// Test getVeggieNeeds()
    @Test
    public void testGetVeggieNeeds() {
		Nutrition nutrition = new Nutrition(10, 20, 30, 40, 2000);
		int clientID = 1;
        Person testPerson = new Person(nutrition, clientID);
		Family testFamily = new Family();
		testFamily.addFamilyMember(testPerson);
		
		int expectedVeggie = (int)((20/(double)100) * 2000);
		int actualVeggie = testFamily.getVeggieNeeds();
		
		assertEquals("getVeggieNeeds() returned the incorrect amount.", actualVeggie, expectedVeggie);
    }
	
	// Test getProteinNeeds()
    @Test
    public void testGetProteinNeeds() {
		Nutrition nutrition = new Nutrition(10, 20, 30, 40, 2000);
		int clientID = 1;
        Person testPerson = new Person(nutrition, clientID);
		Family testFamily = new Family();
		testFamily.addFamilyMember(testPerson);
		
		int expectedProtein = (int)((30/(double)100) * 2000);
		int actualProtein = testFamily.getProteinNeeds();
		
		assertEquals("getProteinNeeds() returned the incorrect amount.", actualProtein, expectedProtein);
    }
	
	// Test getOtherNeeds()
    @Test
    public void testGetOtherNeeds() {
		Nutrition nutrition = new Nutrition(10, 20, 30, 40, 2000);
		int clientID = 1;
        Person testPerson = new Person(nutrition, clientID);
		Family testFamily = new Family();
		testFamily.addFamilyMember(testPerson);
		
		int expectedOther = (int)((40/(double)100) * 2000);
		int actualOther = testFamily.getGrainNeeds();
		
		assertEquals("getOtherNeeds() returned the incorrect amount.", actualOther, expectedOther);
    }
	
	// Test getCalorieNeeds()
    @Test
    public void testGetCalorieNeeds() {
		Nutrition nutrition = new Nutrition(10, 20, 30, 40, 2000);
		int clientID = 1;
        Person testPerson = new Person(nutrition, clientID);
		Family testFamily = new Family();
		testFamily.addFamilyMember(testPerson);
		
		int expectedCalories = 2000;
		int actualCalories = testFamily.getCalorieNeeds();
		
		assertEquals("getCalorieNeeds() returned the incorrect amount.", actualCalories, expectedCalories);
    }
	
	// Test getFamilyMembers
    @Test
    public void testGetFamilyMembers() {
		Nutrition nutrition = new Nutrition(10, 20, 30, 40, 2000);
		int clientID = 1;
        Person testPerson = new Person(nutrition, clientID);
		Family testFamily = new Family();
		testFamily.addFamilyMember(testPerson);
		
		ArrayList<Person> members = testFamily.getFamilyMembers();
		Person expectedPerson = testPerson;
		int actualPerson = members.get(0);
		
		assertEquals("getFamilyMembers() did not work as expected.", actualPerson, expectedPerson);
    }
	
	// Test createHamper()
    @Test
    public void testCreateHamper() {
		Nutrition nutrition = new Nutrition(10, 20, 30, 40, 2000);
		int clientID = 1;
        Person testPerson = new Person(nutrition, clientID);
		Family testFamily = new Family();
		testFamily.addFamilyMember(testPerson);
		Nutrition weeklyNeeds = testFamily.weeklyFamilyNeeds();
		
		testFamily.createHamper(weeklyNeeds);
		Hamper theHamper = testFamily.getHamper();
		
		assertNotNull("createHamper() did not create a hamper properly.", theHamper);
    }
	
    
}