package edu.ucalgary.ensf409;

public class ItemNotFoundException extends Exception{
    public ItemNotFoundException() {
        super();
    }

    public ItemNotFoundException(String foodItem) {
        super(foodItem + " cannot be removed");
    }
}