package com.example.springdemo.service.impl;
import com.example.springdemo.mapper.CartMapper;
import com.example.springdemo.mapper.OrderDetailetMapper;
import com.example.springdemo.mapper.OrdersMapper;
import com.example.springdemo.entities.Cart;
import com.example.springdemo.entities.OrderDetailet;
import com.example.springdemo.entities.Orders;
import com.example.springdemo.service.OrdersService;
import com.example.springdemo.util.CommonUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Resource
	private CartMapper cartMapper;
	@Resource
	private OrdersMapper ordersMapper;
	@Resource
	private OrderDetailetMapper orderDetailetMapper;

	@Override
	@Transactional
	public void createOrders(Orders orders) {
		// 1. 查询当前用户购物车中当前商家的所有食品
		Cart cart = new Cart();
		cart.setUserId(orders.getUserId());
		cart.setBusinessId(orders.getBusinessId());
		List<Cart> cartList = cartMapper.listCart(cart);

		// 2. 创建订单（返回生成的订单编号）
		orders.setOrderDate(CommonUtil.getCurrentDate());
		ordersMapper.createOrders(orders);
		int orderId = orders.getOrderId();

		// 3. 批量添加订单明细
		List<OrderDetailet> list = new ArrayList<>();
		for (Cart c : cartList) {
			OrderDetailet od = new OrderDetailet();
			od.setOrderId(orderId);
			od.setFoodId(c.getFoodId());
			od.setQuantity(c.getQuantity());
			list.add(od);
		}
		orderDetailetMapper.saveOrderDetailetBatch(list);

		// 4. 从购物车表中删除相关食品信息
		for (Cart cartItem : cartList) {
			cartMapper.removeCart(cartItem.getCartId());
		}
	}

	@Override
	public List<OrderDetailet> listOrderDetailetByOrderId(Integer orderId){
		return orderDetailetMapper.listOrderDetailetByOrderId(orderId);
	}

	@Override
	public Orders getOrdersById(Integer orderId) {
		return ordersMapper.getOrdersById(orderId);
	}

	@Override
	public List<Orders> listOrdersByUserId(Integer userId) {
		return ordersMapper.listOrdersByUserId(userId);
	}
	@Override
	public List<Orders> listOrdersByBusinessId(Integer businessId) {
		return ordersMapper.listOrdersByBusinessId(businessId);
	}

	@Override
	public int updateOrderById(Integer orderId, Orders order) {
		return ordersMapper.updateOrderById(orderId, order);
	}

	@Override
	public int deleteOrderById(Integer orderId) {
		return ordersMapper.deleteOrderById(orderId);
	}
}
