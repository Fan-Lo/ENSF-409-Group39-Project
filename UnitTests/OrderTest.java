/**
 * Test file for methods in the Order class.
 * <p>
 * @version 1.2
 * @Since 1.0
 */

// package UnitTests;
package edu.ucalgary.ensf409;
import org.junit.*;

// import edu.ucalgary.ensf409.*;


import static org.junit.Assert.*;
import java.io.*;
import java.util.*;

/* *********** Tests for the Order class  *********** */
public class OrderTest {
    // Test data 

    private final Person testPerson1 = createPerson(0);
    private final Person testPerson2 = createPerson(1);
    private Family family = createFamily(1,1);
    private Inventory inv = new Inventory();

     
    /*
     * Using Order constructors and use the getter 
     * to retrieve an arraylist containing the same family object.
    */
    @Test
    public void testOrderConstructorAndFamilyGetter() {
        // generating expected array list
        ArrayList<Family> expectedFamilyAL = new ArrayList<>();
        expectedFamilyAL.add(family);
        
        // Using Order constructors
        Order testOrder = new Order(family, inv);
        
        assertEquals("Incorrect array list was return using getFamilies()", expectedFamilyAL, testOrder.getFamilies());
    }

    
    /*
     * Using Order constructor whose argument is a Family arraylist and use 
     * addFamily() to test is the addition of Family object is successful
    */
    @Test
    public void testOrderConstructorAndAddFamily() {
        
        // generating expected array list;
        Family familyToBeAdded = createFamily(1, 0);
        ArrayList<Family> expectedFamilyAL = new ArrayList<>();
        expectedFamilyAL.add(family);
        expectedFamilyAL.add(familyToBeAdded);

        // generaing test Order object
        ArrayList<Family> constructorAL = new ArrayList<>();
        constructorAL.add(family);
        Order testOrder = new Order(constructorAL,inv);
        testOrder.addFamily(familyToBeAdded);


        assertEquals("Incorrect arraylist was return", expectedFamilyAL, testOrder.getFamilies());
    }


    /*
     * RemoveFromInventory should successfuly remove items present in the inventory
     * and return an empty string. RemoveFromInventory should return a failure
     * message when the item to be removed is not present in inventory.
    */
    @Test
    public void testRemoveFromInventory() {
        
        Inventory testInv = new Inventory();
        Family testFamily = new Family();
        inv.addToInventory( 0, 80, 10, 10, 120, "Tomato Sauce", 1);
        Order testOrder = new Order(family, inv);
        FoodItem itemInInventory = new FoodItem(0, 80, 10, 10, 120, "Tomato Sauce", 1);
        FoodItem itemNotInInventory = new FoodItem(0, 100, 0, 0, 624, "Apple Dozen", 5);

        ArrayList<FoodItem> itemsPresent= new ArrayList<>();
        itemsPresent.add(itemInInventory);
        ArrayList<FoodItem> itemsNotPresent= new ArrayList<>();
        itemsPresent.add(itemNotInInventory);

        String expectedMessageSuccess = "";
        String expectedMessageFailure = itemNotInInventory.getName() + " not found in inventory.";

        assertNotEquals("Returned String for successful removal is incorrect", 
            expectedMessageSuccess, testOrder.removeFromInventory(itemsPresent));
        assertNotEquals("Returned String for unsuccessful removal is incorrect", 
            expectedMessageFailure, testOrder.removeFromInventory(itemsNotPresent));
    }
    
    
    /*
     * Testing displayOrder to be in correct format for a successful order.
    */
    @Test
    public void testDisplayOrder() {
        String expectedSuccessfulString = "Hamper 1: 1 Adult Female \n" + 
            "Hamper 1 Items: \n" + 
            "1\tTomato Sauce, Jar\n";

        
        Order testOrderSuccessful = new Order(family,inv);

        assertNotEquals("Displayed String for a successful order is incorrect", 
            expectedSuccessfulString, testOrderSuccessful.displayOrder());
    }

    // private method that creates simple Person objects for testing purposes only
    // index can only be 0 or 1
    private Person createPerson(int index) {
        int i = 1;
        Nutrition[] nutritions = new Nutrition[2];
        while (i < 2) {
            nutritions[i-1] = new Nutrition(i, i, i, i, i*100);
            i++;
        }
        Person pers = new Person(nutritions[index], index);
        return pers;
    }

    // private method that creates family objects
    private Family createFamily(int numOfPerson1, int numOfPerson2) {
        Family family = new Family();
        while (numOfPerson1 > 0) {
            family.addMember(testPerson1);
            numOfPerson1--;
        }
        while (numOfPerson2 > 0) {
            family.addMember(testPerson2);
            numOfPerson2--;
        }

        return family;
    }

    // adds a small selection of FoodItem into Inventory as a way to bypass using database
    private Inventory createInventory() {
        Inventory inv = new Inventory();
        FoodItem food1 = new FoodItem(wholeGrain, fruitsVeggies, protein, other, calories, name, itemID)
        return inv;
    }
}
