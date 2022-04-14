/** 
* FoodItemTest.java
* @author Jan Petallo <ahref="mailto:jan.petallo@ucalgary.ca">jan.petallo@ucalgary.ca</a>
* @version 1.2
* @since 1.0	
**/  

package edu.ucalgary.ensf409;

import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.*;


public class FoodItemTest {
    // Test data
    private FoodItem item = new FoodItem(0, 80, 10, 10, 120, "Tomato Sauce, jar", 1234);

    // Tests the constructor for FoodItem
    @Test 
    public void testFoodItemConstructor(){
        FoodItem testFoodITem = new FoodItem(0, 80, 10, 10, 120, "Tomato Sauce, jar", 1234);

        // Asserts that FoodItem is created and is not null
        assertNotNull("The FoodITem object is null.", testFoodITem);
    }

    @Test
    public void testFoodItemCopyConstructor(){
        
    }

    // Tests that getName() method returns the correct name
    @Test
    public void testGetName(){
        String expectedName = "Tomato Sauce, jar";
        String actualName = item.getName();

        // Asserts that the expectedName and actualName are the same
        assertEquals("getName() returned the wrong value.", expectedName, actualName);

    }

    // Tests that getNutrition() method returns the correct Nutrition objects
    @Test
    public void testGetNutrition(){
        Nutrition testNutrition = new Nutrition(0, 80, 10, 10, 120);
        Nutrition actualNutrition = item.getNutrition();

        int expectedGrain = (int)Math.ceil((0/(double)100) * 120);
        int actualGrain = actualNutrition.getWholeGrain();

        int expectedFV = (int)Math.ceil((80/(double)100) * 120);
        int actualFV = actualNutrition.getFruitsVeggies();

        int expectedProtein = (int)Math.ceil((10/(double)100) * 120);
        int actualProtein = actualNutrition.getProtein();

        int expectedOther = (int)Math.ceil((10/(double)100) * 120);
        int actualOther = actualNutrition.getOther();

        int expectedCal = 120;
        int actualCal = actualNutrition.getCalories();


        // Asserts that the expectedNutrition and actualNutrition are the same by checking the nutritional values
        assertEquals("getNutrition() returned the wrong value.", expectedGrain, actualGrain);
        assertEquals("getNutrition() returned the wrong value.", expectedFV, actualFV);
        assertEquals("getNutrition() returned the wrong value.", expectedProtein, actualProtein);
        assertEquals("getNutrition() returned the wrong value.", expectedOther, actualOther);
        assertEquals("getNutrition() returned the wrong value.", expectedCal, actualCal);

    }

    // Tests that getItemID() method returns the correct ITEM_ID
    @Test
    public void testGetItemID(){
        int expectedItemID = 1234;
        int actualItemID = item.getItemID();

        // Asserts that the expectedItemID and actualItemID are the same
        assertEquals("getItemID() returned the wrong value.", expectedItemID, actualItemID);
    }
}
