package edu.ucalgary.ensf409;
import java.sql.*;

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

    // may not need this function
    public void insertFoodItem(FoodItem item) {
        try {
            String query = "INSERT INTO AVAILABLE_FOOD (ItemID, Name, GrainContent, FVContent, ProConetent, Other, Calories) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);

            Nutrition itemNutrition = item.getNutrition();
            myStmt.setInt(1, item.getItemID());
            myStmt.setString(2, item.getName());
            myStmt.setInt(3, itemNutrition.getWholeGrain());
            myStmt.setInt(4, itemNutrition.getFruitsVeggies());
            myStmt.setInt(5, itemNutrition.getProtein());
            myStmt.setInt(6, itemNutrition.getOther());
            myStmt.setInt(7, itemNutrition.getCalories());

            int rowCount = myStmt.executeUpdate();

            myStmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // delete the first occurence of the item
    public void deleteFoodItem(String name) {
        try{
            String query = "DELETE FROM AVAILABLE_FOOD WHERE Name = ?";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);

            myStmt.setString(1, name);

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