package edu.ucalgary.ensf409;
import java.util.*;

/**
 * the Order class takes has member variable families, which is an array list of 
 * Family objects, and inventory, which is an Inventory object. Order handles calls
 * the getHamper method for each Family object in the families vairable, and 
 * removes all items present in the generated hamper from the Inventory object. It
 * will not remove any items in a particular hamper if the FoodItem is not found
 * in the inventory.
 * <p>
 @author Fanny Lo <a href="mailto:fanny.lo@ucalgary.ca"> fanny.lo@ucalgary.ca<a>
 @version 1.4
 @since 1.0
 */
public class Order {

    private ArrayList<Family> families = new ArrayList<Family>();
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


    /**
     * Adds the given Family object into families
     * @param family
     */
    public void addFamily(Family family) {
        this.families.add(family);
    }

    // getter for families
    public ArrayList<Family> getFamilies() {
        return this.families;
    }

    /**
     * This is the driver code for removing all FoodItem objects in hampers generated
     * for each family included in families. 
     * @return a boolean value of whether the removal of all items is successful
     */
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

    /**
     * Helper function for removeFromInventory(). This method takes an argument of
     * array list of food item, and remove each item from the inventory. 
     * @param itemToRemove is the array list of items to be removed.
     * @throws ItemNotFoundException is thrown when the item is not found in the 
     * inventory. Exception handling is done by the driver code of removeFromInventory() above.
     */
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

    /**
     * displayFamilyInfo takes a Family object as argument and displays the composition
     * of the family.
     * @param family
     * @return a string containing information of family composition
     */
    public String displayFamilyInfo(Family family) {
        String str = "";
        ArrayList<Person> familyMembers = family.getFamilyMembers();

        // index 0 is adult male, 1 is adult female, 3 is child over 8, 4 is child under 8
        int[] numOfPerson = new int[4];

        for (int i = 0; i < familyMembers.size(); i++) {
            // client ID is always one more than the index.
            // i.e. adult male has index 0, but ID of 1
            int index = familyMembers.get(i).getClientID() - 1;
            numOfPerson[index]++;
        }
        
        for (int i = 0; i < numOfPerson.length; i++) {
            if (numOfPerson[i] != 0) {
                // if not the first time in this loop, put ,
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

    /**
     * Returns a String containing containing all details of the Order form.
     * Including family composition and hamper composition.
     * @return String
     */
    public String displayOrder() {
        boolean bool = removeFromInventory();
        // if can't remove all items from inventory
        if (!bool) {
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
