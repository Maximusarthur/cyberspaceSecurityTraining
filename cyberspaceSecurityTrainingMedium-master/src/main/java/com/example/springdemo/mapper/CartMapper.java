package com.example.springdemo.mapper;

import com.example.springdemo.entities.Cart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {

	List<Cart> listCart(Cart cart);

	Cart getCartById(Integer cartId);

	int saveCart(Cart cart);

	int updateCart(Integer cartId, Cart cart);

	int removeCart(Integer cartId);
}
