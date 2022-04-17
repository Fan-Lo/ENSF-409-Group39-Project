package edu.ucalgary.ensf409;
import java.io.*;

/**
 * OrderForm class uses the Order class. It's sole purpose is to write the generated
 * order form into a text file.
 * <p>
 @author Fanny Lo <a href="mailto:fanny.lo@ucalgary.ca"> fanny.lo@ucalgary.ca<a>
 @version 1.1
 @since 1.0
 */
public class OrderForm {
    /**
     * Takes an Order object and the path to the output file. It will write the
     * generated order form to a txt file according to the specified path
     * @param order Order object 
     * @param output path to the output file
     */
    public void formToTxtFile(Order order, String output) {
        String orderInfo = order.displayOrder();
        try {
            FileWriter writer = new FileWriter(output);
            writer.write(orderInfo);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();;
        }
    }
 
    /**
     * This method is called when there is not enough food in inventory to complete an Order request
     * @param output
     */
      public void formToText(String output){
        String shortage = "Hamper Order Form: \n\nThere is currently not enough food items to fulfill you request.\nThank you!";
        try {
            FileWriter writer = new FileWriter(output);
            writer.write(shortage);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();;
        }
    }
}
