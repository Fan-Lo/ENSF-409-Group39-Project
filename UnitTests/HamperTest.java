package edu.ucalgary.ensf409;

import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.*;

public class HamperTest {
    
    //TODO: setting these will probably be more work
    public int myGrain = 10;
    public int myVegFru = 20;
    public int myProtien = 30;
    public int myCalories = 40;
    public int myOther = 50;

    public Nutrition myNutReq = new Nutrition(myGrain, myVegFru, 
        myProtien, myOther, myCalories);

    public Nutrition myExpectedNut = new Nutrition(myGrain*7, myVegFru*7, 
        myProtien*7, myOther*7, myCalories*7);

    public Inventory myInv = new Nutrition();

    public String myHamperDisp = "";

    public int myWaste = 68;

    private FoodItem myFood = new FoodItem(0, 80, 10, 10, 120, "Tomato Sauce, jar", 1234);


    public HamperTest() { }


    // Test the constructor for Hamper
    @Test
    public void testHamperConstructorNutrition() {

        Hamper myHamper = new Hammper(myNutReq, myInv);
        assertNotNull("The Hamper object is null.", myHamper);
    }


    // Test Nutrition calculation 
    @Test
    public void testCalculateNutrition() {
        
        Hamper myHamper = new Hammper(myFamily);
        Nutrition returnNut = myHamper.calculateNutrition();
        
        assertEquals(
            "Nutrition was not got calculated properly Hamper: ",
            myExpectedNutgetProtien(), returnNutgetProtien());
    }


    // Test waste calculation  
    @Test
    public void testCalculateWaste() {
        
        Hamper myHamper = new Hammper(myFamily);
        int returnWaste = myHamper.calculateWaste();
       
        assertEquals(
           "Waste was not calculated properly in Hamper: ",
           myWaste, returnWaste);
    }


    // Test food setter and getter 
    @Test
    public void testFoodSetandGet() {

        Hamper myHamper = new Hammper(myNutReq, myInv);
        myHamper.addFood(myFood);
        ArrayList returnFood = myHamper.getFood();

        assertEquals(
            "foodItem was not set/get correctly in Hamper: ",
            myFood.getName(), returnFood.get(0).getName());
    }

    // Test Display
   @Test
   public void testDsiplayHamper() {
       Hamper myHamper = new Hammper(myFamily);
       String returnDisp = myHamper.displayHamper();
       
        assertEquals(
           "Dsiplay was not generated properly in Hamper: ",
           myHamperDisp, returnDisp);
    }
    
}