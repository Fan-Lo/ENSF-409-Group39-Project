package edu.ucalgary.ensf409;
import java.util.*;

public class Order {
    private ArrayList<Family> families;
    private static Inventory inventory;

    // null constructor
    public Order(Inventory inv) {
        this.families = new ArrayList<>();
        inventory = inv;
    }

    // constructor when a Family object is passed
    public Order(Family family, Inventory inv) {
        new Order(inv);
        this.families.add(family);
    }

    // constructor when a Family ArrayList is passed
    public Order(ArrayList<Family> familes, Inventory inv) {
        this.families = familes;
        inventory = inv;
    }

    public void addFamily(Family family) {
        this.families.add(family);
    }

    public ArrayList<Family> getFamiles() {
        return this.families;
    }

    public void removeFromInventory(ArrayList<FoodItem> itemAL) {
        for (int i = 0; i < itemAL.size(); i++) {
            inventory.removeItems(itemAL.get(i));
        }
    }
}
