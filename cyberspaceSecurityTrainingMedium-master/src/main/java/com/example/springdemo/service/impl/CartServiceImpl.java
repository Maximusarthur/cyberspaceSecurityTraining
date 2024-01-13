package com.example.springdemo.service.impl;

import com.example.springdemo.mapper.CartMapper;
import com.example.springdemo.entities.Cart;
import com.example.springdemo.service.CartService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
	
	@Resource
	private CartMapper cartMapper;

	@Override
	public List<Cart> listCart(Cart cart) {
		return cartMapper.listCart(cart);
	}

	@Override
	public Cart getCartById(Integer cartId){
		return cartMapper.getCartById(cartId);
	}
	
	@Override
	public int saveCart(Cart cart) {
		return cartMapper.saveCart(cart);
	}
	
	@Override
	public int updateCart(Integer cartId, Cart cart) {
		return cartMapper.updateCart(cartId, cart);
	}
	
	@Override
	public int removeCart(Integer cartId) {
		return cartMapper.removeCart(cartId);
	}
}
