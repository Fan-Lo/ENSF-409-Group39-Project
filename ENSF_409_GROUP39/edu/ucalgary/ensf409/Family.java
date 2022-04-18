/**
@author     Justin Kuhn
href= "mailto:justinkuhn@ucalgary.ca">justin.kuhn@ucalgary.ca</a>
@version    2.5
@since      1.0
Added trim() method
 * Team member
 * Nooreldeen Abdallah (NooreldeenAbd)
 * Justin Kuhn (Justin-kuhn)
 * Fanny Lo (Fan-Lo)
 * Jan Petallo (janpetallo)
 *
 * the Family class has member variables hamper which is the unique hamper
 * generated for this particular family, integers which represent the number of each
 * type of client in the system, an AccessDatabase object to be able to extract nutritional 
 * information from the database, and an ArrayList of Nutritions, each one corresponding to 
 * the nutritional needs of each type of client.
 * 
 */

package edu.ucalgary.ensf409;

import java.util.*;

public class Family{
	private Hamper hamper;
    private int numAFemale, numAMale, numChildA8, numChildU8;
    private AccessDatabase database = new AccessDatabase("jdbc:mysql://localhost/food_inventory","student","ensf");
    private ArrayList<Nutrition> nutritionalNeeds;

	/**
     * Constructs a new Family given the number of each type of client
     * @param numAMale Number of adult males
	 * @param numAFemale Number of adult females
	 * @param numChildA8 Number of children above 8
	 * @param numChildU8 Number of children under 8
     */
	public Family(int numAMale, int numAFemale, int numChildA8, int numChildU8){
		this.numAMale = numAMale;
        this.numAFemale = numAFemale;
        this.numChildA8 = numChildA8;
        this.numChildU8 = numChildU8;
        database.initializeConnection();
        nutritionalNeeds = database.fetchNutritionalNeeds();;
        // index 0 is male, index 1 is female, index 2 is child above 8 and index 3 is child under 8
		database.close(); // closes the database after getting the Nutritional Needs

	}

	/**
     * Calculates weekly grain needs of the entire family
	 * @return sum of each family member's daily grain needs multiplied by 7
     */
	public int getWeeklyGrainNeeds(){
		int grain = 0;

        grain += numAMale * nutritionalNeeds.get(0).getWholeGrain();
        grain += numAFemale * nutritionalNeeds.get(1).getWholeGrain();
        grain += numChildA8 * nutritionalNeeds.get(2).getWholeGrain();
        grain += numChildU8 * nutritionalNeeds.get(3).getWholeGrain();

		return grain*7;
	}
	
	/**
     * Calculates weekly veggie needs of the entire family
	 * @return sum of each family member's daily veggie needs multiplied by 7
     */
	public int getWeeklyVeggieNeeds(){
		int veggie = 0;

        veggie += (numAMale * nutritionalNeeds.get(0).getFruitsVeggies());
        veggie += (numAFemale * nutritionalNeeds.get(1).getFruitsVeggies());
        veggie += (numChildA8 * nutritionalNeeds.get(2).getFruitsVeggies());
        veggie += (numChildU8 * nutritionalNeeds.get(3).getFruitsVeggies());

		return veggie*7;
	}
	
	/**
     * Calculates weekly protein needs of the entire family
	 * @return sum of each family member's daily protein needs multiplied by 7
     */
	public int getWeeklyProteinNeeds(){
		int protein = 0;

        protein += (numAMale * nutritionalNeeds.get(0).getProtein());
        protein += (numAFemale * nutritionalNeeds.get(1).getProtein());
        protein += (numChildA8 * nutritionalNeeds.get(2).getProtein());
        protein += (numChildU8 * nutritionalNeeds.get(3).getProtein());

		return protein*7;
	}
	
	/**
     * Calculates weekly other needs of the entire family
	 * @return sum of each family member's daily other needs multiplied by 7
     */
	public int getWeeklyOtherNeeds(){
		int other = 0;

        other += (numAMale * nutritionalNeeds.get(0).getOther());
        other += (numAFemale * nutritionalNeeds.get(1).getOther());
        other += (numChildA8 * nutritionalNeeds.get(2).getOther());
        other += (numChildU8 * nutritionalNeeds.get(3).getOther());
		return other*7;
	}
	
	/**
     * Calculates weekly total calorie needs of the entire family
	 * @return sum of each family member's daily total calorie needs multiplied by 7
     */
	public int getWeeklyCalorieNeeds(){
		int calories = 0;

        calories += (numAMale * nutritionalNeeds.get(0).getCalories());
        calories += (numAFemale * nutritionalNeeds.get(1).getCalories());
        calories += (numChildA8 * nutritionalNeeds.get(2).getCalories());
        calories += (numChildU8 * nutritionalNeeds.get(3).getCalories());

		return calories*7;
	}

	/**
     * Creates a hamper for the family from a given inventory
     * @param inventory The inventory from which food is used from
	 * @throws ItemNotFoundException is thrown when a hamper could not be created
     */
	public void createHamper(Inventory inventory) throws ItemNotFoundException{
		int totalNeeds = this.getWeeklyCalorieNeeds();
		int minCals = this.getWeeklyCalorieNeeds(), minGrain = this.getWeeklyGrainNeeds(), minFruit = this.getWeeklyVeggieNeeds(),
		minProtein = this.getWeeklyProteinNeeds(), minOther = this.getWeeklyOtherNeeds(); //del
		ArrayList<FoodItem> foodAsList = inventory.getFood();
		ArrayList<Hamper> needsSet = optimizeCals(foodAsList);

		if(needsSet.size() == 0){
			throw new ItemNotFoundException();
		}
		
		Hamper mostEfficient = needsSet.get(0); // this receives NULL when there needSet received an empty ArrayList
		
		for(int i = 0; i < needsSet.size(); i++){
			if(needsSet.get(i).calculateWaste(totalNeeds) < mostEfficient.calculateWaste(totalNeeds)){
				mostEfficient = needsSet.get(i);
			}
		}
		
		trim(mostEfficient); // Gets rid of as much unnecessary food as it can
		
		this.hamper = mostEfficient; 
	}
	
	/**
     * Helper function to get rid of some unnecessary food after hamper generation
     * @param hamper Hamper to remove food from
     */
	private void trim(Hamper hamper){
		int minCals = this.getWeeklyCalorieNeeds(), minGrain = this.getWeeklyGrainNeeds(), minFruit = this.getWeeklyVeggieNeeds(),
		minProtein = this.getWeeklyProteinNeeds(), minOther = this.getWeeklyOtherNeeds(); //Getting the minimum values of each category
		
		for(int i = 0; i < hamper.getFood().size(); i++){
			if(hamper.getGrain() - hamper.getFood().get(i).getWholeGrain() >= minGrain
			&& hamper.getFruit() - hamper.getFood().get(i).getFruitsVeggies() >= minFruit
			&& hamper.getProtein() - hamper.getFood().get(i).getProtein() >= minProtein
			&& hamper.getOther() - hamper.getFood().get(i).getOther() >= minOther
			&& hamper.getCalories() - hamper.getFood().get(i).getCalories() >= minCals){ 
				hamper.removeFood(i); //Remove item if it can be removed safely
			}
		}
	}
	
	/**
     * Helper function to produce an ArrayList of potential hampers that meet all of the family's needs
     * @param foods Available food from the given inventory
	 * @return ArrayList containing potential hampers that meet the family's needs
     */
	private ArrayList<Hamper> optimizeCals(ArrayList<FoodItem> foods) {
        int maxCals = 0, minCals = this.getWeeklyCalorieNeeds(), grain = this.getWeeklyGrainNeeds(), fruit = this.getWeeklyVeggieNeeds(),
		protein = this.getWeeklyProteinNeeds(), other = this.getWeeklyOtherNeeds();
		
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
	
	/**
     * Getter of the family's hamper
	 * @return This particular family's hamper
     */
	public Hamper getHamper(){
		return this.hamper;
	}

	/**
     * Getter for numAMale
	 * @return number of adult males in family
     */
    public int getNumAMale(){
        return this.numAMale;
    }

	/**
     * Getter for numAFemale
	 * @return number of adult females in family
     */
    public int getNumAFemale(){
        return this.numAFemale;
    }
	
	/**
     * Getter for numChildA8
	 * @return number of children above 8 in family
     */
    public int getNumChildA8(){
        return this.numChildA8;
    }

	/**
     * Getter for numChildU8
	 * @return number of children under 8 in family
     */
    public int getNumChildU8(){
        return this.numChildU8;
    }
}