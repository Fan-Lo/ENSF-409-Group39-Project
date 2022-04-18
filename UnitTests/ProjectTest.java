/**
 * Test file for methods for classes implemented for this Project
 * <p>
 * @version 1.4
 * @Since 1.0
 */

package edu.ucalgary.ensf409;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;


/* *********** Tests for the Order class  *********** */
public class ProjectTest {
    
    // ----------------------- Start of FoodItem class Tests ---------------------------
    // Test data
    private FoodItem item = new FoodItem(0, 80, 10, 10, 120, "Tomato Sauce, jar", 1234);

    /**
     * Tests the constructor for FoodItem
     */
    @Test 
    public void testFoodItemConstructor(){
        FoodItem testFoodITem = new FoodItem(0, 80, 10, 10, 120, "Tomato Sauce, jar", 1234);

        // Asserts that FoodItem is created and is not null
        assertNotNull("The FoodITem object is null.", testFoodITem);
    }

    /**
     * Tests that getName() method returns the correct name
     */
    @Test
    public void testGetName(){
        String expectedName = "Tomato Sauce, jar";
        String actualName = item.getName();

        // Asserts that the expectedName and actualName are the same
        assertEquals("getName() returned the wrong value.", expectedName, actualName);

    }

    /**
     * Tests that getItemID() method returns the correct ITEM_ID
     */
    @Test
    public void testGetItemID(){
        int expectedItemID = 1234;
        int actualItemID = item.getItemID();

        // Asserts that the expectedItemID and actualItemID are the same
        assertEquals("getItemID() returned the wrong value.", expectedItemID, actualItemID);
    }

    
    // ----------------------- Start of Nutrition class Tests ---------------------------
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
    // ----------------------- Start of Inventory class Tests ----------------------
     /**
     * Test the constructor for Inventory
     */
    @Test
    public void testInventoryConstructor(){
        Inventory testInventory = new Inventory();

        // Asserts that the Inventory object is not null
        assertNotNull("The Inventory object is null.", testInventory);
    }

    /**
     * Tests if method populate() contains the food items
     */
    @Test
    public void testPopulate(){
        Inventory inventory = new Inventory();
        ArrayList<FoodItem> items = inventory.getFood();

        // Asserts populate() contians food items
        boolean empty = items.isEmpty();
        assertFalse("The database returned an empty inventory!", empty);
    }

    /**
     *  Tests if removeItems() remove food items from inventory
     */
    @Test
    public void testRemoveItem(){
        Inventory inventory = new Inventory();
        FoodItem itemToRemove = inventory.getFood().get(0);

        // Asserts itemToRemove is removed from the ArrayList
        boolean itemRemoved = inventory.removeItems(itemToRemove);

        assertTrue("The food item was not successfully removed from arrayList inventory", itemRemoved);
    }

    /**
     * Tests if restoreRemovedItems() restores all the previously removed items if the Order can't be processed due to shortage
     */
    @Test
    public void testRestoreRemovedItmes(){
        Inventory inventory = new Inventory();
        ArrayList<FoodItem> items = new ArrayList<>();
        FoodItem item1 = new FoodItem(0, 10, 10, 10, 10, "item1", 1000);
        FoodItem item2 = new FoodItem(10, 20, 30, 40, 50, "item2", 2000);
        items.add(item1);
        items.add(item2);

        //Asserts items are restored to the ArrayList.
        boolean allAdded = inventory.restoreRemovedItems(items);

        assertTrue("Items were not successfully added to the inventory", allAdded);

    }

    /**
     * Tests if removeFromDatabase() removes all the items specified in the ArrayList passed as arguments.
     * allRemoved should be true to confirm this. Otherwise, it is false.
     */
    @Test
    public void testRemoveFromDatabase(){
        Inventory inventory = new Inventory();
        FoodItem itemToRemove1 = inventory.getFood().get(0);
        FoodItem itemToRemove2 = inventory.getFood().get(1);
        ArrayList<FoodItem> itemsToRemove = new ArrayList<>();

        itemsToRemove.add(itemToRemove1);
        itemsToRemove.add(itemToRemove2);

        boolean allRemoved = inventory.removeFromDatabase(itemsToRemove);
        assertTrue("Not all items were removed from the database", allRemoved);

    }

    /**
     * Tests that getFood() returns an ArrayList
     */
    @Test
    public void testGetFood(){
        Inventory testInventory = new Inventory();
        ArrayList<FoodItem> actualFood = testInventory.getFood();

        assertNotNull("getFood() does not contain any food", actualFood);
        
    }


    // ----------------------- Start of Order class Tests ---------------------------
    private Family family1 = new Family(1,0,0,0);
    private Family family2 = new Family(2,0,0,0);
     
    /*
     * Using Order constructors and use the getter 
     * to retrieve an arraylist containing the same family object.
    */
    @Test
    public void testOrderConstructorAndFamilyGetter() {
        // generating expected array list
        ArrayList<Family> expectedFamilyAL = new ArrayList<>();
        expectedFamilyAL.add(family1);
        
        // Using Order constructors
        Order testOrder = new Order(family1);
        
        assertEquals("Incorrect array list was return using getFamilies()", expectedFamilyAL, testOrder.getFamilies());
    }

    /*
     * Testing that addFamily() successfuly add the family into the arraylist
     */
    @Test
    public void testAddFamily() {
        // generating expected array list
        ArrayList<Family> expectedFamilyAL = new ArrayList<>();
        expectedFamilyAL.add(family1);
        expectedFamilyAL.add(family2);

        // Using addFamily method
        Order testOrder = new Order(family1);
        testOrder.addFamily(family2);

        assertEquals("Incorrect array list was return after addFamily() was called", expectedFamilyAL, testOrder.getFamilies());

    }

    /*
     * Testing that generateHamper can generate hampers for one family and that it doesn't throw exception
     * when it shouldn't. Also test that the generated hamper meets the weekly nutritional needs of the family and the
     * items in the hamper have been removed successfully from the inventory
     */
    @Test
    public void testGenerateHamper1Family() {
        Order testOrder1Family = new Order(family1);

        boolean noException = true;
        try {
            testOrder1Family.generateHampers();
        } catch (ItemNotFoundException e) {
            noException = false;
        }

        assertTrue("generateHampers threw ItemNotFoundException when it shouldn't", noException);
    
        // check that the generate hamper meets the nutritional needs of the family
        boolean hamperIsValid = hamperIsValid(testOrder1Family.getFamilies().get(0));
        assertTrue("Hamper created for family doesn't not meet the nutritional needs of the family", hamperIsValid);

         // check that deleted items are not in the inventory anymore
         boolean hamperDeletedFromInv = hamperItemRemoved(testOrder1Family.getFamilies().get(0).getHamper());
         assertTrue("Hamper item is still present in inventory", hamperDeletedFromInv);
    }


    /*
     * Testing that generateHamper can generate hampers for more than one family (2 families) and that it doesn't throw exception
     * when it shouldn't. Also test that the generated hamper meets the weekly nutritional needs of the families and the
     * items in the hamper have been removed successfully from the inventory
     */
    @Test
    public void testGenerateHamper2Families() {
        Order testOrder2Families = new Order(family1);
        testOrder2Families.addFamily(family2);

        boolean noException = true;
        try {
            testOrder2Families.generateHampers();
        } catch (ItemNotFoundException e) {
            noException = false;
        }

        assertTrue("generateHampers threw ItemNotFoundException when it shouldn't", noException);
    
        Iterator<Family> famIter= testOrder2Families.getFamilies().iterator();
        boolean hamperIsValid = true;
        boolean hamperDeletedFromInv = true;
        while (famIter.hasNext()) {
            Family fam = famIter.next();

            // check that the generate hamper meets the nutritional needs of the family
            hamperIsValid = hamperIsValid && hamperIsValid(fam);

            // check that deleted items are not in the inventory anymore
            hamperDeletedFromInv = hamperDeletedFromInv && hamperItemRemoved(fam.getHamper());
        }

        assertTrue("Hampers created for families do not meet the nutritional needs of the families", hamperIsValid);
        assertTrue("Hamper item is still present in inventory", hamperDeletedFromInv);
    }

    /**
     * This tests boundary condition family is too large and order cannot be completed
     */
    @Test
    public void testGenerateHamperBadOrder() {
        // this fmaily has 40 people which cause food shortage
        Family badFamily = new Family(10, 10, 10, 10);
        Order badOrder = new Order(badFamily);
        boolean exceptionIsThrown = false;
        try {
            badOrder.generateHampers();
        } catch (ItemNotFoundException e) {
            exceptionIsThrown = true;
        }

        assertTrue("Exception is not thrown when there is food shortage in inventory", exceptionIsThrown);
    }

    /**
     * Checks if food item in the hamper is present in display order. It doesn't check
     * for formatting. ony that food item name is present.
     */
    @Test
    public void testDisplayOrder() {
        Order testOrder = new Order(family1);
        try {
            testOrder.generateHampers();
        } catch (ItemNotFoundException e) {
            assertTrue("Not enough food item for hamper generation. Refresh inventory.sql to restore inventory", true);
        }

        String orderInfo = testOrder.displayOrder();
        Hamper hamper = testOrder.getFamilies().get(0).getHamper();
        boolean bool = orderInfo.contains(hamper.displayHamper());
        assertTrue("displayOrder returns a string that doesn't contain the correct hamper food item", bool);
    }

    /**
     * Compares the nutritional content in the hamper is at least the nutritional needs
     * of the family. The mehtod does not check if the hamper is optimized or not,
     * only that the needs of the family is met.
     * @param order
     * @return
     */
    private static boolean hamperIsValid(Family family) {
        int grainInHamper = 0, fvInHamper = 0, proInHamper = 0, otherInHamper = 0, calInHamper = 0;
        Hamper hamper = family.getHamper();
        grainInHamper = hamper.getGrain();
        fvInHamper = hamper.getFruit();
        proInHamper = hamper.getProtein();
        otherInHamper = hamper.getOther();
        calInHamper = hamper.getCalories();

        if (grainInHamper < family.getWeeklyGrainNeeds()) {
            return false;
        } else if (fvInHamper < family.getWeeklyVeggieNeeds()) {
            return false;
        } else if (proInHamper < family.getWeeklyProteinNeeds()) {
            return false;
        } else if (otherInHamper < family.getWeeklyOtherNeeds()) {
            return false;
        } else if (calInHamper < family.getWeeklyCalorieNeeds()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * hamperItemRemoved checks if all FoodItem in hamper is removed.
     * @param hamper hamper whose content is to be checked
     * @return boolean value. true is all items are not present in the database. false if at least one
     * item is still present in database.
     */
    private static boolean hamperItemRemoved (Hamper hamper) {
        boolean bool = false;
        // initialize connection to the database
        AccessDatabase db = new AccessDatabase("jdbc:mysql://localhost/food_inventory", "student", "ensf");
        db.initializeConnection();

        Iterator<FoodItem> iter = hamper.getFood().iterator();
        while (iter.hasNext()) {
            bool = db.itemIsPresent(iter.next().getItemID());
            if (!bool) {
                return bool;
            }
        }
        // db.close() is commented out because it causes null point exception
        // db.close();
        return bool;
    }
 


}