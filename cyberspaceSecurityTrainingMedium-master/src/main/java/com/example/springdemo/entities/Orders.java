package com.example.springdemo.entities;
import lombok.Data;

@Data
public class Orders {
	private Integer orderId; // 订单ID
	private Integer userId; // 下单用户ID
	private Integer businessId; // 订单所属商家ID
	private String orderDate; // 订单的下单日期
	private Double orderTotal; // 订单的总金额
	private Integer daId; // 送货地址ID，表示送货地址与订单相关
	private Integer orderState; // 订单的状态
}
