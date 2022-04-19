 /**
 * The Hamper class is used to store combinations of FoodItems, which could 
 * potentially be given to a family to sustain them for one week.
 * It has member variables items, which is an ArrayList of all the FoodItems
 * it contains, and integers to represent the cumulative sum of all the 
 * categories of calories as food items are added to it.
 * 
 * Team member
 * Nooreldeen Abdallah (NooreldeenAbd)
 * Justin Kuhn (Justin-kuhn)
 * Fanny Lo (Fan-Lo)
 * Jan Petallo (janpetallo)
 */

package edu.ucalgary.ensf409;

import java.util.*;

public class Hamper{

	private ArrayList<FoodItem> items = new ArrayList<FoodItem>();
	private int grain = 0;
	private int fruit = 0;
	private int protein = 0;
	private int other = 0;
	private int totalCals = Integer.MAX_VALUE;
	
	/**
     * No-argument constructor which is used to create empty hampers in the 
	 * hamper-creation algorithm.
     */
	public Hamper(){
		
	}

	/**
     * Copy constructor, which creates a new hamper as a copy
	 * of a provided hamper.
     * @param hamper Hamper from which to copy from
     */
	public Hamper(Hamper hamper){
		this.items = new ArrayList<FoodItem>();
		Iterator<FoodItem> myIterator = hamper.getFood().iterator();
		while(myIterator.hasNext()){
			this.items.add((myIterator.next())); 
		}
		this.grain = hamper.getGrain();
		this.fruit = hamper.getFruit();
		this.protein = hamper.getProtein();
		this.other = hamper.getOther();
		this.totalCals = hamper.getCalories();
	}
	
	/**
     * Adds a FoodItem to the ArrayList of items, updating
	 * the hamper's calorie counts as well.
     * @param item FoodItem to be added
     */
	public void addFood(FoodItem item){
		this.items.add(item);
		this.grain += item.getWholeGrain();
		this.fruit += item.getFruitsVeggies();
		this.protein += item.getProtein();
		this.other += item.getOther();
		if(this.totalCals == Integer.MAX_VALUE)
			this.totalCals = 0;
		this.totalCals += item.getCalories();
	}
	
	/**
     * Removes a FoodItem from the ArrayList of items, updating
	 * the hamper's calorie counts as well.
     * @param index Index in the array which will be removed
     */
	public void removeFood(int index){
		this.grain -= items.get(index).getWholeGrain();
		this.fruit -= items.get(index).getFruitsVeggies();
		this.protein -= items.get(index).getProtein();
		this.other -= items.get(index).getOther();
		this.totalCals -= items.get(index).getCalories();
		this.items.remove(index);
	}
	
	/**
     * Getter of items
     * @return ArrayList of FoodItems contained in the hamper
     */
	public ArrayList<FoodItem> getFood(){
		return this.items;
	}
	
	/**
     * Getter of calories
     * @return Total amount of calories contained in the hamper
     */
	public int getCalories(){
		return this.totalCals;
	}
	
	/**
     * Setter of calories. Needed in hamper-creation algorithm.
     * @param cals The value which calories will be set to
     */
	public void setCalories(int cals){
		this.totalCals = cals;
	}
	
	/**
     * Getter of grain
     * @return total amount of grain calories in the hamper
     */
	public int getGrain(){
		return this.grain;
	}
	
	/**
     * Getter of fruit
     * @return total amount of fruit/veggie calories in the hamper
     */
	public int getFruit(){
		return this.fruit;
	}
	
	/**
     * Getter of protein
     * @return total amount of protein calories in the hamper
     */
	public int getProtein(){
		return this.protein;
	}
	
	/**
     * Getter of other
     * @return total amount of other calories in the hamper
     */
	public int getOther(){
		return this.other;
	}
	
	/**
     * Displays the contents of the hamper
     * @return String which contains each item's ID and name 
	 * on it's own line.
     */
	public String displayHamper(){
		String result = "";
		Iterator<FoodItem> myIterator = this.items.iterator();
		while(myIterator.hasNext()){
			FoodItem iterItem = myIterator.next();
			result += iterItem.getItemID() + "\t" + iterItem.getName() + "\n";
		}
		return result;
	}
	
	/**
     * Calculates the amount of excess calories the hamper has
	 * @param calories Minimum number of calories the hamper should have 
     * @return the difference between the amount of calories the hamper actually has,
	 * and the ideal amount of calories.
     */
	public int calculateWaste(int calories){
		return this.totalCals - calories;
	}
	
}