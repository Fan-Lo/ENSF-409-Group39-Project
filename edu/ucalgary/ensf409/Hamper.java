/** 
* Hamper.java
* @version 1.5
* @since 1.4	
* Updated to accomodate the changes to FoodItem
*
**/  

package edu.ucalgary.ensf409;

import java.util.*;

public class Hamper{

	private ArrayList<FoodItem> items = new ArrayList<FoodItem>();
	private int grain = 0;
	private int fruit = 0;
	private int protein = 0;
	private int other = 0;
	private int totalCals = Integer.MAX_VALUE;
	
	public Hamper(){
		
	}
	//Copy constructor needed for the algorithm to work
	public Hamper(Hamper hamper){
		this.items = new ArrayList<FoodItem>();
		Iterator<FoodItem> myIterator = hamper.getFood().iterator();
		while(myIterator.hasNext()){
			this.items.add(new FoodItem(myIterator.next()));
		}
		this.grain = hamper.getGrain();
		this.fruit = hamper.getFruit();
		this.protein = hamper.getProtein();
		this.other = hamper.getOther();
		this.totalCals = hamper.getCalories();
	}
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
	public ArrayList<FoodItem> getFood(){
		return this.items;
	}
	public int getCalories(){
		return this.totalCals;
	}
	//Setter which is also needed for the algorithm to work
	public void setCalories(int cals){
		this.totalCals = cals;
	}
	public int getGrain(){
		return this.grain;
	}
	public int getFruit(){
		return this.fruit;
	}
	public int getProtein(){
		return this.protein;
	}
	public int getOther(){
		return this.other;
	}
	
	public String displayHamper(){
		String result = "";
		Iterator<FoodItem> myIterator = this.items.iterator();
		while(myIterator.hasNext()){
			result += myIterator.next().getName() + "\n";
		}
		return result;
	}
	
	public int calculateWaste(int calories){
		return this.totalCals - calories;
	}
	
}
