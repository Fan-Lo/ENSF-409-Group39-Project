/** 
* AdultMale.java
* @version 1.0
* @since 1.0	
* First draft of class AdultMale
**/   

package edu.ucalgary.ensf409;

public class AdultMale extends Person{
	private static final Nutrition NUTRITIONAL_NEEDS = new
	Nutrition(16, 28, 26, 30, 2500);
	private final int CLIENT_ID = 1;
	
	public AdultMale(int age, char gender, boolean mobilityConcerns){
		super(age, gender, mobilityConcerns);
	}
	public Nutrition getNutritionalNeeds(){
		return NUTRITIONAL_NEEDS;
	}
}
