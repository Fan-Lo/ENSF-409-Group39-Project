package edu.ucalgary.ensf409;

public class Nutrition{
	private int grainCals;
	private int fruitCals;
	private int proteinCals;
	private int otherCals;
	private int totalCals;
	private AccessDatabase database;
	
	// constructor used to store Nutritional content of Food items
	public Nutrition(int grain, int fruit, int protein, int other, int total){
		this.grainCals = grain;
		this.fruitCals = fruit;
		this.proteinCals = protein;
		this.otherCals = other;
		this.totalCals = total;
	}
	
	//constructor used to store Nutritional needs of clients
	public Nutrition(int grain, int fruit, int protein, int other, int cals, String name) {
		this.grainCals = grain;  
		this.fruitCals = fruit;
		this.proteinCals = protein;
		this.otherCals = other;
		this.totalCals = cals;
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