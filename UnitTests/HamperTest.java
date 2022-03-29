package edu.ucalgary.ensf409;

import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.*;

public class HamperTest {
    
    //TODO: setting these will probably be more work

    public Family myFamily = new Family();
    public FoodItems myFood = new FoodItems();
    public Nutrition myNutrition = new Nutrition();
    public String myHamperDisp = "";
    public int myWaste = 0;

    public HamperTest() { }


    // Test the constructor for Hamper
    @Test
    public void testHamperConstructor() {

        Hamper myHamper = new Hammper(myFamily);
        Family returnFam = myHamper.getFamily();

        assertEquals(
            "Hamper constructor returns incorrect family: ",
            myFamily, returnFam);
    }

    // Test food setter and getter 
    @Test
    public void testFoodSetandGet() {

        Hamper myHamper = new Hammper(myFamily);
        myHamper.addFood(myFood);
        ArrayList returnFood = myHamper.getFood();

        assertEquals(
            "foodItem was not set/get correctly in Hamper: ",
            myFood, returnFood.get(0));
    }

    // Test family setter and getter 
    @Test
    public void testFamilyGet() {
    
        Hamper myHamper = new Hammper(myFamily);
        ArrayList returnFam = myHamper.getFamily();
    
        assertEquals(
            "Family was not got correctly in Hamper: ",
            myFamily.getFamilyMembers(), returnFam);
    }

    // Test Nutrition calculation
    @Test
    public void testCalculateNutrition() {
        
        Hamper myHamper = new Hammper(myFamily);
        Nutrition returnNut = myHamper.calculateNutrition();
        
        assertEquals(
            "Nutrition was not got calculated properly Hamper: ",
            myNutrition, returnNut);
    }

   // Test Nutrition calculation
   @Test
   public void testDsiplayHamper() {
       Hamper myHamper = new Hammper(myFamily);
       String returnDisp = myHamper.displayHamper();
       
        assertEquals(
           "Dsiplay was not generated properly in Hamper: ",
           myHamperDisp, returnDisp);
    }

    // Test Nutrition calculation
    @Test
    public void testCalculateWaste() {
        
        Hamper myHamper = new Hammper(myFamily);
        int returnWaste = myHamper.calculateWaste();
       
        assertEquals(
           "Waste was not calculated properly in Hamper: ",
           myWaste, returnWaste);
    }
    
}