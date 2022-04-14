/** 
* FoodItem.java
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
     * Copy Constructor of a FoodItem object
     */
	public FoodItem(FoodItem item){
		this.name = item.getName();
		this.ITEM_ID = item.getItemID();
		this.NUTRITION = new Nutrition(item.getNutrition().getWholeGrain(), item.getNutrition().getFruitsVeggies(), item.getNutrition().getProtein(), item.getNutrition().getOther(), item.getNutrition().getCalories());
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