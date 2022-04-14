package edu.ucalgary.ensf409;
import java.util.*;

public class Order {
    private ArrayList<Family> families = new ArrayList<Family>();
    private static Inventory inventory;


    public Order(Family family) {
        this.families.add(family);
        inventory = new Inventory(); // this creates a connection to the database 
    }

    public void addFamily(Family family) {
        this.families.add(family);
    }

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

    // YOU COULD DELETE THIS OR TRANSFER IN FAMILY.JAVA IF NEEDED
    // Note: displayOrder() already fetches the number of members in each family
    /*
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
    */

    public String displayOrder() {
        String str = "Hamper Order Form\n";
        String hamperContent = "";
        str += "Name:\n";
        str += "Date:\n";
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