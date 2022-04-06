package edu.ucalgary.ensf409;

import java.util.*;
public class Hamper {
    private ArrayList<FoodItem> foodItems = null;
    private int wastedCalories;
    private Nutrition nutritionalRequirement;
    private final Inventory inventory;

    public Hamper(Nutrition needs, Inventory inventory) {
        this.nutritionalRequirement = needs;
        this.inventory = inventory;
        genHamper();
    }

    //adds a food item to ArrayList
    public void addFood(FoodItem item){
        foodItems.add(item);
    }

    //returns food ArrayList
    public ArrayList<FoodItem> getFood(){
        return this.foodItems;
    }





    // TODO: algorithm to generate hamper
    // I am assuming that myInv is an array of food items (obtained from DB),
    // when an item is removed from myInv, it isn't replenished
    // between calls of genHamper.
    public void genHamper(){

        //Example of my thoughts
        ArrayList<FoodItem> myInv = new ArrayList<FoodItem>();

        int calGoal = nutritionalRequirement.getCalories();

        for (int i = 0; i <myInv.size(); i++){
            var item = myInv.remove(i);
            calGoal -= item.getNutrition().getCalories();
            foodItems.add(item);
            if(calGoal <=0){
                break;
            }
        }
        
        this.wastedCalories= calGoal*-1;
    }
    
    // TODO: calculation of wastes
    public int calculateWaste(){
        return this.wastedCalories;
    }
}
