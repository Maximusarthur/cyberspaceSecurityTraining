package com.example.springdemo.service;

import com.example.springdemo.entities.Food;
import java.util.List;

public interface FoodService {

    List<Food> listFoodByBusinessId(Integer businessId);

    List<Food> listAllFood();

    Food addFood(Food food);
    int updateFoodById(Integer foodId, Food food);

    int deleteFoodById(Integer foodId);
}
