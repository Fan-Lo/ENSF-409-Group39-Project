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
	private int grainCals;
	private int fruitCals;
	private int proteinCals;
	private int otherCals;
	private int totalCals;
	
	// constructor used to store Nutritional content of Food items
	public Nutrition(int grain, int fruit, int protein, int other, int total){
		this.grainCals = (int)Math.ceil((grain/(double)100) * total);
		this.fruitCals = (int)Math.ceil((fruit/(double)100) * total);
		this.proteinCals = (int)Math.ceil((protein/(double)100) * total);
		this.otherCals = (int)Math.ceil((other/(double)100) * total);
		this.totalCals = total;
	}
	
	//constructor used to store Nutritional needs of clients
	public Nutrition(int grain, int fruit, int protein, int other, int total, String name) {
		this.grainCals = (int)Math.ceil((grain/(double)100) * total);
		this.fruitCals = (int)Math.ceil((fruit/(double)100) * total);
		this.proteinCals = (int)Math.ceil((protein/(double)100) * total);
		this.otherCals = (int)Math.ceil((other/(double)100) * total);
		this.totalCals = total;
	}

	public int getWholeGrain(){
		return this.grainCals;
	}
	public int getFruitsVeggies(){
		return this.fruitCals;
	}
	public int getProtein(){
		return this.proteinCals;
	}
	public int getOther(){
		return this.otherCals;
	}
	public int getCalories(){
		return this.totalCals;
	}
}
