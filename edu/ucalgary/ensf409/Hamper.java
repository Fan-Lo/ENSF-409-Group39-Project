/**
@author     Nooreldeen Abdallah
href= "mailto:nooreldeen.abdallah@ucalgary.ca">nooreldeen.abdallah@ucalgary.ca</a>
@version    1.4
@since      1.0
 */

 package edu.ucalgary.ensf409;

import java.util.*;
public class Hamper {
    private ArrayList<FoodItem> foodItems = null;
    private int wastedCalories;
    private Nutrition nutritionalRequirement;

    public Hamper(Nutrition needs) {
        this.nutritionalRequirement = needs;
    }

    //adds a food item to ArrayList
    public void addFood(FoodItem item){
        foodItems.add(item);
    }

    //returns food ArrayList
    public ArrayList<FoodItem> getFood(){
        return this.foodItems;
    }

    // Returns the sum of all calories in this hamper
    public Nutrition calculateNutrition(){
        int grain = 0;
        int frVg = 0;
        int pro = 0;
        int other = 0;
        int cals =0;
        for(var item : foodItems){
            var currNut = item.getNutrition();
            grain += currNut.getWholeGrain();
            frVg += currNut.getFruitsVeggies();
            pro += currNut.getProtein();
            other += currNut.getOther();
            cals += currNut.getCalories();
        }
        Nutrition totalHampeNutrition = new Nutrition(grain, frVg, pro, other, cals);
        return totalHampeNutrition;
    }

    // Returns food names sperated by a comma
    public String displayHamper(){
        String disp = "";
        for(var item : foodItems){
            disp += item.getName() + ", ";
        }

        return disp.substring(0, disp.length()-1);
    }

    // // Returns the total wasted calories from each catagory
    public int calculateWaste(){
        var nutritionInHamper = calculateNutrition();
        this.wastedCalories = 0;

        wastedCalories += nutritionInHamper.getWholeGrain() - nutritionalRequirement.getWholeGrain();
        wastedCalories += nutritionInHamper.getFruitsVeggies() - nutritionalRequirement.getFruitsVeggies();
        wastedCalories += nutritionInHamper.getProtein() - nutritionalRequirement.getProtein();
        wastedCalories += nutritionInHamper.getOther() - nutritionalRequirement.getOther();
        wastedCalories += nutritionInHamper.getCalories() - nutritionalRequirement.getCalories();

        return this.wastedCalories;
    }
}
