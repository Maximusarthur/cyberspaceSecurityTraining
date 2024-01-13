package com.example.springdemo.entities;
import lombok.Data;

@Data
public class Cart {
	private Integer cartId;  // 购物车ID
	private Integer foodId;  // 所选食物ID
	private Integer businessId;  // 所选商家ID
	private Integer userId;  // 购物车所属用户ID
	private Integer quantity;  // 购物车中该项食物的数量
	private Food food;  // 与该购物车项相关的食物信息
	private Business business;  // 与该购物车项相关的商家信息
}
