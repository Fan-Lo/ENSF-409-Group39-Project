package edu.ucalgary.ensf409;

public class FoodItem {
    private String name;
    private final int ITEM_ID;
    private final Nutrition NUTRITION;

    public FoodItem(String name, int ID, Nutrition nutrition) {
        this.name = name;
        this.ITEM_ID = ID;
        this.NUTRITION = nutrition;
    }
    
}
