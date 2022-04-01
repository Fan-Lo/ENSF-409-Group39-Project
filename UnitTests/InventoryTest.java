/** 
* InventoryTest.java
* @version 1.0
* @since 1.0	
* First draft of class InventoryTest
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
        ArrayList<FoodItem> testInventory = new ArrayList<FoodItem>();

        // Asserts that the Inventory objects is not null
        assertNotNull("The Inventory object is null.", testInventory);
    }
}
