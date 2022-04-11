/** 
* Inventory.java
* @version 1.3
* @since 1.0	
**/   
package edu.ucalgary.ensf409;

import java.util.*;

public class Inventory {
    private ArrayList<FoodItem> inventory = null; // meant to reflect the the available items in the inventory (database)
    private AccessDatabase database;

    /**
     * Constructs a new ArrayList of all the available items in the inventory
     * Also connects to the databse
     * */ 
    public Inventory() {
        inventory = new ArrayList<>();
        database = new AccessDatabase("jdbc:mysql://localhost/competition","student","ensf");
        database.initializeConnection();
    }
    
    /** 
     * Creates a new FoodItem object and add it into the ArrayList inventory
     */
    public void addToInventory(int grain, int fv, int pro, int other, int cal, String name, int ID) {
        inventory.add(new FoodItem(grain, fv, pro, other, cal, name, ID));
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
    public void restoreRemovedItems(List<FoodItem> items){
        for(FoodItem item: items){
            inventory.add(item); // adds the item back into the ArrayList inventory
        }
    }

    /**
     * Removes all items (that were actually available to fulfill the order) from the database
     */
    public void removeFromDatabase(ArrayList<FoodItem> itemToRemove) {
        for(FoodItem item: itemToRemove){
            database.deleteFoodItem(item.getItemID());
        }
    }
}
