package edu.ucalgary.ensf409;
import java.sql.*;
import java.util.*;

/**
 * AccessDatabase creates a Connection to mySQL database and allows for handling
 * of simple actions such as insert and remove
 * <p>
 @author Fanny Lo <a href="mailto:fanny.lo@ucalgary.ca"> fanny.lo@ucalgary.ca<a>
 @version 1.2
 @since 1.0
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

    // getter for DBURL
    String getDburl() {
        return this.DBURL;
    }

    // getter for USERNAME
    String getUsername() {
        return this.USERNAME;
    }

    // getter for PASSWORD
    String getPassword() {
        return this.PASSWORD;
    }

    // method commented out as it's not needed
    // public void insertFoodItem(FoodItem item) {
    //     try {
    //         String query = "INSERT INTO AVAILABLE_FOOD (ItemID, Name, GrainContent, FVContent, ProConetent, Other, Calories) VALUES (?,?,?,?,?,?,?)";
    //         PreparedStatement myStmt = dbConnect.prepareStatement(query);

    //         Nutrition itemNutrition = item.getNutrition();
    //         myStmt.setInt(1, item.getItemID());
    //         myStmt.setString(2, item.getName());
    //         myStmt.setInt(3, itemNutrition.getWholeGrain());
    //         myStmt.setInt(4, itemNutrition.getFruitsVeggies());
    //         myStmt.setInt(5, itemNutrition.getProtein());
    //         myStmt.setInt(6, itemNutrition.getOther());
    //         myStmt.setInt(7, itemNutrition.getCalories());

    //         int rowCount = myStmt.executeUpdate();

    //         myStmt.close();

    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }
	
    /**
     * Takes all entries from table AVAILABLE_FOOD and convert each entry into an
     * FoodItem object. Returns a FoodItem array list, which will become our invenotry
     * later.
     * @return an array list of FoodItem objects, each FoodItem object is an 
     * entry in the table AVAILABLE_FOOD
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

    // returns an array of nutritional needs of a client from the database in the 
    // order of grain, fruitsAndVeggie, protein, other, calories
    public int[] getClientNeeds(int ID) {
        int[] nutritionalNeeds = new int[5];
        try {
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM DAILY_CLIENT_NEEDS WHERE ClientID = " + ID);

            while (results.next()) {
                nutritionalNeeds[0] = results.getInt("WholeGrains");
                nutritionalNeeds[1] = results.getInt("FruitVeggies");
                nutritionalNeeds[2] = results.getInt("Protein");
                nutritionalNeeds[3] = results.getInt("Other");
                nutritionalNeeds[4] = results.getInt("Calories");
            }
            myStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();;
        }
        return nutritionalNeeds;
    }

    // Removes entries in AVAILABLE_FOOD that has the specified ItemID.
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

    // closes the connection
    public void close() {
        try {
            results.close();
            dbConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     /*public static void main(String[] args) {
         AccessDatabase db = new AccessDatabase("jdbc:mysql://localhost/FOOD_INVENTORY", "student", "ensf");
		 db.initializeConnection();
         int[] test = db.getClientNeeds(1);
         for (int i = 0; i < 5; i++) {
             System.out.println(test[i]);
         }
         db.close();
     }*/
}
