package com.example.springdemo.controller;


import com.example.springdemo.entities.DeliveryAddress;
import com.example.springdemo.service.DeliveryAddressService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/DeliveryAddressController")
public class DeliveryAddressController {

	@Resource
	private DeliveryAddressService deliveryAddressService;
	
	@GetMapping("/listDeliveryAddressByUserId")
	public List<DeliveryAddress> listDeliveryAddressByUserId(@RequestBody DeliveryAddress deliveryAddress){
		return deliveryAddressService.listDeliveryAddressByUserId(deliveryAddress.getUserId());
	} 
	
	@GetMapping("/getDeliveryAddressById")
	public DeliveryAddress getDeliveryAddressById(DeliveryAddress deliveryAddress){
		return deliveryAddressService.getDeliveryAddressById(deliveryAddress.getDaId());
	} 
	
	@GetMapping("/saveDeliveryAddress")
	public int saveDeliveryAddress(DeliveryAddress deliveryAddress){
		return deliveryAddressService.saveDeliveryAddress(deliveryAddress);
	} 
	
	@GetMapping("/updateDeliveryAddress")
	public int updateDeliveryAddress(DeliveryAddress deliveryAddress){
		return deliveryAddressService.updateDeliveryAddress(deliveryAddress);
	} 
	
	@GetMapping("/removeDeliveryAddress")
	public int removeDeliveryAddress(DeliveryAddress deliveryAddress){
		return deliveryAddressService.removeDeliveryAddress(deliveryAddress.getDaId());
	} 
}
