package com.example.springdemo.service.impl;

import com.example.springdemo.mapper.FoodMapper;
import com.example.springdemo.entities.Food;
import com.example.springdemo.service.FoodService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {
	
	@Resource
	private FoodMapper foodMapper;

	@Override
	public List<Food> listFoodByBusinessId(Integer businessId) {
		return foodMapper.listFoodByBusinessId(businessId);
	}

	@Override
	public List<Food> listAllFood(){
		return foodMapper.listAllFood();
	}

	//商家插入商品
	@Override
	public Food addFood(Food food){
		return foodMapper.addFood(food);
	}

	//商家更新商品信息
	@Override
	public int updateFoodById(Integer foodId, Food food){
		return foodMapper.updateFoodById(foodId,food);
	}

	//商家删除商品
	@Override
	public int deleteFoodById(Integer foodId){
		return foodMapper.deleteFoodById(foodId);
	}
}
