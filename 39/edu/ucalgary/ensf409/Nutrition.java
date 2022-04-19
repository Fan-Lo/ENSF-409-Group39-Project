/** 
* Nutrition.java
* @author     Justin Kuhn
* href= "mailto:justinkuhn@ucalgary.ca">justin.kuhn@ucalgary.ca</a>
* @version 2.6
* @since 1.5
* Now a superclass to FoodItem
*	
 * Team member
 * Nooreldeen Abdallah (NooreldeenAbd)
 * Justin Kuhn (Justin-kuhn)
 * Fanny Lo (Fan-Lo)
 * Jan Petallo (janpetallo)
* The Nutrition class has member variables wholeGrain which is the amount of grain calories a
* FoodItem contains, fruitsVeggies which is the amount of fruits/veggies calories a
* FoodItem contains, protein which is the amount of protein calories a FoodItem contains,
* other which is the amount of other calories a FoodItem contains, and calories which is the total
* number of calories that a FoodItem contains.
*
**/  

package edu.ucalgary.ensf409;

public class Nutrition{
	private int wholeGrain;
	private int fruitsVeggies;
	private int protein;
	private int other;
	private int calories;
	
	/**
     * Constructs a new Nutriton object given the total number of calories,
	 * and the percentages of each category (between 0 and 100)
     * @param grain Percentage of the total calories which are dedicated to grain
	 * @param fruit Percentage of the total calories which are dedicated to fruit/veggies
	 * @param protein Percentage of the total calories which are dedicated to protein
	 * @param other Percentage of the total calories which are dedicated to other
	 * @param total The total number of calories that a FoodItem contains
     */
	public Nutrition(int grain, int fruit, int protein, int other, int total){
		this.wholeGrain = (int)Math.ceil((grain/(double)100) * total);
		this.fruitsVeggies = (int)Math.ceil((fruit/(double)100) * total);
		this.protein = (int)Math.ceil((protein/(double)100) * total);
		this.other = (int)Math.ceil((other/(double)100) * total);
		this.calories = total;
	}

	/**
     * Getter for wholeGrain
	 * @return number of calories dedicated to grain in a FoodItem
     */
	public int getWholeGrain(){
		return this.wholeGrain;
	}
	
	/**
     * Getter for fruitsVeggies
	 * @return number of calories dedicated to fruits/veggies in a FoodItem
     */
	public int getFruitsVeggies(){
		return this.fruitsVeggies;
	}
	
	/**
     * Getter for protein
	 * @return number of calories dedicated to protein in a FoodItem
     */
	public int getProtein(){
		return this.protein;
	}
	
	/**
     * Getter for other
	 * @return number of calories dedicated to other in a FoodItem
     */
	public int getOther(){
		return this.other;
	}
	
	/**
     * Getter for calories
	 * @return total number of calories in a FoodItem
     */
	public int getCalories(){
		return this.calories;
	}
}
