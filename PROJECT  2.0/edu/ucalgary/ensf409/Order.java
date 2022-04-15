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
    public Order(Family family) {
        this.families.add(family);
        inventory = new Inventory(); // this creates a connection to the database 
    }

    /**
     * Adds the given Family object into families
     * @param family
     */
    public void addFamily(Family family) {
        this.families.add(family);
    }

    // getter for families
    public ArrayList<Family> getFamiles() {
        return this.families;
    }


    public void generateHampers() throws ItemNotFoundException{

        for(int i = 0; i < families.size(); i++){
            families.get(i).createHamper(inventory);
            boolean removed = removeFromInventory(families.get(i)); //remove from ArrayList inventory only, not the database
            if(!removed){ // if there was an exception, we need to restore all the previously removed items for the previous families
                for(int j = i - 1; j >= 0; j--){ 
                    ArrayList<FoodItem> itemsToRemove = families.get(j).getHamper().getFood();
                    inventory.restoreRemovedItems(itemsToRemove);
                }
                throw new ItemNotFoundException();
            }
        }

        //When every hamper for each family was successfully removed from ArrayList, then we start removing from database now.
        for(Family fam: families){
            ArrayList<FoodItem> itemsToRemove = fam.getHamper().getFood();
            inventory.removeFromDatabase(itemsToRemove);
        }
    }


    /**
     * This is the driver code for removing all FoodItem objects in hampers generated
     * for each family included in families. 
     * @return a boolean value of whether the removal of all items is successful
     */
    public boolean removeFromInventory(Family fam) {
        Hamper hamper = fam.getHamper();
        try {
            removeFromInventory(hamper.getFood());
        } catch (ItemNotFoundException e) {
            System.out.print(e.getMessage());
            return false;
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
                // restore all removed items for the current family only, need to restore the items removed for the previous families as well
                inventory.restoreRemovedItems(itemToRemove.subList(0, i-1));
                throw new ItemNotFoundException(itemToRemove.get(i).getName());
            }
        }
    }



    /**
     * Returns a String containing containing all details of the Order form.
     * Including family composition and hamper composition.
     * @return String
     */
    public String displayOrder() {
        String str = "Hamper Order Form\n\n";
        String hamperContent = "";
        str += "Name:\n";
        str += "Date:\n\n";
        str += "Original Request\n";
        for (int i = 0; i < families.size(); i++){
            str += String.format("Hamper %d: ", i + 1);
            str += Integer.toString( families.get(i).getNumAMale()) + " Adult Male, " + Integer.toString( families.get(i).getNumAFemale()) + " Adult Female, " + 
                    Integer.toString( families.get(i).getNumChildA8()) + " Child Over 8, " + Integer.toString( families.get(i).getNumChildU8()) + " Child Under 8 \n";
        }
        str += "\n";

        for (int i = 0; i < families.size(); i++) { //changed from int i = 1 to i = 0 and 
            //str += String.format("Hamper %d: ", i + 1);
            hamperContent += String.format("Hamper %d Items: \n", i + 1);
            Hamper hamper = families.get(i).getHamper();
            hamperContent += hamper.displayHamper();
            hamperContent += "\n\n";
        }

        str += hamperContent;
        return str;
    }
}