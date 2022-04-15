/** 
* @author Jan Petallo <ahref="mailto:jan.petallo@ucalgary.ca">jan.petallo@ucalgary.ca</a>
* @version 1.5
* @since 1.0	
*
*/   

package edu.ucalgary.ensf409;

import java.util.*;

/**
 * Inventory class is the gateway to the database. The no-argument constructor
 * initializes a connection to the database food_inventory that contains the food_items
 * available. These items are fetched and assigned into the ArrayList inventory through
 * the method populate(). 
 */
public class Inventory {
    private static ArrayList<FoodItem> inventory = new ArrayList<FoodItem>(); // meant to reflect the the available items in the inventory (database)
    private AccessDatabase database;

    /**
     * Constructs a new ArrayList of all the available items in the inventory
     * Also connects to the databse
     * */ 
    public Inventory() {
        inventory = new ArrayList<>();
        database = new AccessDatabase("jdbc:mysql://localhost/food_inventory","student","ensf");
        database.initializeConnection();
        populate();;
        int totalCals = 0;
        for(FoodItem item: inventory){
            totalCals += item.getNutrition().getCalories();
        }
        System.out.println("Total Calories of all items in inventory = " + totalCals);
    }
    
    /** 
     * populates the ArrayList inventory with all items in available_food in database
     */
	public void populate(){
		Inventory.inventory = database.fetchItems();
	}

    /**
     * Removes item from the ArrayList inventory
     */
    public boolean removeItems(FoodItem item) {
        boolean isRemoved = inventory.remove(item); // true if an item is removed from the ArrayList. Otherwise, false.
        return isRemoved;
    }

    /**
     * Called when an attempt to remove an item from the inventory fails (item is not in the inventory anymore)
     * It adds back all the items already removed before the failure
     */
    public boolean restoreRemovedItems(List<FoodItem> items){
        boolean added = false;
        for(FoodItem item: items){
            added = inventory.add(item); // adds the item back into the ArrayList inventory
        }
        return added;
    }

    /**
     * Removes all items (that were actually available to fulfill the order) from the database
     */
    public boolean removeFromDatabase(ArrayList<FoodItem> itemToRemove) {
        boolean allRemovved = false; 
        for(FoodItem item: itemToRemove){
            allRemovved = database.deleteFoodItem(item.getItemID());
        }
        return allRemovved; // true if all items were removed. Otherwise, false!

    }
	
	 /**
     * Returns the ArrayList of food in the inventory
     */
    public ArrayList<FoodItem> getFood() {
        return Inventory.inventory;
    }
}
