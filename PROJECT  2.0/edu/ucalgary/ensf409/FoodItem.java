/** 
* FoodItem.java
* @author Jan Petallo <ahref="mailto:jan.petallo@ucalgary.ca">jan.petallo@ucalgary.ca</a>
* @version 2.4
* @since 1.3
* Updated to have inheritance	
*
* The FoodItem class is used to hold information about individual food items
* in an inventory.
* It is a subclass of class Nutrition, so during construction
* the arguments associated with calories are passed to the superclass constructor.
* FoodItem has member variables name which is the name of the item, and ITEM_ID
* which is the ID of the item.
*
**/  

package edu.ucalgary.ensf409;

public class FoodItem extends Nutrition{
    private String name; // name of the food item
    private final int ITEM_ID; // ID of the food item

    /**
     * Constructs a FoodItem object
	 * @param wholeGrain Percentage of calories dedicated to grain of the total
	 * @param fruitsVeggies Percentage of calories dedicated to fruits/veggies of the total
	 * @param protein Percentage of calories dedicated to protein of the total
	 * @param other Percentage of calories dedicated to other of the total
	 * @param calories Total number of calories in this FoodItem
	 * @param name The name of this FoodItem
	 * @param itemID The ID of this FoodItem
     */
    public FoodItem(int wholeGrain, int fruitsVeggies, int protein, int other, int calories, String name, int itemID){
		super(wholeGrain, fruitsVeggies, protein, other, calories);
        this.name = name;
        this.ITEM_ID = itemID;
    }
    
    /**
     * Getter of ITEM_ID
	 * @return the item ID associated with this item
     */
    public int getItemID() {
        return this.ITEM_ID;
    }

    /**
     * Getter of name
	 * @return the name of this item
     */
    public String getName() {
        return this.name;
    }
    
}
