package edu.ucalgary.ensf409;

import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.*;

public class PersonTest {

    public PersonTest() { }

    // Test the constructor for Person
    @Test
    public void testPersonConstructor() {
		Nutrition nutrition = new Nutrition(10, 20, 30, 40, 2000);
		int clientID = 1;
        Person testPerson = new Person(nutrition, clientID);

	    // Assert that Person constructor creates a valid Object
        assertNotNull("The Person object is null.", testPerson); 
    }
	
	// Test getNutritionalNeeds()
    @Test
    public void testGetNutritionalNeeds() {
		Nutrition expectedNutrition = new Nutrition(10, 20, 30, 40, 2000);
		int clientID = 1;
        Person testPerson = new Person(expectedNutrition, clientID);
		
		Nutrition actualNutrition = testPerson.getNutritionalNeeds();

		// Assert that the Nutrition object returned is correct
        assertEquals("getNutritionalNeeds() returned the wrong Nutrition.", actualNutrition, expectedNutrition); 
    }
	
	// Test getClientID()
    @Test
    public void testGetClientID() {
		Nutrition nutrition = new Nutrition(10, 20, 30, 40, 2000);
		int expectedClientID = 1;
        Person testPerson = new Person(nutrition, expectedClientID);
		
		int actualClientID = testPerson.getClientID();

		// Assert that the ClientID returned is correct
        assertEquals("getClientID() returned the wrong value.", actualClientID, expectedClientID); 
    }
    
}