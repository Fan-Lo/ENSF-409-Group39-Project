package edu.ucalgary.ensf409;
import java.util.*;

public class Order {
    private ArrayList<Family> families;

    // null constructor
    public Order() {
        this.families = new ArrayList<>();
    }

    // constructor when a Family object is passed
    public Order(Family family) {
        new Order();
        this.families.add(family);
    }

    // constructor when a Family ArrayList is passed
    public Order(ArrayList<Family> familes) {
        this.families = familes;
    }

    public void addFamily(Family family) {
        this.families.add(family);
    }

    public ArrayList<Family> getFamiles() {
        return this.families;
    }
}
