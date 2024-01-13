package com.example.springdemo.mapper;
import com.example.springdemo.entities.Food;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FoodMapper {

	List<Food> listFoodByBusinessId(Integer businessId);

	List<Food> listAllFood();

	Food addFood(Food food);
	int updateFoodById(Integer foodId, Food food);

	int deleteFoodById(Integer foodId);

}
