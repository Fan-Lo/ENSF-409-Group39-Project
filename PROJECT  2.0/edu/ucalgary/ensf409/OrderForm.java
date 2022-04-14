package edu.ucalgary.ensf409;
import java.io.*;

public class OrderForm {
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
}