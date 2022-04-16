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
			this.items.add((myIterator.next())); // I changed from "new FoodItem(myIte...)" to just "myIterator.next()"" inside add()
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
	public void removeFood(int index){
		this.grain -= items.get(index).getWholeGrain();
		this.fruit -= items.get(index).getFruitsVeggies();
		this.protein -= items.get(index).getProtein();
		this.other -= items.get(index).getOther();
		this.totalCals -= items.get(index).getCalories();
		this.items.remove(index);
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
	// Displays the contents of the hamper
	public String displayHamper(){
		String result = "";
		Iterator<FoodItem> myIterator = this.items.iterator();
		while(myIterator.hasNext()){
			FoodItem iterItem = myIterator.next();
			result += iterItem.getItemID() + "\t" + iterItem.getName() + "\n";
		}
		return result;
	}
	//Used for determining the most efficient hamper, by passing in the total calories required and taking the difference.
	public int calculateWaste(int calories){
		return this.totalCals - calories;
	}
	
}