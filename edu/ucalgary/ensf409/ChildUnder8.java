/** 
* ChildUnder8.java
* @version 1.0
* @since 1.0	
* First draft of class ChildUnder8
**/   

package edu.ucalgary.ensf409;

public class ChildUnder8 extends Person{
	private static final Nutrition NUTRITIONAL_NEEDS = new
	Nutrition(21, 33, 31, 15, 1400);
	private final int CLIENT_ID = 4;
	
	public ChildUnder8(int age, char gender, boolean mobilityConcerns){
		super(age, gender, mobilityConcerns);
	}
	public static Nutrition getNutritionalNeeds(){
		return NUTRITIONAL_NEEDS;
	}
}
