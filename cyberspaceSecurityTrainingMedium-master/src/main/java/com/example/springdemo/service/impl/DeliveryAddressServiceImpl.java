package com.example.springdemo.service.impl;


import com.example.springdemo.mapper.DeliveryAddressMapper;
import com.example.springdemo.entities.DeliveryAddress;
import com.example.springdemo.service.DeliveryAddressService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryAddressServiceImpl implements DeliveryAddressService {
	
	@Resource
	private DeliveryAddressMapper deliveryAddressMapper;
	
	@Override
	public List<DeliveryAddress> listDeliveryAddressByUserId(String userId) {
		return deliveryAddressMapper.listDeliveryAddressByUserId(userId);
	}
	
	@Override
	public DeliveryAddress getDeliveryAddressById(Integer daId) {
		return deliveryAddressMapper.getDeliveryAddressById(daId);
	}
	
	@Override
	public int saveDeliveryAddress(DeliveryAddress deliveryAddress) {
		return deliveryAddressMapper.saveDeliveryAddress(deliveryAddress);
	}
	
	@Override
	public int updateDeliveryAddress(DeliveryAddress deliveryAddress) {
		return deliveryAddressMapper.updateDeliveryAddress(deliveryAddress);
	}
	
	@Override
	public int removeDeliveryAddress(Integer daId) {
		return deliveryAddressMapper.removeDeliveryAddress(daId);
	}
}
