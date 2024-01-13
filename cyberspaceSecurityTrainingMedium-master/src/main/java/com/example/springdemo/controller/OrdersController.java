package com.example.springdemo.controller;

import com.example.springdemo.entities.OrderDetailet;
import com.example.springdemo.entities.Orders;
import com.example.springdemo.service.OrdersService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/OrdersController")
public class OrdersController {

	@Resource
	private OrdersService ordersService;
	
	@RequestMapping (value = "/createOrders")
	public void createOrders(@RequestBody Orders orders){
		ordersService.createOrders(orders);
	}
	
	@GetMapping("/getOrdersById")
	public Orders getOrdersById(@RequestParam Integer orderId){
		return ordersService.getOrdersById(orderId);
	}
	
	@GetMapping("/listOrdersByUserId")
	public List<Orders> listOrdersByUserId(@RequestParam Integer userId){
		return ordersService.listOrdersByUserId(userId);
	}

	@GetMapping("/listOrdersByBusinessId")
	public List<Orders> listOrdersByBusinessId(@RequestParam Integer BusinessId){
		return ordersService.listOrdersByBusinessId(BusinessId);
	}

	@GetMapping("/listOrderDetailetByOrderId")
	public List<OrderDetailet> listOrderDetailetByOrderId(@RequestParam Integer orderId){
		return ordersService.listOrderDetailetByOrderId(orderId);
	}

	@PutMapping("/updateOrderById/{orderId}")
	public int updateOrderById(@RequestParam Integer orderId, @RequestParam Orders order){
		return ordersService.updateOrderById(orderId, order);
	}

	@DeleteMapping("/deleteOrderById")
	public int deleteOrderById(@RequestParam Integer orderId){
		return ordersService.deleteOrderById(orderId);
	}
}
