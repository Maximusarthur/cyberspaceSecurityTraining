package com.example.springdemo.service;

import com.example.springdemo.entities.OrderDetailet;
import com.example.springdemo.entities.Orders;

import java.util.List;

public interface OrdersService {

	void createOrders(Orders orders);

	List<OrderDetailet> listOrderDetailetByOrderId(Integer orderId);

	Orders getOrdersById(Integer orderId);
	List<Orders> listOrdersByUserId(Integer userId);

	List<Orders> listOrdersByBusinessId(Integer businessId);

	int updateOrderById(Integer orderId, Orders order);

	int deleteOrderById(Integer orderId);
}
