package UnitTests;
import org.junit.*;

import edu.ucalgary.ensf409.*;

import static org.junit.Assert.*;
import java.io.*;
import java.util.*;

/* *********** Tests for the Order class  *********** */
public class OrderTest {
    // Test data 
    private final Nutrition ADULT_MALE_NEEDS = new Nutrition(16, 28, 26, 30, 2500);
    private final Nutrition ADULT_FEMALE_NEEDS = new Nutrition(16, 28, 26, 30, 2500);
    private final Nutrition CHILDEN_UNDER_NEEDS = new Nutrition(21, 33, 31, 15, 2200);
    private final Nutrition CHILDEN_OVER_NEEDS = new Nutrition(21, 33, 31, 15, 1400);
    private final Person ADULT_MALE = new Person(ADULT_MALE_NEEDS, 1);
    private final Person ADULT_FEMALE = new Person(ADULT_FEMALE_NEEDS, 2);
    private final Person CHILD_UNDER = new Person(CHILDEN_UNDER_NEEDS, 3);
    private final Person CHILD_OVER = new Person(CHILDEN_OVER_NEEDS, 3);
    private Inventory inv = new Inventory();
    private  Family family= new Family();
     
    /*
     * Using Order constructor whose argument is a Family object and use the getter 
     * to retrieve an arraylist containing the same family object.
    */
    @Test
    public void testOrderConstructorAndFamilyGetter() {
        family.addFamilyMember(ADULT_FEMALE);
        ArrayList<Family> expectedFamilyAL = new ArrayList<>();
        expectedFamilyAL.add(family);

        Order testOrder = new Order(family,inv);
        
        assertEquals("Incorrect arraylist was return using getFamilies()", expectedFamilyAL, testOrder.getFamiles());
    }

    
    /*
     * Using Order constructor whose argument is a Family arraylist and use 
     * addFamily() to test is the addition of Family object is successful
    */
    @Test
    public void testOrderConstructorAndAddFamily() {
        
        Family family1 = new Family();
        family1.addFamilyMember(CHILD_OVER);
        ArrayList<Family> expectedFamilyAL = new ArrayList<>();
        expectedFamilyAL.add(family);
        expectedFamilyAL.add(family1);

        ArrayList<Family> constructorAL = new ArrayList<>();
        constructorAL.add(family);
        Order testOrder = new Order(constructorAL,inv);
        testOrder.addFamily(family1);


        assertEquals("Incorrect arraylist was return", expectedFamilyAL, testOrder.getFamiles());
    }


    /*
     * RemoveFromInventory should successfuly remove items present in the inventory
     * and return an empty string. RemoveFromInventory should return a failure
     * message when the item to be removed is not present in inventory.
    */
    @Test
    public void testRemoveFromInventory() {
        
        inv.addToInventory("Tomato Sauce", 0, 80, 10, 10, 120, 1);
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
}
