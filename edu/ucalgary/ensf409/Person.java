/** 
* Person.java
* @version 1.2
* @since 1.0	
* 
**/   

package edu.ucalgary.ensf409;

public class Person{

	private final Nutrition NUTRITIONAL_NEEDS;
	private final int CLIENT_ID;
	
	public Person(Nutrition nutritionalNeeds, int clientID){
		this.NUTRITIONAL_NEEDS = nutritionalNeeds;
		this.CLIENT_ID = clientID;
	}
	public Nutrition getNutritionalNeeds(){
		return this.NUTRITIONAL_NEEDS;
	}
	public int getClientID(){
		return this.CLIENT_ID;
	}
}
