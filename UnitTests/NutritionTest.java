/** 
* NutritionTest.java
* @version 2.3
* @since 1.0	
**/  

package edu.ucalgary.ensf409;

import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.*;

public class NutritionTest {
	 public NutritionTest() { }
	 
	// Test the constructor for Nutrition
    @Test
    public void testNutritionConstructor() {
		int grain = 16, fruit = 28, protein = 26, other = 30, total = 2500;
		Nutrition myNutrition = new Nutrition(grain, fruit, protein, other, total);

	    // Assert that Nutrition constructor creates a valid Object
        assertNotNull("The Nutrition object is null.", myNutrition); 
    }
	
	// Test getWholeGrain()
    @Test
    public void testGetWholeGrain() {
		int grain = 16, fruit = 28, protein = 26, other = 30, total = 2500;
		Nutrition myNutrition = new Nutrition(grain, fruit, protein, other, total);
		
		int expectedWholeGrain = (int)Math.ceil((grain/(double)100) * total);
		int actualWholeGrain = myNutrition.getWholeGrain();

	    // Assert that getWholeGrain() returns the correct value
		assertEquals("getWholeGrain() returned the incorrect value.", expectedWholeGrain, actualWholeGrain); 
    }
	
	// Test getFruitsVeggies()
    @Test
    public void testGetFruitsVeggies() {
		int grain = 16, fruit = 28, protein = 26, other = 30, total = 2500;
		Nutrition myNutrition = new Nutrition(grain, fruit, protein, other, total);
		
		int expectedFruitsVeggies = (int)Math.ceil((fruit/(double)100) * total);
		int actualFruitsVeggies = myNutrition.getFruitsVeggies();

	    // Assert that getFruitsVeggies() returns the correct value
		assertEquals("getFruitsVeggies() returned the incorrect value.", expectedFruitsVeggies, actualFruitsVeggies); 
    }
	
	// Test getProtein()
    @Test
    public void testGetProtein() {
		int grain = 16, fruit = 28, protein = 26, other = 30, total = 2500;
		Nutrition myNutrition = new Nutrition(grain, fruit, protein, other, total);
		
		int expectedProtein = (int)Math.ceil((protein/(double)100) * total);
		int actualProtein = myNutrition.getProtein();

	    // Assert that getProtein() returns the correct value
		assertEquals("getProtein() returned the incorrect value.", expectedProtein, actualProtein); 
    }
	
	// Test getOther()
    @Test
    public void testGetOther() {
		int grain = 16, fruit = 28, protein = 26, other = 30, total = 2500;
		Nutrition myNutrition = new Nutrition(grain, fruit, protein, other, total);
		
		int expectedOther = (int)Math.ceil((other/(double)100) * total);
		int actualOther = myNutrition.getOther();

	    // Assert that getOther() returns the correct value
		assertEquals("getOther() returned the incorrect value.", expectedOther, actualOther); 
    }
	
	// Test getCalories()
    @Test
    public void testGetCalories() {
		int grain = 16, fruit = 28, protein = 26, other = 30, total = 2500;
		Nutrition myNutrition = new Nutrition(grain, fruit, protein, other, total);
		
		int expectedCalories = total;
		int actualCalories = myNutrition.getCalories();

	    // Assert that getCalories() returns the correct value
		assertEquals("getCalories() returned the incorrect value.", expectedCalories, actualCalories); 
    }
}