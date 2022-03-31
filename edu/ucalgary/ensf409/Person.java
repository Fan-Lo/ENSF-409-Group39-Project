/** 
* Person.java
* @version 1.1
* @since 1.0	
* First draft of class Person
**/   

package edu.ucalgary.ensf409;

public class Person{
	private final Nutrition NUTRITIONAL_NEEDS;
	private final int CLIENT_ID;
	
	public Person(Nutrition nutritionalNeeds, int clientID){
		this.NUTRITIONAL_NEEDS = nutritionalNeeds;
		this.CLIENT_ID = clientID;
	}

}
