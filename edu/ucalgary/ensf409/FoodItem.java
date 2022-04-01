package edu.ucalgary.ensf409;

public class FoodItem {
    private String name;
    private final int ITEM_ID;
    private final Nutrition NUTRITION;

    public FoodItem(int wholeGrain, int fruitsVeggies, int protein, int other, int calories, String name, int itemID){
        this.name = name;
        this.ITEM_ID = itemID;
        this.NUTRITION = new Nutrition(wholeGrain, fruitsVeggies, protein, other, calories);
    }

    public int getItemID() {
        return this.ITEM_ID;
    }

    public Nutrition getNutrition() {
        return this.NUTRITION;
    }

    public String getName() {
        return this.name;
    }
    
}
