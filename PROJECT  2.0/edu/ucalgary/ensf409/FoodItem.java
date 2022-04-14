/** 
* FoodItem.java
* @author Jan Petallo <ahref="mailto:jan.petallo@ucalgary.ca">jan.petallo@ucalgary.ca</a>
* @version 1.2
* @since 1.0	
**/  

package edu.ucalgary.ensf409;


public class FoodItem {
    private String name; // name of the food item
    private final int ITEM_ID; // ID of the food item
    private final Nutrition NUTRITION; //Nutrition content of the food item

    /**
     * Constructs a FoodItem object
     */
    public FoodItem(int wholeGrain, int fruitsVeggies, int protein, int other, int calories, String name, int itemID){
        this.name = name;
        this.ITEM_ID = itemID;
        this.NUTRITION = new Nutrition(wholeGrain, fruitsVeggies, protein, other, calories);
    }

    /**
     *Getter of ITEM_ID
     */
    public int getItemID() {
        return this.ITEM_ID;
    }

    /**
     * Getter of Nutrition object
     */
    public Nutrition getNutrition() {
        return this.NUTRITION;
    }

    /**
     * Getter of the name of the food item
     */
    public String getName() {
        return this.name;
    }
    
}
