package com.example.springdemo.controller;
import com.example.springdemo.entities.Food;
import com.example.springdemo.service.FoodService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/FoodController")
public class FoodController {
	
	@Resource
	private FoodService foodService;

	//查看所有商品
	@GetMapping("/listAllFood")
	public List<Food> listAllFood(){
		return foodService.listAllFood();
	}

	//查看商家商品
	@GetMapping("/listFoodByBusinessId")
	public List<Food> listFoodByBusinessId(@RequestParam Integer businessId){
		return foodService.listFoodByBusinessId(businessId);
	}

	//商家插入商品
	@RequestMapping(value = "/addFood")
	public Food addFood(@RequestParam Food food){
		return foodService.addFood(food);
	}

	//商家更新商品信息
	@PutMapping(value = "/updateFood/{foodId}")
	public int updateFoodById(@PathVariable Integer foodId,@RequestParam Food food){
		return foodService.updateFoodById(foodId,food);
	}

	//商家删除商品
	@DeleteMapping("/deleteFoodById")
	public int deleteFoodById(Integer foodId){
		return foodService.deleteFoodById(foodId);
	}
}
