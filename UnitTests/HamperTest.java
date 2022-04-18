/** 
* HamperTest.java
* @version 2.2
* @since 1.0	
**/  

package edu.ucalgary.ensf409;

import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.*;
import java.util.*;

public class HamperTest {

    public HamperTest() { }

    // Test the no-argument constructor for Hamper
    @Test
    public void testHamperConstructor() {
		Hamper testHamper = new Hamper();

	    // Assert that Hamper constructor creates a valid Object
        assertNotNull("The Hamper object is null.", testHamper); 
    }
	
    // Test the copy constructor for Hamper
    @Test
    public void testHamperCopyConstructor() {
		boolean sameContents = true;
		Hamper toBeCopied = new Hamper();
		toBeCopied.addFood(new FoodItem(10, 20, 30, 40, 100, "Apple", 1));
		toBeCopied.addFood(new FoodItem(10, 20, 30, 40, 100, "Banana", 2));
		
		Hamper copy = new Hamper(toBeCopied);
		
		if(copy.getGrain() != toBeCopied.getGrain())
			sameContents = false;
		if(copy.getFruit() != toBeCopied.getFruit())
			sameContents = false;
		if(copy.getProtein() != toBeCopied.getProtein())
			sameContents = false;
		if(copy.getOther() != toBeCopied.getOther())
			sameContents = false;
		if(copy.getCalories() != toBeCopied.getCalories())
			sameContents = false;
		
		for(int i = 0; i < copy.getFood().size(); i++){
			if(!copy.getFood().get(i).getName().equals(toBeCopied.getFood().get(i).getName()))
				sameContents = false;
		}

	    // Assert that the fields of the copy hamper match the fields of the copied hamper
        assertEquals("The two hampers have different contents.", true, sameContents); 
    }
	
	// Test addFood()
    @Test
    public void testAddFood() {
		boolean addFoodWorked;
		boolean foodsHaveRightIDs = true;
		Hamper testHamper = new Hamper();
		testHamper.addFood(new FoodItem(10, 20, 30, 40, 100, "Apple", 1));
		testHamper.addFood(new FoodItem(10, 20, 30, 40, 100, "Banana", 2));
		
		int numberOfItems = testHamper.getFood().size();
		
		if(testHamper.getFood().get(0).getItemID() != 1)
			foodsHaveRightIDs = false;
		if(testHamper.getFood().get(1).getItemID() != 2)
			foodsHaveRightIDs = false;
		
		addFoodWorked = (numberOfItems == 2) && foodsHaveRightIDs;

	    // Assert that the FoodItems were added to the hamper's ArrayList successfully
        assertEquals("addFood() did not add FoodItems correctly.", true, addFoodWorked); 
    }
	
	// Test removeFood()
    @Test
    public void testRemoveFood() {
		Hamper testHamper = new Hamper();
		testHamper.addFood(new FoodItem(10, 20, 30, 40, 100, "Apple", 1));
		testHamper.addFood(new FoodItem(10, 20, 30, 40, 100, "Banana", 2));
		
		testHamper.removeFood(1);
		int expectedSize = 1;
		int actualSize = testHamper.getFood().size();

	    // Assert that removeFood() properly removed the FoodItem from the ArrayList
        assertEquals("removeFood() did not remove FoodItem correctly.", expectedSize, actualSize); 
    }
	
	// Test removeFood() subtracts removed food's grain calories 
    @Test
    public void testRemoveFoodGrain() {
		Hamper testHamper = new Hamper();
		testHamper.addFood(new FoodItem(10, 20, 30, 40, 100, "Apple", 1));
		testHamper.addFood(new FoodItem(10, 20, 30, 40, 100, "Banana", 2));
		
		int totalGrain = testHamper.getGrain();
		int expectedGrain = totalGrain - testHamper.getFood().get(1).getWholeGrain();
		
		testHamper.removeFood(1);
		int actualGrain = testHamper.getGrain();

	    // Assert that removeFood() properly subtracts the removed food's grain from the hamper's total grain
        assertEquals("removeFood() did not subtract grain properly.", expectedGrain, actualGrain); 
    }
	
	// Test removeFood() subtracts removed food's fruit calories 
    @Test
    public void testRemoveFoodFruit() {
		Hamper testHamper = new Hamper();
		testHamper.addFood(new FoodItem(10, 20, 30, 40, 100, "Apple", 1));
		testHamper.addFood(new FoodItem(10, 20, 30, 40, 100, "Banana", 2));
		
		int totalFruit = testHamper.getFruit();
		int expectedFruit = totalFruit - testHamper.getFood().get(1).getFruitsVeggies();
		
		testHamper.removeFood(1);
		int actualFruit = testHamper.getFruit();

	    // Assert that removeFood() properly subtracts the removed food's fruit from the hamper's total fruit
        assertEquals("removeFood() did not subtract fruit properly.", expectedFruit, actualFruit); 
    }
	
	// Test removeFood() subtracts removed food's protein calories 
    @Test
    public void testRemoveFoodProtein() {
		Hamper testHamper = new Hamper();
		testHamper.addFood(new FoodItem(10, 20, 30, 40, 100, "Apple", 1));
		testHamper.addFood(new FoodItem(10, 20, 30, 40, 100, "Banana", 2));
		
		int totalProtein = testHamper.getProtein();
		int expectedProtein = totalProtein - testHamper.getFood().get(1).getProtein();
		
		testHamper.removeFood(1);
		int actualProtein = testHamper.getProtein();

	    // Assert that removeFood() properly subtracts the removed food's protein from the hamper's total protein
        assertEquals("removeFood() did not subtract protein properly.", expectedProtein, actualProtein); 
    }
	
	// Test removeFood() subtracts removed food's other calories 
    @Test
    public void testRemoveFoodOther() {
		Hamper testHamper = new Hamper();
		testHamper.addFood(new FoodItem(10, 20, 30, 40, 100, "Apple", 1));
		testHamper.addFood(new FoodItem(10, 20, 30, 40, 100, "Banana", 2));
		
		int totalOther = testHamper.getOther();
		int expectedOther = totalOther - testHamper.getFood().get(1).getOther();
		
		testHamper.removeFood(1);
		int actualOther = testHamper.getOther();

	    // Assert that removeFood() properly subtracts the removed food's other from the hamper's total other
        assertEquals("removeFood() did not subtract other properly.", expectedOther, actualOther); 
    }
	
	// Test removeFood() subtracts removed food's total calories 
    @Test
    public void testRemoveFoodCalories() {
		Hamper testHamper = new Hamper();
		testHamper.addFood(new FoodItem(10, 20, 30, 40, 100, "Apple", 1));
		testHamper.addFood(new FoodItem(10, 20, 30, 40, 100, "Banana", 2));
		
		int totalCalories = testHamper.getCalories();
		int expectedCalories = totalCalories - testHamper.getFood().get(1).getCalories();
		
		testHamper.removeFood(1);
		int actualCalories = testHamper.getCalories();

	    // Assert that removeFood() properly subtracts the removed food's calories from the hamper's total calories
        assertEquals("removeFood() did not subtract calories properly.", expectedCalories, actualCalories); 
    }
	
	// Test getFood()
    @Test
    public void testGetFood() {
		boolean correctList = true;
		Hamper testHamper = new Hamper();
		testHamper.addFood(new FoodItem(10, 20, 30, 40, 100, "Apple", 1));
		testHamper.addFood(new FoodItem(10, 20, 30, 40, 100, "Banana", 2));
		
		ArrayList<FoodItem> testList = testHamper.getFood();
		
		int[] expectedItemIDs = {1, 2};
		String[] expectedNames = {"Apple", "Banana"};
		
		for(int i = 0; i < testList.size(); i++){
			if(testList.get(i).getItemID() != expectedItemIDs[i])
				correctList = false;
			if(!testList.get(i).getName().equals(expectedNames[i]))
				correctList = false;
		}

	    // Assert that getFood() returned the correct ArrayList of FoodItems
        assertEquals("getFood() did not return the expected ArrayList", true, correctList); 
    }
	
	// Test getGrain()
    @Test
    public void testGetGrain() {
		Hamper testHamper = new Hamper();
		testHamper.addFood(new FoodItem(10, 20, 30, 40, 100, "Apple", 1));
		testHamper.addFood(new FoodItem(10, 20, 30, 40, 100, "Banana", 2));
		
		int expectedGrain = testHamper.getFood().get(0).getWholeGrain()
		+ testHamper.getFood().get(1).getWholeGrain();
		
		int actualGrain = testHamper.getGrain();

	    // Assert that getGrain() returns the correct value
        assertEquals("getGrain() did not return the correct value.", expectedGrain, actualGrain); 
    }
	
	// Test getFruit()
    @Test
    public void testGetFruit() {
		Hamper testHamper = new Hamper();
		testHamper.addFood(new FoodItem(10, 20, 30, 40, 100, "Apple", 1));
		testHamper.addFood(new FoodItem(10, 20, 30, 40, 100, "Banana", 2));
		
		int expectedFruit = testHamper.getFood().get(0).getFruitsVeggies()
		+ testHamper.getFood().get(1).getFruitsVeggies();
		
		int actualFruit = testHamper.getFruit();

	    // Assert that getFruit() returns the correct value
        assertEquals("getFruit() did not return the correct value.", expectedFruit, actualFruit); 
    }
	
	// Test getProtein()
    @Test
    public void testGetProtein() {
		Hamper testHamper = new Hamper();
		testHamper.addFood(new FoodItem(10, 20, 30, 40, 100, "Apple", 1));
		testHamper.addFood(new FoodItem(10, 20, 30, 40, 100, "Banana", 2));
		
		int expectedProtein = testHamper.getFood().get(0).getProtein()
		+ testHamper.getFood().get(1).getProtein();
		
		int actualProtein = testHamper.getProtein();

	    // Assert that getProtein() returns the correct value
        assertEquals("getProtein() did not return the correct value.", expectedProtein, actualProtein); 
    }
	
	// Test getOther()
    @Test
    public void testGetOther() {
		Hamper testHamper = new Hamper();
		testHamper.addFood(new FoodItem(10, 20, 30, 40, 100, "Apple", 1));
		testHamper.addFood(new FoodItem(10, 20, 30, 40, 100, "Banana", 2));
		
		int expectedOther = testHamper.getFood().get(0).getOther()
		+ testHamper.getFood().get(1).getOther();
		
		int actualOther = testHamper.getOther();

	    // Assert that getOther() returns the correct value
        assertEquals("getOther() did not return the correct value.", expectedOther, actualOther); 
    }
	
	// Test getCalories()
    @Test
    public void testGetCalories() {
		Hamper testHamper = new Hamper();
		testHamper.addFood(new FoodItem(10, 20, 30, 40, 100, "Apple", 1));
		testHamper.addFood(new FoodItem(10, 20, 30, 40, 100, "Banana", 2));
		
		int expectedCalories = testHamper.getFood().get(0).getCalories()
		+ testHamper.getFood().get(1).getCalories();
		
		int actualCalories = testHamper.getCalories();

	    // Assert that getCalories() returns the correct value
        assertEquals("getCalories() did not return the correct value.", expectedCalories, actualCalories); 
    }
	
	// Test displayHamper()
    @Test
    public void testDisplayHamper() {
		Hamper testHamper = new Hamper();
		testHamper.addFood(new FoodItem(10, 20, 30, 40, 100, "Apple", 1));
		testHamper.addFood(new FoodItem(10, 20, 30, 40, 100, "Banana", 2));
		
		String expectedString = "1\tApple\n2\tBanana\n";
		String actualString = testHamper.displayHamper();
		
		boolean correctString = expectedString.equals(actualString);

	    // Assert that displayHamper() returns the correctly formatted String
        assertEquals("displayHamper() did not return the correct String.", true, correctString); 
    }
	
	// Test setCalories()
    @Test
    public void testSetCalories() {
		Hamper testHamper = new Hamper();
		testHamper.addFood(new FoodItem(10, 20, 30, 40, 100, "Apple", 1));
		testHamper.addFood(new FoodItem(10, 20, 30, 40, 100, "Banana", 2));
		
		int expectedCalories = 0;
		testHamper.setCalories(0);
		int actualCalories = testHamper.getCalories();

	    // Assert that setCalories() did its job correctly
        assertEquals("setCalories() did not set the correcr value.", expectedCalories, actualCalories); 
    }
	
	// Test calculateWaste()
    @Test
    public void testCalculateWaste() {
		Hamper testHamper = new Hamper();
		testHamper.addFood(new FoodItem(10, 20, 30, 40, 100, "Apple", 1));
		testHamper.addFood(new FoodItem(10, 20, 30, 40, 100, "Banana", 2));
		int idealCals = 150;
		
		int expectedWaste = testHamper.getCalories() - idealCals;
		int actualWaste = testHamper.calculateWaste(idealCals);

	    // Assert that calculateWaste() returns the correct value
        assertEquals("calculateWaste() did not return the correcr value.", expectedWaste, actualWaste); 
    }
    
}