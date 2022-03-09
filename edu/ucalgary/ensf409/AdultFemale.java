/** 
* AdultFemale.java
* @version 1.0
* @since 1.0	
* First draft of class AdultFemale
**/   

package edu.ucalgary.ensf409;

public class AdultFemale extends Person{
	private static final Nutrition NUTRITIONAL_NEEDS = new
	Nutrition(16, 28, 26, 30, 2000);
	private final int CLIENT_ID = 2;
	
	public AdultFemale(int age, char gender, boolean mobilityConcerns){
		super(age, gender, mobilityConcerns);
	}
	public Nutrition getNutritionalNeeds(){
		return NUTRITIONAL_NEEDS;
	}
}
