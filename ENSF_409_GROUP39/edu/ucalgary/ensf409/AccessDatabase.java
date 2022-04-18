package edu.ucalgary.ensf409;
import java.sql.*;
import java.util.*;

/**
 * AccessDatabase creates a Connection to mySQL database and allows for handling
 * of simple actions such as insert and remove
 * <p>
 @author Fanny Lo <a href="mailto:fanny.lo@ucalgary.ca"> fanny.lo@ucalgary.ca<a>
 @version 2.2
 @since 1.0
 * Team member
 * Nooreldeen Abdallah (NooreldeenAbd)
 * Justin Kuhn (Justin-kuhn)
 * Fanny Lo (Fan-Lo)
 * Jan Petallo (janpetallo)
 */
public class AccessDatabase{
    public final String DBURL;
    public final String USERNAME;
    public final String PASSWORD;

    private Connection dbConnect;
    private ResultSet results;

    public AccessDatabase(String url, String user, String pw) {
        // Database URL
        this.DBURL = url;

        // Database credentials
        this.USERNAME = user;
        this.PASSWORD = pw;
    }

    // Create a connection to the database
    public void initializeConnection() {
        try {
            dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        } catch (SQLException e) {
			System.out.println("Failed to connect to database");
            e.printStackTrace();
        }
    }

    String getDburl() {
        return this.DBURL;
    }

    String getUsername() {
        return this.USERNAME;
    }

    String getPassword() {
        return this.PASSWORD;
    }
	
    /**
     * Returns the nutritional needs of all clients in an ArrayList. index 0 is
     * for adult male, index 1 for adult female, index 2 for child over 8, index 3
     * for child under 8
     */
    public ArrayList<Nutrition> fetchNutritionalNeeds(){
        ArrayList<Nutrition> nutritions = new ArrayList<Nutrition>();
		int grain, fruit, protein, other, cals;
		String name;
		
		try {                    
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM daily_client_needs");
            
            while (results.next()){
                grain = results.getInt("WholeGrains");
				fruit = results.getInt("FruitVeggies");
				protein = results.getInt("Protein");
				other = results.getInt("Other");
				cals = results.getInt("Calories");
				name = results.getString("Client");
				nutritions.add(new Nutrition(grain, fruit, protein, other, cals));
            }
            
            myStmt.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
		return nutritions;
    }

    /**
     * Returns an array list of all class FoodItem in AVAILABLE_FOOD
     * @return arraylist of FoodItem
     */
	public ArrayList<FoodItem> fetchItems(){
		ArrayList<FoodItem> food = new ArrayList<FoodItem>();
		int grain, fruit, protein, other, cals, id;
		String name;
		
		try {                    
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM available_food");
            
            while (results.next()){
                grain = results.getInt("GrainContent");
				fruit = results.getInt("FVContent");
				protein = results.getInt("ProContent");
				other = results.getInt("Other");
				cals = results.getInt("Calories");
				id = results.getInt("ItemID");
				name = results.getString("Name");
				food.add(new FoodItem(grain, fruit, protein, other, cals, name, id));
                
            }
            
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
		return food;
	}

    /**
     * Deletes entry in table AVAILABLE_FOOD in database FOOD_INVENTORY where
     * entry has ItemID is the specificed itemID
     * @param itemID
     * @return false if there is SQL exception, true otherwise
     */
    public boolean deleteFoodItem(int itemID) {
        try{
            String query = "DELETE FROM AVAILABLE_FOOD WHERE ItemID = ?";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);

            myStmt.setInt(1, itemID);

            int rowCount = myStmt.executeUpdate();

            myStmt.clearParameters();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true; // return true if it is deleted successfully

    }

    /**
     * close method of the database where resutlset and connection are closed.
     */
    public void close() {
        try {
            results.close();
            dbConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * itemIsPresent checks if the food item with itemID is present in the database.
     * returns a boolean value of true if present, false is not presnet
     * @param itemID itemID of the food item of inquiry
     * @return boolean value indicating if the item is present or not
     */
    public boolean itemIsPresent(int itemID) {
        boolean bool = true;
        try {
            String query = "SELECT * FROM AVILABLE_FOOD WHERE ItemID = ?";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);

            myStmt.setInt(1, itemID);
            int rowCount = myStmt.executeUpdate();

            // item is in the table still
            if (rowCount != 0) {
                bool = false;
            }

            myStmt.clearParameters();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bool;
    }

}
