package edu.ucalgary.ensf409;
import java.util.*;

public class Order {
    private ArrayList<Family> families;
    private static Inventory inventory;

    // constructor
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


    public boolean removeFromInventory() {
        Iterator<Family> iter = families.iterator();
        while (iter.hasNext()) {
            Hamper hamper = iter.next().getHamper();
            try {
                removeFromInventory(hamper.getFood());
            } catch (Exception e) {
                System.out.print(e.getMessage());
                return false;
            }
        }
        return true;
    }

    private void removeFromInventory(ArrayList<FoodItem> itemToRemove) throws ItemNotFoundException {
        boolean bool;
        for (int i = 0; i < itemToRemove.size(); i++) {
            bool = inventory.removeItems(itemToRemove.get(i));
            if (!bool) {
                // restore all removed items
                inventory.restoreRemovedItems(itemToRemove.subList(0, i-1));
                throw new ItemNotFoundException(itemToRemove.get(i).getName());
            }
        }
        inventory.removeFromDatabase(itemToRemove);  // bool == true when it reaches this point so we remove all items from the database
    }

    public String displayFamilyInfo(Family family) {
        String str = "";
        ArrayList<Person> familyMembers = family.getFamilyMembers();
        // index 0 is adult male, 1 is adult female, 3 is child over 8, 4 is child under 8
        int[] numOfPerson = new int[4];
        for (int i = 0; i < familyMembers.size(); i++) {
            int index = familyMembers.get(i).getClientID() - 1;
            numOfPerson[index]++;
        }
        
        for (int i = 0; i < numOfPerson.length; i++) {
            if (numOfPerson[i] != 0) {
                // not the first time in this loop, put ,
                if (str != "") {
                    str += ", ";
                }
                if (i == 0) {
                    str += numOfPerson[i] + " Adult Male";
                } else if (i == 1) {
                    str += numOfPerson[i] + " Adult Female";
                } else if (i == 2) {
                    str += numOfPerson[i] + " Child over 8";
                } else {
                    str += numOfPerson[i] + " Child under 8";
                }
            }
        }
        return str;
    }

    public String displayOrder() {
        // if can't remove all items from inventory
        if (!removeFromInventory()) {
            System.out.println("Order Cannot Be Completed");
            return null;
        }
        String str = "Hamper Order Form\n";
        String hamperContent = "";
        str += "Name:\n";
        str += "Date:\n";
        str += "Original Request\n";
        for (int i = 0; i < families.size(); i++) {
            str += String.format("Hamper %d: ", i);
            hamperContent += String.format("Hamper %d Items: \n", i);
            Hamper hamper = families.get(i).getHamper();
            hamperContent += hamper.displayHamper();
            hamperContent += "\n\n";
        }

        str += hamperContent;
        return str;
    }
}
