package com.example.springdemo.mapper;
import com.example.springdemo.entities.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrdersMapper {
	void createOrders(Orders orders);
	Orders getOrdersById(Integer orderId);
	List<Orders> listOrdersByUserId(Integer userId);

	List<Orders> listOrdersByBusinessId(Integer businessId);

	int updateOrderById(Integer orderId, Orders order);

	int deleteOrderById(Integer orderId);
}
