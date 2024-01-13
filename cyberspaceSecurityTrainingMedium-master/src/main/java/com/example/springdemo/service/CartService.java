package com.example.springdemo.service;

import com.example.springdemo.entities.Cart;
import java.util.List;


public interface CartService {

	List<Cart> listCart(Cart cart);

	Cart getCartById(Integer cartId);
	int saveCart(Cart cart);
	int updateCart(Integer cartId, Cart cart);
	int removeCart(Integer cartId);
}
