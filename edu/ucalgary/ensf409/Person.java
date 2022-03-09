/** 
* Person.java
* @version 1.0
* @since 1.0	
* First draft of class Person
**/   

package edu.ucalgary.ensf409;

public abstract class Person{
	private int age;
	private char gender;
	private boolean mobilityConcerns;
	
	public Person(int age, char gender, boolean mobilityConcerns){
		this.age = age;
		this.gender = gender;
		this.mobilityConcerns = mobilityConcerns;
	}
	public int getAge(){
		return this.age;
	}
	public char getGender(){
		return this.gender;
	}
	public boolean getMobilityConcerns(){
		return this.mobilityConcerns;
	}
}
