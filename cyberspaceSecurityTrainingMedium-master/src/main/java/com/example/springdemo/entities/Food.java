package com.example.springdemo.entities;
import lombok.Data;
@Data
public class Food {
	private Integer foodId; // 食物ID
	private String foodName; // 食物的名称
	private String foodExplain; // 食物的说明
	private Double foodPrice; // 食物的价格
	private Integer businessId; // 食物所属商家ID
	private String remarks; // 备注或附加信息
}
