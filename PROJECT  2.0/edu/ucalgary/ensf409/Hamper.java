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
		this.grain += item.getNutrition().getWholeGrain();
		this.fruit += item.getNutrition().getFruitsVeggies();
		this.protein += item.getNutrition().getProtein();
		this.other += item.getNutrition().getOther();
		if(this.totalCals == Integer.MAX_VALUE)
			this.totalCals = 0;
		this.totalCals += item.getNutrition().getCalories();
	}
	public void removeFood(int index){
		this.grain -= items.get(index).getNutrition().getWholeGrain();
		this.fruit -= items.get(index).getNutrition().getFruitsVeggies();
		this.protein -= items.get(index).getNutrition().getProtein();
		this.other -= items.get(index).getNutrition().getOther();
		this.totalCals -= items.get(index).getNutrition().getCalories();
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
	//Similar to display function, which I used for testing purposes
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