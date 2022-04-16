/** 
* Nutrition.java
* 
* @version 1.6
* @since 1.5
* Now a superclass to FoodItem
*	
**/  

package edu.ucalgary.ensf409;

public class Nutrition{
	private int wholeGrain;
	private int fruitsVeggies;
	private int protein;
	private int other;
	private int calories;
	
	// constructor used to store Nutritional content of Food items
	public Nutrition(int grain, int fruit, int protein, int other, int total){
		this.wholeGrain = (int)Math.ceil((grain/(double)100) * total);
		this.fruitsVeggies = (int)Math.ceil((fruit/(double)100) * total);
		this.protein = (int)Math.ceil((protein/(double)100) * total);
		this.other = (int)Math.ceil((other/(double)100) * total);
		this.calories = total;
	}

	public int getWholeGrain(){
		return this.wholeGrain;
	}
	public int getFruitsVeggies(){
		return this.fruitsVeggies;
	}
	public int getProtein(){
		return this.protein;
	}
	public int getOther(){
		return this.other;
	}
	public int getCalories(){
		return this.calories;
	}
}
