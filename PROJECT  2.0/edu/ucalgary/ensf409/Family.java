/**
@author     Justin Kuhn
href= "mailto:justinkuhn@ucalgary.ca">justin.kuhn@ucalgary.ca</a>
@version    1.3
@since      1.2
 */

package edu.ucalgary.ensf409;

import java.util.*;

public class Family{
	private Hamper hamper;
    private int numAFemale, numAMale, numChildA8, numChildU8;
    private AccessDatabase database = new AccessDatabase("jdbc:mysql://localhost/food_inventory","student","ensf");
    private ArrayList<Nutrition> nutritionalNeeds;
	 
	public Family(){
        database = new AccessDatabase("jdbc:mysql://localhost/food_inventory","student","ensf");
        database.initializeConnection();
	}

	public Family(int numAMale, int numAFemale, int numChildA8, int numChildU8){
		this.numAMale = numAMale;
        this.numAFemale = numAFemale;
        this.numChildA8 = numChildA8;
        this.numChildU8 = numChildU8;
        database.initializeConnection();
        nutritionalNeeds = database.fetchNutritionalNeeds();;
        // index 0 is male, index 1 is female, index 2 is child above 8 and index 3 is child under 8
	}

	public int getWeeklyGrainNeeds(){
		int grain = 0;

        grain += numAMale * nutritionalNeeds.get(0).getWholeGrain();
        grain += numAFemale * nutritionalNeeds.get(1).getWholeGrain();
        grain += numChildA8 * nutritionalNeeds.get(2).getWholeGrain();
        grain += numChildU8 * nutritionalNeeds.get(3).getWholeGrain();

		return grain*7;
	}
	public int getWeeklyVeggieNeeds(){
		int veggie = 0;

        veggie += (numAMale * nutritionalNeeds.get(0).getFruitsVeggies());
        veggie += (numAFemale * nutritionalNeeds.get(1).getFruitsVeggies());
        veggie += (numChildA8 * nutritionalNeeds.get(2).getFruitsVeggies());
        veggie += (numChildU8 * nutritionalNeeds.get(3).getFruitsVeggies());

		return veggie*7;
	}
	public int getWeeklyProteinNeeds(){
		int protein = 0;

        protein += (numAMale * nutritionalNeeds.get(0).getProtein());
        protein += (numAFemale * nutritionalNeeds.get(1).getProtein());
        protein += (numChildA8 * nutritionalNeeds.get(2).getProtein());
        protein += (numChildU8 * nutritionalNeeds.get(3).getProtein());

		return protein*7;
	}
	public int getWeeklyOtherNeeds(){
		int other = 0;

        other += (numAMale * nutritionalNeeds.get(0).getOther());
        other += (numAFemale * nutritionalNeeds.get(1).getOther());
        other += (numChildA8 * nutritionalNeeds.get(2).getOther());
        other += (numChildU8 * nutritionalNeeds.get(3).getOther());
		return other*7;
	}
	public int getWeeklyCalorieNeeds(){
		int calories = 0;

        calories += (numAMale * nutritionalNeeds.get(0).getCalories());
        calories += (numAFemale * nutritionalNeeds.get(1).getCalories());
        calories += (numChildA8 * nutritionalNeeds.get(2).getCalories());
        calories += (numChildU8 * nutritionalNeeds.get(3).getCalories());

		return calories*7;
	}


	public void createHamper(Inventory inventory) throws ItemNotFoundException{
		int totalNeeds = this.getWeeklyCalorieNeeds();
		ArrayList<FoodItem> foodAsList = inventory.getFood();
		ArrayList<Hamper> needsSet = optimizeCals(foodAsList);

		if(needsSet.size() == 0){
			// there are some instances this happens in my testing
			// TODO: need a logic that handles this or maybe this is enough
			throw new ItemNotFoundException();
		}
		
		Hamper mostEfficient = needsSet.get(0); // this receives NULL when there needSet received an empty ArrayList
		
		for(int i = 0; i < needsSet.size(); i++){
			if(needsSet.get(i).calculateWaste(totalNeeds) < mostEfficient.calculateWaste(totalNeeds)){
				mostEfficient = needsSet.get(i);
			}
		}
		//System.out.println("\nThe hamper: " + mostEfficient.toString() + " is the most efficient as it has " + mostEfficient.calculateWaste(totalNeeds) + " excess.");
		this.hamper = mostEfficient; 
	}
	
	private ArrayList<Hamper> optimizeCals(ArrayList<FoodItem> foods) {
        int maxCals = 0, minCals = this.getWeeklyCalorieNeeds(), grain = this.getWeeklyGrainNeeds(), fruit = this.getWeeklyVeggieNeeds(),
		protein = this.getWeeklyProteinNeeds(), other = this.getWeeklyOtherNeeds();
		
		for(int i = 0; i < foods.size(); i++){
            maxCals += foods.get(i).getNutrition().getCalories();
        }
        ArrayList<Hamper> hamperCombinations = new ArrayList<Hamper>(maxCals + 1);
		
        for(int i = 0; i <= maxCals; i++){
            hamperCombinations.add(new Hamper());
        } 
		hamperCombinations.get(0).setCalories(0);
		
        for(int i = 0; i < foods.size(); i++){
            for(int j = maxCals; j >= foods.get(i).getNutrition().getCalories(); j--){
                if(hamperCombinations.get(j - foods.get(i).getNutrition().getCalories()).getCalories() != Integer.MAX_VALUE){
					Hamper testHamper = new Hamper(hamperCombinations.get(j - foods.get(i).getNutrition().getCalories()));
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

    public int getNumAMale(){
        return this.numAMale;
    }

    public int getNumAFemale(){
        return this.numAFemale;
    }
    public int getNumChildA8(){
        return this.numChildA8;
    }

    public int getNumChildU8(){
        return this.numChildU8;
    }
}