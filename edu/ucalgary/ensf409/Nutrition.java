/** 
* Nutrition.java
* @version 1.0
* @since 1.0	
* First draft of class Nutrition
**/   

package edu.ucalgary.ensf409;

public class Nutrition{
	private int wholeGrainPercent;
	private int fruitsVeggiesPercent;
	private int proteinPercent;
	private int otherPercent;
	private int calories;
	
	public Nutrition(int wholeGrainPercent, int fruitsVeggiesPercent, int proteinPercent,
	int otherPercent, int calories){
		this.wholeGrainPercent = wholeGrainPercent;
		this.fruitsVeggiesPercent = fruitsVeggiesPercent;
		this.proteinPercent = proteinPercent;
		this.otherPercent = otherPercent;
		this.calories = calories;
	}
	public Nutrition toWeeklyNeeds(){
		return new Nutrition(wholeGrainPercent*7, fruitsVeggiesPercent*7,
		proteinPercent*7, otherPercent*7, calories*7);
	}
	public int getWholeGrain(){
		return this.wholeGrainPercent;
	}
	public int getFruitsVeggies(){
		return this.fruitsVeggiesPercent;
	}
	public int getProtein(){
		return this.proteinPercent;
	}
	public int getOther(){
		return this.otherPercent;
	}
	public int getCalories(){
		return this.calories;
	}
}
