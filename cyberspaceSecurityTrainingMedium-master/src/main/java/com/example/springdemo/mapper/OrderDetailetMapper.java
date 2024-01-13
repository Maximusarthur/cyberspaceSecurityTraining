package com.example.springdemo.mapper;


import com.example.springdemo.entities.OrderDetailet;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDetailetMapper {

	void saveOrderDetailetBatch(List<OrderDetailet> list);
	
	List<OrderDetailet> listOrderDetailetByOrderId(Integer orderId);
}
