/**
 * Test file for methods in the Order class.
 * <p>
 * @version 1.3
 * @Since 1.0
 */

package edu.ucalgary.ensf409;
import org.junit.*;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.*;


/* *********** Tests for the Order class  *********** */
public class OrderTest {
    
    // Test data 


    private Family family1 = new Family(1,0,0,0);
    private Family family2 = new Family(2,0,0,0);
     
    /*
     * Using Order constructors and use the getter 
     * to retrieve an arraylist containing the same family object.
    */
    @Test
    public void testOrderConstructorAndFamilyGetter() {
        // generating expected array list
        ArrayList<Family> expectedFamilyAL = new ArrayList<>();
        expectedFamilyAL.add(family1);
        
        // Using Order constructors
        Order testOrder = new Order(family1);
        
        assertEquals("Incorrect array list was return using getFamilies()", expectedFamilyAL, testOrder.getFamilies());
    }

    /*
     * Testing that addFamily() successfuly add the family into the arraylist
     */
    @Test
    public void testAddFamily() {
        // generating expected array list
        ArrayList<Family> expectedFamilyAL = new ArrayList<>();
        expectedFamilyAL.add(family1);
        expectedFamilyAL.add(family2);

        // Using addFamily method
        Order testOrder = new Order(family1);
        testOrder.addFamily(family2);

        assertEquals("Incorrect array list was return after addFamily() was called", expectedFamilyAL, testOrder.getFamilies());

    }

    /*
     * Testing that generateHamper can generate hampers for one family and that it doesn't throw exception
     * when it shouldn't. Also test that the generated hamper meets the weekly nutritional needs of the family and the
     * items in the hamper have been removed successfully from the inventory
     */
    @Test
    public void testGenerateHamper1Family() {
        Order testOrder1Family = new Order(family1);

        boolean noException = true;
        try {
            testOrder1Family.generateHampers();
        } catch (ItemNotFoundException e) {
            noException = false;
        }

        assertTrue("generateHampers threw ItemNotFoundException when it shouldn't", noException);
    
        // check that the generate hamper meets the nutritional needs of the family
        boolean hamperIsValid = hamperIsValid(testOrder1Family.getFamilies().get(0));
        assertTrue("Hamper created for family doesn't not meet the nutritional needs of the family", hamperIsValid);

         // check that deleted items are not in the inventory anymore
         boolean hamperDeletedFromInv = hamperItemRemoved(testOrder1Family.getFamilies().get(0).getHamper());
         assertTrue("Hamper item is still present in inventory", hamperDeletedFromInv);
    }


    /*
     * Testing that generateHamper can generate hampers for more than one family (2 families) and that it doesn't throw exception
     * when it shouldn't. Also test that the generated hamper meets the weekly nutritional needs of the families and the
     * items in the hamper have been removed successfully from the inventory
     */
    @Test
    public void testGenerateHamper2Families() {
        Order testOrder2Families = new Order(family1);
        testOrder2Families.addFamily(family2);

        boolean noException = true;
        try {
            testOrder2Families.generateHampers();
        } catch (ItemNotFoundException e) {
            noException = false;
        }

        assertTrue("generateHampers threw ItemNotFoundException when it shouldn't", noException);
    
        Iterator<Family> famIter= testOrder2Families.getFamilies().iterator();
        boolean hamperIsValid = true;
        boolean hamperDeletedFromInv = true;
        while (famIter.hasNext()) {
            Family fam = famIter.next();

            // check that the generate hamper meets the nutritional needs of the family
            hamperIsValid = hamperIsValid && hamperIsValid(fam);

            // check that deleted items are not in the inventory anymore
            hamperDeletedFromInv = hamperDeletedFromInv && hamperItemRemoved(fam.getHamper());
        }

        assertTrue("Hampers created for families do not meet the nutritional needs of the families", hamperIsValid);
        assertTrue("Hamper item is still present in inventory", hamperDeletedFromInv);
    }

    /**
     * Checks if food item in the hamper is present in display order. It doesn't check
     * for formatting. ony that food item name is present.
     */
    @Test
    public void testDisplayOrder() {
        Order testOrder = new Order(family1);
        try {
            testOrder.generateHampers();
        } catch (ItemNotFoundException e) {
            assertTrue("Not enough food item for hamper generation. Refresh inventory.sql to restore inventory", true);
        }

        String orderInfo = testOrder.displayOrder();
        Hamper hamper = testOrder.getFamilies().get(0).getHamper();
        boolean bool = orderInfo.contains(hamper.displayHamper());
        assertTrue("displayOrder returns a string that doesn't contain the correct hamper food item", bool);
    }

    /**
     * Compares the nutritional content in the hamper is at least the nutritional needs
     * of the family. The mehtod does not check if the hamper is optimized or not,
     * only that the needs of the family is met.
     * @param order
     * @return
     */
    private static boolean hamperIsValid(Family family) {
        int grainInHamper = 0, fvInHamper = 0, proInHamper = 0, otherInHamper = 0, calInHamper = 0;
        Hamper hamper = family.getHamper();
        grainInHamper = hamper.getGrain();
        fvInHamper = hamper.getFruit();
        proInHamper = hamper.getProtein();
        otherInHamper = hamper.getOther();
        calInHamper = hamper.getCalories();

        if (grainInHamper < family.getWeeklyGrainNeeds()) {
            return false;
        } else if (fvInHamper < family.getWeeklyVeggieNeeds()) {
            return false;
        } else if (proInHamper < family.getWeeklyProteinNeeds()) {
            return false;
        } else if (otherInHamper < family.getWeeklyOtherNeeds()) {
            return false;
        } else if (calInHamper < family.getWeeklyCalorieNeeds()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * hamperItemRemoved checks if all FoodItem in hamper is removed.
     * @param hamper hamper whose content is to be checked
     * @return boolean value. true is all items are not present in the database. false if at least one
     * item is still present in database.
     */
    private static boolean hamperItemRemoved (Hamper hamper) {
        boolean bool = false;
        // initialize connection to the database
        AccessDatabase db = new AccessDatabase("jdbc:mysql://localhost/food_inventory", "student", "ensf");
        db.initializeConnection();

        Iterator<FoodItem> iter = hamper.getFood().iterator();
        while (iter.hasNext()) {
            bool = db.itemIsPresent(iter.next().getItemID());
            if (!bool) {
                return bool;
            }
        }
        // db.close() is commented out because it causes null point exception
        // db.close();
        return bool;
    }
 


}
