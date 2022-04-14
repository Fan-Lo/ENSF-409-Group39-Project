/** 
* Person.java
* @version 1.3
* @since 1.2	
* Updated to implement inheritance
* 
**/   

package edu.ucalgary.ensf409;

public class Person extends Nutrition{

	private final int CLIENT_ID;
	
	public Person(int grain, int fruitVeg, int protein, int other, int calories, int clientID){
		super(grain, fruitVeg, protein, other, calories);
		this.CLIENT_ID = clientID;
	}
	public int getClientID(){
		return this.CLIENT_ID;
	}
}
