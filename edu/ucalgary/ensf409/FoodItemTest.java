package edu.ucalgary.ensf409;

import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.*;


public class FoodItemTest {
    // Test data
    public FoodItem item = new FoodItem(0, 80, 10, 10, 120, "Tomato Sauce, jar", 1234);

    @Test 
    public void testFoodItemConstructor(){
        FoodItem testFoodITem = new FoodItem(0, 80, 10, 10, 120, "Tomato Sauce, jar", 1234);

        assertNull("The FoodITem object is null.", testFoodITem);
    }

    @Test
    public void testgetName(){
        String expectedName = "Tomato Sauce, jar";
        String actualName = item.getName();

        assertEquals("getName() returned the wrong value.", expectedName, actualName);

    }

    @Test
    public void testGetNutrition(){
        Nutrition expectedNutrition = new Nutrition(0, 80, 10, 10, 120);
        Nutrition actualNutrition = item.getNutrition();

        assertEquals("getNutrition() returned the wrong value.", expectedNutrition, actualNutrition);

    }

    @Test
    public void testGetItemID(){
        int expectedItemID = 1234;
        int actualItemID = item.getItemID();

        assertEquals("getItemID() returned the wrong value.", expectedItemID, actualItemID);
    }
}
