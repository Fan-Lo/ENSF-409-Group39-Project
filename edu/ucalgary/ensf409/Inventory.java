package edu.ucalgary.ensf409;

import java.util.*;

public class Inventory {
    private ArrayList<FoodItem> inventory = null;

    public Inventory() {
        inventory = new ArrayList<>();
    }
    
    public void addToInventory(int grain, int fv, int pro, int other, int cal, String name, int ID) {
        inventory.add(new FoodItem(grain, fv, pro, other, cal, name, ID));
    }

    public boolean removeItems(FoodItem item) {
        return inventory.remove(item);
    }
}
