package edu.ucalgary.ensf409;

/**
 * ItemNotFoundException is a custom exception thrown when the intended
 * food item is not found in the inventory.
 * <p>
 @author Fanny Lo <a href="mailto:fanny.lo@ucalgary.ca"> fanny.lo@ucalgary.ca<a>
 @version 2.0
 @since 1.0
 * Team member
 * Nooreldeen Abdallah (NooreldeenAbd)
 * Justin Kuhn (Justin-kuhn)
 * Fanny Lo (Fan-Lo)
 * Jan Petallo (janpetallo)
 */
public class ItemNotFoundException extends Exception{
    public ItemNotFoundException() {
        super();
    }

    public ItemNotFoundException(String foodItem) {
        super(foodItem + " cannot be removed");
    }
}