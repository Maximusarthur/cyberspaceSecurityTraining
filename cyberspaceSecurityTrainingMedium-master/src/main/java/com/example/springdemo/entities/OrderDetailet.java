package com.example.springdemo.entities;
import lombok.Data;
@Data
public class OrderDetailet {
	private Integer odId; // 订单详情ID
	private Integer orderId; // 订单ID
	private Integer foodId; // 食物ID
	private Integer quantity; // 食物的数量
	private Food food; // 与订单详情相关的食物信息
}
