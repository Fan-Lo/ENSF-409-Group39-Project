package edu.ucalgary.ensf409;
import java.sql.*;
import java.util.*;

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
				nutritions.add(new Nutrition(grain, fruit, protein, other, cals, name));
            }
            
            myStmt.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
		return nutritions;
    }

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

    // delete the first occurence of the item
    public void deleteFoodItem(int itemID) {
        try{
            String query = "DELETE FROM AVAILABLE_FOOD WHERE ItemID = ?";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);

            myStmt.setInt(1, itemID);

            int rowCount = myStmt.executeUpdate();

            myStmt.clearParameters();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void close() {
        try {
            results.close();
            dbConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
