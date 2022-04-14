/** 
* FoodItem.java
* @version 1.3
* @since 1.2	
* Updated to implement inheritance
*
**/  

package edu.ucalgary.ensf409;

public class FoodItem extends Nutrition{
    private String name; // name of the food item
    private final int ITEM_ID; // ID of the food item

    /**
     * Constructs a FoodItem object
     */
    public FoodItem(int grain, int fruitVeg, int protein, int other, int calories, String name, int itemID){
        super(grain, fruitVeg, protein, other, calories);
		this.name = name;
        this.ITEM_ID = itemID;
    }
	/**
     * Copy Constructor of a FoodItem object
     */
	public FoodItem(FoodItem item){
		super(item);
		this.name = item.getName();
		this.ITEM_ID = item.getItemID();
	}

    /**
     *Getter of ITEM_ID
     */
    public int getItemID() {
        return this.ITEM_ID;
    }

    /**
     * Getter of the name of the food item
     */
    public String getName() {
        return this.name;
    }
    
}
