/** 
* Nutrition.java
* @version 1.4
* @since 1.3	
* Updated to be a superclass
* 
**/  

package edu.ucalgary.ensf409;

public class Nutrition{
	private int wholeGrain;
	private int fruitsVeggies;
	private int protein;
	private int other;
	private int calories;
	
	public Nutrition(int wholeGrain, int fruitsVeggies, int protein, int other, int calories){
		this.wholeGrain = (int)Math.ceil((wholeGrain/(double)100) * calories);
		this.fruitsVeggies = (int)Math.ceil((fruitsVeggies/(double)100) * calories);
		this.protein = (int)Math.ceil((protein/(double)100) * calories);
		this.other = (int)Math.ceil((other/(double)100) * calories);
		this.calories = calories;
	}
	public Nutrition(Nutrition nutrition){
		this.wholeGrain = nutrition.getWholeGrain();
		this.fruitsVeggies = nutrition.getFruitsVeggies();
		this.protein = nutrition.getProtein();
		this.other = nutrition.getOther();
		this.calories = nutrition.getCalories();
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
