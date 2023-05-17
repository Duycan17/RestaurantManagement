/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

/**
 *
 * @author duy
 */
import DAL.Food;
import DAL.FoodDAL;
import java.util.List;

public class FoodBLL {
    private FoodDAL foodDAL;

    public FoodBLL(FoodDAL foodDAL) {
        this.foodDAL = foodDAL;
    }

    public void addFood(String name, String description, double price) {
        Food food = new Food(0, name, description, price);
        foodDAL.addFood(food);
    }

    public void updateFood(int id, String name, String description, double price) {
        Food food = new Food(id, name, description, price);
        foodDAL.updateFood(food);
    }

    public void deleteFood(int foodId) {
        foodDAL.deleteFood(foodId);
    }

    public Food getFoodById(int foodId) {
        return foodDAL.getFoodById(foodId);
    }

    public List<Food> getAllFoods() {
        return foodDAL.getAllFoods();
    }
    public List<Food> getFoodByName(String keyword){
        return foodDAL.searchFood(keyword);
    }
}