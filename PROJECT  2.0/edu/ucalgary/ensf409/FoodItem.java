/** 
* FoodItem.java
* @author Jan Petallo <ahref="mailto:jan.petallo@ucalgary.ca">jan.petallo@ucalgary.ca</a>
* @version 1.4
* @since 1.3
* Updated to have inheritance	
**/  

package edu.ucalgary.ensf409;

public class FoodItem extends Nutrition{
    private String name; // name of the food item
    private final int ITEM_ID; // ID of the food item

    /**
     * Constructs a FoodItem object
     */
    public FoodItem(int wholeGrain, int fruitsVeggies, int protein, int other, int calories, String name, int itemID){
		super(wholeGrain, fruitsVeggies, protein, other, calories);
        this.name = name;
        this.ITEM_ID = itemID;
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
