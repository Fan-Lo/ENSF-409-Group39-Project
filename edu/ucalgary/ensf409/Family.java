/**
@author     Justin Kuhn
href= "mailto:justinkuhn@ucalgary.ca">justin.kuhn@ucalgary.ca</a>
@version    1.4
@since      1.3
 */

package edu.ucalgary.ensf409;

import java.util.*;

public class Family{
	private ArrayList<Person> familyMembers = new ArrayList<>();
	private Hamper hamper;
	
	public Family(){
		
	}
	public Family(int numAMale, int numAFemale, int numChildA8, int numChildU8){
		
	}
	public void addMember(Person member){
		this.familyMembers.add(member);
	}
	public int getWeeklyGrainNeeds(){
		int grain = 0;
		for(int i = 0; i < familyMembers.size(); i++){
			grain += familyMembers.get(i).getNutritionalNeeds().getGrain();
		}
		return grain*7;
	}
	public int getWeeklyVeggieNeeds(){
		int veggie = 0;
		for(int i = 0; i < familyMembers.size(); i++){
			veggie += familyMembers.get(i).getNutritionalNeeds().getFruitsVeggies();
		}
		return veggie*7;
	}
	public int getWeeklyProteinNeeds(){
		int protein = 0;
		for(int i = 0; i < familyMembers.size(); i++){
			protein += familyMembers.get(i).getNutritionalNeeds().getProtein();
		}
		return protein*7;
	}
	public int getWeeklyOtherNeeds(){
		int other = 0;
		for(int i = 0; i < familyMembers.size(); i++){
			other += familyMembers.get(i).getNutritionalNeeds().getOther();
		}
		return other*7;
	}
	public int getWeeklyCalorieNeeds(){
		int calories = 0;
		for(int i = 0; i < familyMembers.size(); i++){
			calories += familyMembers.get(i).getNutritionalNeeds().getCalories();
		}
		return calories*7;
	}
	public ArrayList<Person> getFamilyMembers(){
		return this.familyMembers;
	}
	public void createHamper(Inventory inventory){
		ArrayList<FoodItem> foodAsList = inventory.getFood();
		ArrayList<Hamper> needsSet = optimizeCals(totalNeeds, grainNeeds, fruitNeeds, proteinNeeds, otherNeeds, foodAsList);
		
		Hamper mostEfficient = needsSet.get(0);
		
		for(int i = 0; i < needsSet.size(); i++){
			if(needsSet.get(i).calculateWaste(totalNeeds) < mostEfficient.calculateWaste(totalNeeds)){
				mostEfficient = needsSet.get(i);
			}
		}
		System.out.println("\nThe hamper: " + mostEfficient.toString() + " is the most efficient as it has " + mostEfficient.calculateWaste(totalNeeds) + " excess.");
		this.hamper = mostEfficient; 
	}
	
	private ArrayList<Hamper> optimizeCals(int minCals, int grain, int fruit, int protein, int other, ArrayList<FoodItem> foods) {
        int maxCals = 0;
		for(int i = 0; i < foods.size(); i++){
            maxCals += foods.get(i).getCalories();
        }
        ArrayList<Hamper> hamperCombinations = new ArrayList<Hamper>(maxCals + 1);
		
        for(int i = 0; i <= maxCals; i++){
            hamperCombinations.add(new Hamper());
        } 
		hamperCombinations.get(0).setCalories(0);
		
        for(int i = 0; i < foods.size(); i++){
            for(int j = maxCals; j >= foods.get(i).getCalories(); j--){
                if(hamperCombinations.get(j - foods.get(i).getCalories()).getCalories() != Integer.MAX_VALUE){
					Hamper testHamper = new Hamper(hamperCombinations.get(j - foods.get(i).getCalories()));
					testHamper.addFood(foods.get(i));
					if(testHamper.getCalories() < hamperCombinations.get(j).getCalories()){
						hamperCombinations.set(j, testHamper);
					}
                }
            }
        }
		ArrayList<Hamper> minHampers = new ArrayList<Hamper>();
		//Retaining only the Hampers that meet the minimum requirement
        for(int i = minCals; i <= maxCals; i++){
            if(hamperCombinations.get(i).getCalories() != Integer.MAX_VALUE && hamperCombinations.get(i).getGrain() >= grain
			&& hamperCombinations.get(i).getFruit() >= fruit && hamperCombinations.get(i).getProtein() >= protein
			&& hamperCombinations.get(i).getOther() >= other)
				minHampers.add(hamperCombinations.get(i));
        }
        return minHampers;
    }
	public Hamper getHamper(){
		return this.hamper;
	}
}
