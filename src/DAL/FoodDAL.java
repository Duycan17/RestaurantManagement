/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author duy
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodDAL {

    private Connection connection;

    public FoodDAL() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/thucan?characterEncoding=UTF-8";
            String username = "root";
            String password = "123";
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void addFood(Food food) {
        try {
            String query = "INSERT INTO food (name, description, price) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, food.getName());
            statement.setString(2, food.getDescription());
            statement.setDouble(3, food.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateFood(Food food) {
        try {
            String query = "UPDATE food SET name = ?, description = ?, price = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, food.getName());
            statement.setString(2, food.getDescription());
            statement.setDouble(3, food.getPrice());
            statement.setInt(4, food.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFood(int foodId) {
        try {
            String query = "DELETE FROM food WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, foodId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Food getFoodById(int foodId) {
        try {
            String query = "SELECT * FROM food WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, foodId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                return new Food(foodId, name, description, price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Food> getAllFoods() {
        List<Food> foods = new ArrayList<>();
        try {
            String query = "SELECT * FROM food";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                foods.add(new Food(id, name, description, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foods;
    }

    public List<Food> searchFood(String keyword) {
        List<Food> foods = new ArrayList<>();
        try {
            String query = "SELECT * FROM food WHERE name LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + keyword + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                foods.add(new Food(id, name, description, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foods;
    }
}
