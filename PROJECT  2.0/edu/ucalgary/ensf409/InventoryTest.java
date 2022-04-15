/** 
* InventoryTest.java
* @author Jan Petallo <ahref="mailto:jan.petallo@ucalgary.ca">jan.petallo@ucalgary.ca</a>
* @version 1.3
* @since 1.0	
**/  

package edu.ucalgary.ensf409;

import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.*;
import java.util.*;

public class InventoryTest {

    //Test the constructor for Inventory
    @Test
    public void testInventoryConstructor(){
        Inventory testInventory = new Inventory();

        // Asserts that the Inventory object is not null
        assertNotNull("The Inventory object is null.", testInventory);
    }

    //Tests if method populate() contains the food items
    @Test
    public void testPopulate(){
        Inventory inventory = new Inventory();
        ArrayList<FoodItem> items = inventory.getFood();

        // Asserts populate() contians food items
        boolean empty = items.isEmpty();
        assertFalse("The database returned an empty inventory!", empty);
    }

    //Tests if removeItems() remove food items from inventory
    @Test
    public void testRemoveItem(){
        Inventory inventory = new Inventory();
        FoodItem itemToRemove = inventory.getFood().get(0);

        // Asserts itemToRemove is removed from the ArrayList
        boolean itemRemoved = inventory.removeItems(itemToRemove);

        assertTrue("The food item was not successfully removed from arrayList inventory", itemRemoved);
    }

    //Tests if restoreRemovedItems() restores all the previously removed items if the Order can't be processed due to shortage
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

    //Tests that getFood() returns an ArrayList
    @Test
    public void testGetFood(){
        Inventory testInventory = new Inventory();
        ArrayList<FoodItem> actualFood = testInventory.getFood();

        assertNotNull("getFood() does not contain any food", actualFood);
        
    }
}
