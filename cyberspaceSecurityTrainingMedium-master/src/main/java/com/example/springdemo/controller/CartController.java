package com.example.springdemo.controller;

import com.example.springdemo.entities.Cart;
import com.example.springdemo.service.CartService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("CartController")
public class CartController {

	@Resource
	private CartService cartService;
	
	@GetMapping("/listCart")
	public List<Cart> listCart(Cart cart){
		return cartService.listCart(cart);
	}
	@GetMapping("/getCartById")
	Cart getCartById(@RequestParam(required = false) Integer cartId){
		return cartService.getCartById(cartId);
	}
	
	@RequestMapping(value = "/saveCart")
	public int saveCart(@RequestBody Cart cart){
		return cartService.saveCart(cart);
	}
	
	@PutMapping("/updateCart/{cartId}")
	public int updateCart(@PathVariable Integer cartId, @RequestBody Cart cart){
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("cartId", cartId);
		parameters.put("cart", cart);
		return cartService.updateCart(cartId,cart);
	}
	
	@DeleteMapping("/removeCart")
	public int removeCart(Integer cartId){
		return cartService.removeCart(cartId);
	}
}
