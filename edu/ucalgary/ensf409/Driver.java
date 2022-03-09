/** 
* Driver.java
* @version 1.0
* @since 1.0	
* This is a file made to test the functionality of the individual components
* Not part of the UML diagram
**/   

package edu.ucalgary.ensf409;

public class Driver{
	public static void main(String[] args){
		/* Testing Nutrional Needs of each family member */
		System.out.println(AdultMale.getNutritionalNeeds().getCalories());
		System.out.println(AdultFemale.getNutritionalNeeds().getCalories());
		System.out.println(ChildOver8.getNutritionalNeeds().getCalories());
		System.out.println(ChildUnder8.getNutritionalNeeds().getCalories());
		/* Prints 2500, 2000, 2200, 1400 */
		
		Nutrition adultMaleWeeklyNeeds = AdultMale.getNutritionalNeeds().toWeeklyNeeds();
		System.out.println(adultMaleWeeklyNeeds.getCalories());
		/* Prints 17500 */
	}
}
