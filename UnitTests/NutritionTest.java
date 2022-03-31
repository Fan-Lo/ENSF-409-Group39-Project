package edu.ucalgary.ensf409;

import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.*;

public class NutritionTest {

    public int myGrain = 0;
    public int myVegFru = 0;
    public int myProtien = 0;
    public int myCalories = 0;
    public int myOther = 0;
    

    public Nutrition myNut = new Nutrition(myGrain, myVegFru, 
        myProtien, myOther, myCalories);

    public Nutrition myWeekly = myNut.getWeeklyNeeds();


    // Test getGrain
    @Test
    public void testGetGrain() {
            
        int returnGrain = myNut.getWholeGrain();
           
        assertEquals(
            "Whole Grain was not return properly from Nutrition: ",
            myGrain, returnGrain);
    }

    // Test fruits/vegs
    @Test
    public void testGetFruitVeggies() {
            
        int returnVegFru = myNut.getFruitsVeggies();
           
        assertEquals(
            "Fruits/Veg was not return properly from Nutrition: ",
            myVegFru, returnVegFru);
    }

    // Test protien
    @Test
    public void testGetProtien() {
            
        int returnPro = myNut.getProtien();
           
        assertEquals(
            "Protien was not return properly from Nutrition: ",
            myProtien, returnPro);
    }

    // Test cals
    @Test
    public void testGetCals() {
                
        int returnCal= myNut.getCalories();
               
        assertEquals(
            "Calories was not return properly from Nutrition: ",
            myCalories, returnCal);
    }

    // Test other
    @Test
    public void testGetOther() {
                
        int returnOther= myNut.getProtien();
               
        assertEquals(
            "Other was not return properly from Nutrition: ",
            myOther, returnOther);
    }

    // Test getGrain
    @Test
    public void testGetGrainWeek() {
            
        int returnGrain = myWeekly.getWholeGrain();
           
        assertEquals(
            "Weekly Whole Grain was not return properly from Nutrition: ",
            myGrain*7, returnGrain);
    }

    // Test fruits/vegs
    @Test
    public void testGetFruitVeggiesWeek() {
            
        int returnVegFru = myWeekly.getFruitsVeggies();
           
        assertEquals(
            "Weekly Fruits/Veg was not return properly from Nutrition: ",
            myVegFru*7, returnVegFru);
    }

    // Test protien
    @Test
    public void testGetProtienWeek() {
            
        int returnPro = myWeekly.getProtien();
           
        assertEquals(
            "Weekly Protien was not return properly from Nutrition: ",
            myProtien*7, returnPro);
    }

    // Test cals
    @Test
    public void testGetCalsWeek() {
                
        int returnCal= myWeekly.getCalories();
               
        assertEquals(
            "Weekly Calories was not return properly from Nutrition: ",
            myCalories*7, returnCal);
    }

    // Test other
    @Test
    public void testGetOtherWeek() {
                
        int returnOther= myWeekly.getProtien();
               
        assertEquals(
            "Weekly Other was not return properly from Nutrition: ",
            myOther*7, returnOther);
    }
}